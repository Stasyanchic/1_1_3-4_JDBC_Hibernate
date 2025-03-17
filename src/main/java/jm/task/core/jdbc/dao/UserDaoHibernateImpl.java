package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoHibernateImpl implements UserDao {

    public static final String TABLE_NAME = "users";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "age TINYINT" +
                ")";
        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sql);
            System.out.println("Таблица " + TABLE_NAME + " успешно создана.");
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица " + TABLE_NAME + " успешно удалена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO " + TABLE_NAME + " (name, lastName, age) VALUES ('" +
                name + "', '" + lastName + "', " + age + ")";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Пользователь " + name + " " + lastName + " успешно добавлен в базу данных.");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя в базу данных: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id;

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sql);
            System.out.println("Пользователь с id = " + id + " успешно удален из базы данных.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя из базы данных: " + e.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении всех пользователей из базы данных: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE " + TABLE_NAME;

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица " + TABLE_NAME + " успешно очищена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы " + TABLE_NAME + ": " + e.getMessage());
        }
    }
}
