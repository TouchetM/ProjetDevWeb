package htmlWriter;

import bean.LocationBean;
import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class htmlWriterLocation {

    public static ArrayList getLocationList(HttpServletRequest request){
        ArrayList<String> location = new ArrayList<String>();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {
            LocationBean[] locations = DAOFactorySQL.getInstance().getLocationDAO().loadAll();

            for (LocationBean locationBean : locations) {
                String name = locationBean.getName();
                String address = locationBean.getAddress();

                location.add(name + " " + address);
            }
        }
        return location;
    }

    public static String getLocations(HttpServletRequest request) {
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            LocationBean[] locations = DAOFactorySQL.getInstance().getLocationDAO().loadAll();

            for (LocationBean locationBean : locations) {
                String name = locationBean.getName();
                String address = locationBean.getAddress();

                String location = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-primary shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">" +
                        "                                                                " + name + "</div>" +
                        "                                                                " + address + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + "</div>" +
                        "                                                        </div>" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                            <i class=\"fas fa-calendar fa-2x text-gray-300\"></i>" +
                        "                                                        </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";
                retour.append(location);
            }
        }

        return retour.toString();
    }
}
