package servlets;
import database.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "CheckSign")
public class CheckSign extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new UserDaoImp().read(request.getParameter("login"),request.getParameter("password"));
        if(user!=null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user.getLogin());
            request.setAttribute("user", user);
            switch (user.getRole().getRoleName()) {
                case "user":
                    request.getRequestDispatcher("SignedIn.jsp").forward(request, response);
                    break;
                case "mandator":
                    request.setAttribute("users",new UserDaoImp().readAll());
                    request.getRequestDispatcher("mandatorPage.jsp").forward(request,response);
                    break;
                case "admin":
                    request.setAttribute("users",new UserDaoImp().readAll());
                    List<Role> roles=new ArrayList<>();
                    Collections.addAll(roles, new RoleDAOImp().readAll());
                    request.setAttribute("allRoles",roles);
                    List<MusicType> musicTypes=new ArrayList<>();
                    Collections.addAll(musicTypes, new MusicTypeDAOImp().readAll());
                    request.setAttribute("musicTypes",musicTypes);
                    request.getRequestDispatcher("adminPanel.jsp").forward(request,response);
                    break;
            }
        }
            else{
                request.setAttribute("check", 0);
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
