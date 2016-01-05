package servlets;

import database.*;
import sun.text.normalizer.UTF16;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class addUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLogin(req,resp);
        if(!req.getParameter("password").equals(req.getParameter("confrimPassword"))){
            req.setAttribute("wrong",0);
            getAllMusicTypes(req);
            setAttributes(req);
            req.getRequestDispatcher("register form.jsp").forward(req, resp);
        }
        else{
           User user= createUser(req);
            new UserDaoImp().create(user);
            req.setAttribute("user",user);
            req.getRequestDispatcher("SignedIn.jsp").forward(req,resp);
        }
    }

    public void setAttributes(HttpServletRequest req){
        req.setAttribute("login",req.getParameter("login"));
        req.setAttribute("password",req.getParameter("password"));
        req.setAttribute("firstName",req.getParameter("firstName"));
        req.setAttribute("lastName",req.getParameter("lastName"));
        req.setAttribute("country",req.getParameter("country"));
        req.setAttribute("street",req.getParameter("street"));
        req.setAttribute("zip",req.getParameter("zip"));
    }

    public void getAllMusicTypes(HttpServletRequest request){
        List<MusicType> musicTypes=new ArrayList<>();
        Collections.addAll(musicTypes, new MusicTypeDAOImp().readAll());
        request.setAttribute("musicTypes",musicTypes);
    }

    public User createUser(HttpServletRequest req){
        User user=new User();
        Set<MusicType> musicTypes=new HashSet<>();
        String [] musics=req.getParameterValues("musicTypes");
        for(int i=0; i<musics.length; i++){
            musicTypes.add(new MusicTypeDAOImp().read(Integer.valueOf(musics[i])));
        }
        Adress adress=new Adress();
        adress.setCountry(req.getParameter("country"));
        adress.setStreet(req.getParameter("street"));
        adress.setIndex(Integer.valueOf(req.getParameter("zip")));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setFistName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setAdress(adress);
        user.setRole(new RoleDAOImp().getDefaultRole());
        user.setMusicTypes(musicTypes);
        return user;
    }

    public void checkLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        User[] users=new UserDaoImp().readAll();
        for(User s:users){
            if(s.getLogin().equals(request.getParameter("login"))) {
                request.setAttribute("wrong", 1);
                getAllMusicTypes(request);
                setAttributes(request);
                request.getRequestDispatcher("register form.jsp").forward(request, response);
            }
        }
    }



}
