package servlet;

import dao.sql.DAOFactorySQL;
import form.NewActivityForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddActivityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        NewActivityForm activityForm= new NewActivityForm(DAOFactorySQL.getInstance().getActivityDAO(),
                                                            DAOFactorySQL.getInstance().getLocationDAO());
        activityForm.addActivity(request);

        String path = "/newActivity.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path = "/newActivity.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}