package servlet;

import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class PositifServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {
            UserBean[] friends = DAOFactorySQL.getInstance().getUserDAO().friendList(current_user);
            DAOFactorySQL.getInstance().getNotificationDAO().notifier(friends);
        }

        String path = "/positif.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path = "/positif.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}