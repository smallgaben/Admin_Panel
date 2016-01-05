package servlets;

import database.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "redByAdmin")
public class redByAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user=new UserDaoImp().read(Integer.valueOf(request.getParameter("redId")));
        System.out.println("TD: "+Integer.valueOf(request.getParameter("redId")));
        System.out.println("User: "+user.getId());
            Adress adress=new AdressDAOImp().read(user.getId());
            adress.setCountry(request.getParameter("country"));
            adress.setStreet(request.getParameter("street"));
            adress.setIndex(Integer.valueOf(request.getParameter("index")));
            user.setAdress(adress);
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            user.setFistName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            new UserDaoImp().update(user);
            request.setAttribute("users",new UserDaoImp().readAll());
            request.setAttribute("allRoles",new RoleDAOImp().readAll());
        List<MusicType> musicTypes=new ArrayList<>();
        Collections.addAll(musicTypes, new MusicTypeDAOImp().readAll());
        request.setAttribute("musicTypes",musicTypes);
        request.getRequestDispatcher("adminPanel.jsp").forward(request,response);
    }
}
