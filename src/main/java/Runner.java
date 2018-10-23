import model.User;
import service.UserService;

public class Runner {

    public static void main(String[] args) {
        User user=new User();
        user.setName("Ivan");
        user.setPassword("1234");
        user.setAge(20);
        user.setSex(true);

        UserService userService = new UserService();
        userService.add(user);
    }
}
