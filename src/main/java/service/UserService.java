package service;

import model.User;
import persisting.DB;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private DB database = new DB();

    public void add(User user){ database.add(user);
    }

    public User get(int id){
        return database.get(id);
    }

    public void update(User user){
        database.update(user);
    }

    public void delete(int id){
        database.delete(id);
    }

    public void setDatabase(DB database) {
        this.database = database;
    }

    public List<User> getAllUsers(){
        List<User> users=new ArrayList<>();

        for(int i=1; i<=database.getMaxId(); i++){
            User user = database.get(i);

            if(user != null){
                users.add(user);
            }
        }
        if(users.size()==0){
            users=null;
        }

        return users;
    }

}
