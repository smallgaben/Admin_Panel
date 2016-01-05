package servlets;

import database.MusicType;
import database.MusicTypeDAOImp;
import database.RoleDAOImp;
import database.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by miroslav on 22.11.15.
 */
@WebServlet(name = "delByAdmin")
public class delByAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new UserDaoImp().delete(Integer.valueOf(request.getParameter("delId")));
        request.setAttribute("users",new UserDaoImp().readAll());
        request.setAttribute("allRoles",new RoleDAOImp().readAll());
        List<MusicType> musicTypes=new ArrayList<>();
        Collections.addAll(musicTypes, new MusicTypeDAOImp().readAll());
        request.setAttribute("musicTypes",musicTypes);
        request.getRequestDispatcher("adminPanel.jsp").forward(request,response);
    }
}
