package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.saveUser("Stan", "Arakelyan", (byte) 25);
        userService.saveUser("Miceal", "Michaelov", (byte) 24);
        userService.saveUser("Semen", "Voronin", (byte) 54);



    }
}
