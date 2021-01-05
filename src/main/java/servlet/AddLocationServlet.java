package servlet;

import dao.sql.DAOFactorySQL;
import form.NewLocationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddLocationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        NewLocationForm locationForm= new NewLocationForm(DAOFactorySQL.getInstance().getLocationDAO());
        locationForm.addLocation(request);

        String path = "/location.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path = "/location.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}
