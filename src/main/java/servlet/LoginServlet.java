package servlet;

import bean.UserBean;
import dao.DAOFactorySQL;
import form.ConnexionForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        ConnexionForm connexionForm = new ConnexionForm(DAOFactorySQL.getInstance().getUserDAO());
        connexionForm.connectUser(request);

        String path;
        if(connexionForm.getResultat().equals("Connexion réussie !")){
            path = "/tables.html";
        } else {
            path = "/JSP_pages/login.jsp";
        }
        request.getRequestDispatcher(path).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path = "/JSP_pages/login.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}
