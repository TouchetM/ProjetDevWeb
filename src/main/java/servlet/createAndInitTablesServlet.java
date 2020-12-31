package servlet;

import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class createAndInitTablesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");

        String path;
        path = "/index.jsp";
        if(current_user != null){
            if(current_user.getRole().equals("admin")) {
                DAOFactorySQL.getInstance().initDataBase();
                request.getSession().setAttribute("current_user",null);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }
}
