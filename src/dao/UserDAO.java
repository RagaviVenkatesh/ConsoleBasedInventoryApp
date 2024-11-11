package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	User findUserById(int id);
    User findUserByName(String name);
    List<User> getAllUsers();
    void addUser(User user);
    boolean validateUserCredentials(String name, String password);
}
