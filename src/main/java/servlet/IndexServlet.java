package servlet;

import bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request,response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path;

        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");

        if(current_user == null){
            path = "/index.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } else {
            path = "/myFriends";
            response.sendRedirect(request.getContextPath() + path);
        }
    }
}
