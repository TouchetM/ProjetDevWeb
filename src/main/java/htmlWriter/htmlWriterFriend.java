package htmlWriter;

import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.http.HttpServletRequest;

public class htmlWriterFriend {

    public static String getFriends(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            UserBean[] friends = DAOFactorySQL.getInstance().getUserDAO().friendList(current_user);


            for (UserBean userBean : friends) {
                String mail = userBean.getMail();
                String name = userBean.getLastName();
                String firstname = userBean.getFirstName();

                String friend = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-primary shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">" +
                        "                                                                " + mail + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + firstname + " " + name + "</div>" +
                        "                                                        </div>" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                            <i class=\"fas fa-calendar fa-2x text-gray-300\"></i>" +
                        "                                                        </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(friend);
            }

        }


        return retour.toString();
    }

    public static String getFriendRequested(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            UserBean[] friends = DAOFactorySQL.getInstance().getUserDAO().friendRequested(current_user);


            for (UserBean userBean : friends) {
                String mail = userBean.getMail();
                String name = userBean.getLastName();
                String firstname = userBean.getFirstName();

                String friend = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-danger shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-danger text-uppercase mb-1\">" +
                        "                                                                " + mail + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + firstname + " " + name + "</div>" +
                        "                                                        </div>" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                            <i class=\"fas fa-calendar fa-2x text-gray-300\"></i>" +
                        "                                                        </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(friend);
            }

        }


        return retour.toString();
    }

    public static String getFriendRequesting(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            UserBean[] friends = DAOFactorySQL.getInstance().getUserDAO().friendRequesting(current_user);


            for (UserBean userBean : friends) {
                String mail = userBean.getMail();
                String name = userBean.getLastName();
                String firstname = userBean.getFirstName();

                String friend = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-warning shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-warning text-uppercase mb-1\">" +
                        "                                                                " + mail + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + firstname + " " + name + "</div>" +
                        "                                                        </div>" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                            <i class=\"fas fa-calendar fa-2x text-gray-300\"></i>" +
                        "                                                        </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(friend);
            }

        }


        return retour.toString();
    }

}
