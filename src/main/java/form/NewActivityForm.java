package form;

import bean.ActivityBean;
import dao.ActivityDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class NewActivityForm {

    private static final int CHAMP_LOCATION = 0;
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_DATE = "date";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private ActivityDAO activityDAOo;

    public NewActivityForm(ActivityDAO activityDAO){
        this.activityDAOo = activityDAO;
    }

    public void addActivity(HttpServletRequest request){
        String nom = getValeurChamp(request,CHAMP_NOM);
        String date = getValeurChamp(request,CHAMP_DATE);
        int id_location = CHAMP_LOCATION;

        traiterLocation(nom,date,id_location);

        if(erreurs.isEmpty()){
            ActivityBean activity = new ActivityBean();

            activity.setName(nom);
            activity.setDate(date);
            activity.setId_location(id_location);

            activityDAOo.createActivity(activity);
            resultat = "Ajout réussi !";
            request.setAttribute("erreur_add_activity",null);

        } else {
            resultat = "L'ajout a échoué!";
            request.setAttribute("erreur_add_activity",erreurs);
        }
    }

    private String getValeurChamp(HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    private void traiterLocation(String nom, String date, int id_location){
        try {
            validationLocation(nom, date, id_location);
        } catch (FormValidationException e){
            setErreur(CHAMP_NOM,e.getMessage());
            setErreur(CHAMP_DATE,e.getMessage());
        }
    }

    private void validationLocation(String nom, String date, int id_location) throws FormValidationException{
        if(nom == null){
            throw new FormValidationException("Merci de saisir un intitulé.");
        }
        if(date == null){
            throw new FormValidationException("Merci de saisir une date.");
        }
        if(id_location == 0){
            throw new FormValidationException("Merci de choisir un lieu.");
        }
        if(activityDAOo.exist(date,id_location)){
            throw new FormValidationException("Cette activité est déjà enregistrée.");
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    public String getResultat() {
        return resultat;
    }
}
