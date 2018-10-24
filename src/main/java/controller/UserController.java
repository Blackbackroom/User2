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
        Answer answer = new Answer(userService.getAllUsers());
        Gson gson = new Gson();
        try {
            User user = gson.fromJson(req.getReader(), User.class);
            userService.add(user);
        }catch (Exception e){
            answer.setAnswer(false);
            answer.setUsers(null);
            answer.setError("Server internal error");
            resp.getWriter().println(gson.toJson(answer));
            resp.setStatus(503);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Answer answer = new Answer(userService.getAllUsers());
        Gson gson = new Gson();
        try {
            User user = gson.fromJson(req.getReader(), User.class);
            userService.update(user);
        }catch (Exception e){
            answer.setAnswer(false);
            answer.setUsers(null);
            answer.setError("Server internal error");
            resp.getWriter().println(gson.toJson(answer));
            resp.setStatus(503);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Answer answer = new Answer(userService.getAllUsers());
        Gson gson = new Gson();
        try{
            answer.setAnswer(true);
            resp.getWriter().println(gson.toJson(answer));
        }catch (Exception e){
            answer.setAnswer(false);
            answer.setUsers(null);
            answer.setError("Server internal error");
            resp.getWriter().println(gson.toJson(answer));
            resp.setStatus(503);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Answer answer = new Answer(userService.getAllUsers());
        Gson gson = new Gson();
        try {
            String id = req.getParameter("id");
            userService.delete(Integer.valueOf(id));
        }catch (Exception e){
            answer.setAnswer(false);
            answer.setUsers(null);
            answer.setError("Server internal error");
            resp.getWriter().println(gson.toJson(answer));
            resp.setStatus(503);
        }
    }
}
