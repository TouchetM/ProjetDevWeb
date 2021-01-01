package htmlWriter;

import bean.NotificationBean;
import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.http.HttpServletRequest;

public class htmlWriterNotification {

    public static String getNotifications(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            NotificationBean[] notifications = DAOFactorySQL.getInstance().getNotificationDAO().userNotification(current_user);


            for (NotificationBean notif : notifications) {
                String message = notif.getMessage();
                String notification = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-primary shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">" +
                        "                                                                " + message + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                           </form>" +
                        "                                                        </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(notification);
            }

        }


        return retour.toString();
    }

}
