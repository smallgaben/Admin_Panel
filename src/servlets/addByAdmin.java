package servlets;

import database.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by miroslav on 22.11.15.
 */
@WebServlet(name = "addByAdmin")
public class addByAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        Adress adress=new Adress();
        adress.setCountry(request.getParameter("country"));
        adress.setStreet(request.getParameter("street"));
        adress.setIndex(Integer.valueOf(request.getParameter("index")));
        user.setAdress(adress);
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setFistName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setRole(new RoleDAOImp().read(request.getParameter("role")));

        Set<MusicType> musicTypes=new HashSet<>();
        String [] musics=request.getParameterValues("musicTypes");
        for(int i=0; i<musics.length; i++){
            musicTypes.add(new MusicTypeDAOImp().read(Integer.valueOf(musics[i])));
        }

        user.setMusicTypes(musicTypes);

        new UserDaoImp().create(user);

        request.setAttribute("users",new UserDaoImp().readAll());
        request.setAttribute("allRoles",new RoleDAOImp().readAll());
        List<MusicType> allMusicTypes=new ArrayList<>();
        Collections.addAll(allMusicTypes, new MusicTypeDAOImp().readAll());
        request.setAttribute("musicTypes",allMusicTypes);
        request.getRequestDispatcher("adminPanel.jsp").forward(request,response);
    }
}
