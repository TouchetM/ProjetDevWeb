package htmlWriter;

import bean.ActivityBean;
import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.http.HttpServletRequest;

public class htmlWriterActivity {

    public static String getActivities(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            ActivityBean[] activities = DAOFactorySQL.getInstance().getActivityDAO().loadAll(current_user.getId());


            for (ActivityBean activityBean : activities) {
                int id_user = activityBean.getId_user();
                int id_location = activityBean.getId_location();
                String name = activityBean.getName();
                String date = activityBean.getDate();
                String start_at = activityBean.getStart_at();
                String end_at = activityBean.getEnd_at();

                String activity = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-primary shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">" +
                        "                                                                " + name + "</div>" +
                        "                                                                " + id_user + "</div>" +
                        "                                                                " + id_location + "</div>" +
                        "                                                                " + date + "</div>" +
                        "                                                                " + start_at + "</div>" +
                        "                                                                " + end_at + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">"  + "</div>" +
                        "                                                        </div>" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                            <i class=\"fas fa-calendar fa-2x text-gray-300\"></i>" +
                        "                                                        </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(activity);
            }

        }


        return retour.toString();
    }
}
