package form;

import bean.FriendBean;
import bean.UserBean;
import dao.FriendDAO;
import dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

public class DemandeAmiForm {

    private static final String CHAMP_EMAIL = "email";

    private String resultat;
    private String erreur;

    private UserDAO userDAOo;
    private FriendDAO friendDAO;

    public DemandeAmiForm(UserDAO userDAO,FriendDAO friendDAO){
        this.userDAOo = userDAO;
        this.friendDAO = friendDAO;
    }

    public void envoyerDemandeAmi(HttpServletRequest request){
        String email = getValeurChamp(request,"email");

        traiterEmail(email);

        if(erreur == null){
            UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");
            UserBean userToInvite = userDAOo.load(email);

            if(!friendDAO.exist(current_user.getId(),userToInvite.getId())){
                FriendBean friendRequest = new FriendBean();
                friendRequest.setId_to(userToInvite.getId());
                friendRequest.setId_from(current_user.getId());
                friendDAO.createFriendRequest(friendRequest);
                resultat = "Demande d'ami envoyée";
                request.setAttribute("erreur_demande_ami",null);
            }
        } else {
            request.setAttribute("erreur_demande_ami",erreur);
            resultat = "échec de la création de la demande d'ami";
        }
    }

    private void traiterEmail(String email){
        try{
            validationEmail(email);
        } catch (FormValidationException e) {
            erreur = e.getMessage();
        }
    }

    private void validationEmail(String email) throws FormValidationException {
        if(email != null){
            if(!userDAOo.exist(email)){
                throw new FormValidationException("L'utilisateur n'existe pas.");
            }
        } else {
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }

    }

    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
    public String getResultat() {
        return resultat;
    }
}
