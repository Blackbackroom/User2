package persisting;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    MySQLConnector mySQLConnector = new MySQLConnector();

    public void add(User user){
        try(Connection connection = mySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `user` (name, password, age, sex) VALUES(?,?,?,?)")){

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setBoolean(4, user.isSex());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public User get(int id){
        User user=null;
        try(Connection connection = mySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE ID=?")){

            preparedStatement.setInt(1, id);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
             while (resultSet.next()){
                 user=new User();
                 user.setId(resultSet.getInt("id"));
                 user.setName(resultSet.getString("name"));
                 user.setPassword(resultSet.getString("password"));
                 user.setAge(resultSet.getInt("age"));
                 user.setSex(resultSet.getBoolean("sex"));
             }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public void update(User user){
        try(Connection connection = mySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `user` SET name=?, password=?, age=?, sex=? WHERE id=?")){

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setBoolean(4, user.isSex());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try(Connection connection = mySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `user` WHERE id=?")){

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        int maxId=-1;
        try(Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM `user`")){
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                maxId=resultSet.getInt("MAX(id)");
            }
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return maxId;
    }

}
