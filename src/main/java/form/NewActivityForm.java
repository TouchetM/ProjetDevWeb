package form;

import bean.ActivityBean;
import bean.LocationBean;
import dao.ActivityDAO;
import dao.LocationDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class NewActivityForm {

    private static final String CHAMP_LOCATION = "address";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_DATE = "date";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private ActivityDAO activityDAO;
    private LocationDAO locationDAO;

    public NewActivityForm(ActivityDAO activityDAO, LocationDAO locationDAO){

        this.activityDAO = activityDAO;
        this.locationDAO = locationDAO;
    }

    public void addActivity(HttpServletRequest request){
        String nom = getValeurChamp(request,CHAMP_NOM);
        String date = getValeurChamp(request,CHAMP_DATE);
        String location = getValeurChamp(request, CHAMP_LOCATION);
        int id_location = locationDAO.getId(CHAMP_LOCATION);

        traiterLocation(nom,date,location,id_location);

        if(erreurs.isEmpty()){
            ActivityBean activity = new ActivityBean();

            activity.setName(nom);
            activity.setDate(date);
            //activity.setId_location(id_location);

            activityDAO.createActivity(activity);
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

    private void traiterLocation(String nom, String date, String location, int id_location){
        try {
            validationLocation(nom, date, location, id_location);
        } catch (FormValidationException e){
            setErreur(CHAMP_NOM,e.getMessage());
            setErreur(CHAMP_DATE,e.getMessage());
            setErreur(CHAMP_LOCATION,e.getMessage());
        }
    }

    private void validationLocation(String nom, String date, String location, int id_location) throws FormValidationException{
        if(nom == null){
            throw new FormValidationException("Merci de saisir un intitulé.");
        }
        if(date == null){
            throw new FormValidationException("Merci de saisir une date.");
        }
        if(location == null){
            throw new FormValidationException("Merci de choisir un lieu.");
        }
        if(activityDAO.exist(date,id_location)){
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
