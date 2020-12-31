package form;

import bean.LocationBean;
import dao.LocationDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class NewLocationForm {

    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_ADRESSE = "adresse";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private LocationDAO locationDAOo;

    public NewLocationForm(LocationDAO locationDAO){
        this.locationDAOo = locationDAO;
    }

    public void addLocation(HttpServletRequest request){
        String nom = getValeurChamp(request,CHAMP_NOM);
        String adresse = getValeurChamp(request,CHAMP_ADRESSE);

        traiterLocation(nom,adresse);

        if(erreurs.isEmpty()){
            LocationBean location = new LocationBean();

            location.setName(nom);
            location.setAddress(adresse);

            locationDAOo.createLocation(location);
            resultat = "Ajout réussi !";
            request.setAttribute("erreur_add_location",null);

        } else {
            resultat = "L'ajout a échoué!";
            request.setAttribute("erreur_add_location",erreurs);
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

    private void traiterLocation(String nom, String adresse){
        try {
            validationLocation(nom, adresse);
        } catch (FormValidationException e){
            setErreur(CHAMP_NOM,e.getMessage());
            setErreur(CHAMP_ADRESSE,e.getMessage());
        }
    }

    private void validationLocation(String nom, String adresse) throws FormValidationException{
        if(nom == null){
            throw new FormValidationException("Merci de saisir un intitulé.");
        }
        if(adresse == null){
            throw new FormValidationException("Merci de saisir une adresse.");
        }
        if(locationDAOo.exist(nom,adresse)){
            throw new FormValidationException("Ce lieu est déjà enregistré.");
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    public String getResultat() {
        return resultat;
    }
}
