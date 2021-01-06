package htmlWriter;

import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.http.HttpServletRequest;

public class htmlWriterAdminUser {


    public static String getUsersTable(HttpServletRequest request){
        String html = "<div class=\"table-responsive\">"+
                "                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">"+
                "                                    <thead>"+
                "                                        <tr>"+
                "                                            <th>id</th>"+
                "                                            <th>Email</th>"+
                "                                            <th>Nom</th>"+
                "                                            <th>Prénom</th>"+
                "                                            <th>Date de naissance</th>"+
                "                                            <th>Rôle</th>"+
                "                                        </tr>"+
                "                                    </thead>"+
                "                                    <tbody>";

                StringBuilder sb = new StringBuilder();

                UserBean[] users = DAOFactorySQL.getInstance().getUserDAO().loadAll();

                for (UserBean u:users) {

                    sb.append("<tr>");

                    sb.append("<td>");
                    sb.append("<a href=\"adminUserProfile?id_user="+u.getId()+"\">"+u.getId()+"</a>");
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getMail());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getLastName());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getFirstName());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getBirthdate());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getRole());
                    sb.append("</td>");

                    sb.append("</tr>");
                    System.out.println(sb);
                }
                html += sb.toString();
                html +=
                "                                    </tbody>"+
                "                                </table>"+
                "                            </div>";
                return html;
    }

    public static String getTest(){

        String myvar = "<div class=\"table-responsive\">"+
                "                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">"+
                "                                    <thead>"+
                "                                        <tr>"+
                "                                            <th>Name</th>"+
                "                                            <th>Position</th>"+
                "                                            <th>Office</th>"+
                "                                            <th>Age</th>"+
                "                                            <th>Start date</th>"+
                "                                            <th>Salary</th>"+
                "                                        </tr>"+
                "                                    </thead>"+
                "                                    <tbody>"+
                "                                        <tr>"+
                "                                            <td>Tiger Nixon</td><td>System Architect</td><td>Edinburgh</td><td>61</td><td>2011/04/25</td><td>$320,800</td>"+
                "                                        </tr>"+
                "                                    </tbody>"+
                "                                </table>"+
                "                            </div>";

            return myvar;
    }

}
