package controller;

import com.google.gson.Gson;
import model.Answer;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {

    UserService userService = new UserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        User user = gson.fromJson(req.getReader(), User.class);
        userService.add(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        User user = gson.fromJson(req.getReader(), User.class);
        userService.update(user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            Answer answer = new Answer(userService.getAllUsers());
            answer.setAnswer(true);
            Gson gson = new Gson();
            resp.getWriter().println(gson.toJson(answer));
        }catch (Exception e){
            resp.getWriter().println("Error");
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        userService.delete(Integer.valueOf(id));
    }
}
