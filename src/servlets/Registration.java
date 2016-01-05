package servlets;

import database.MusicType;
import database.MusicTypeDAOImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAllMusicTypes(request);
        request.getRequestDispatcher("register form.jsp").forward(request, response);
    }

    public void getAllMusicTypes(HttpServletRequest request){
        List<MusicType> musicTypes=new ArrayList<>();
        Collections.addAll(musicTypes, new MusicTypeDAOImp().readAll());
        request.setAttribute("musicTypes",musicTypes);
    }
}
