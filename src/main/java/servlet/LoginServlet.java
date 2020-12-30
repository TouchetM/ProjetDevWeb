package servlet;

import bean.UserBean;
import dao.sql.DAOFactorySQL;
import form.ConnexionForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        ConnexionForm connexionForm = new ConnexionForm(DAOFactorySQL.getInstance().getUserDAO());
        connexionForm.connectUser(request);

        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");

        String path;
        if(current_user != null){
            path = "/index";
        } else {
            path = "/login.jsp";
        }
        request.getRequestDispatcher(path).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");

        String path;
        if(current_user != null){
            path = "/index";
        } else {
            path = "/login.jsp";
        }
        request.getRequestDispatcher(path).forward(request,response);
    }
}
