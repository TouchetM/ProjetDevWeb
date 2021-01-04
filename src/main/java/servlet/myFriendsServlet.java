package servlet;

import bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class myFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");
        String path;

        if(current_user != null){
            path = "/myFriends.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } else {
            path = "/index";
            response.sendRedirect(request.getContextPath() + path);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");
        String path;

        if(current_user != null){
            path = "/myFriends.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } else {
            path = "/index";
            response.sendRedirect(request.getContextPath() + path);
        }

    }
}
