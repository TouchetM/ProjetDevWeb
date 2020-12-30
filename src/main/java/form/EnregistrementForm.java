package form;

import dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class EnregistrementForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "password";
    private static final String CHAMP_CONF  = "confirmation";
    private static final String CHAMP_NAME  = "name";
    private static final String CHAMP_FISTNAME  = "firstname";
    private static final String CHAMP_BIRTH  = "birthdate";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private UserDAO userDAOo;

    public EnregistrementForm(UserDAO userDAO){
        this.userDAOo = userDAO;
    }

    public Map<String,String> getErreur() {
        return erreurs;
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    public String getResultat() {
        return resultat;
    }

    private String getValeurChamp(HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
