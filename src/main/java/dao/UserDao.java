package dao;

import model.User;
import service.DBException;

import java.util.List;

public interface UserDao {

    List<User> getUserById(Long id) throws DBException;
    List<User> getUserByLoginPassword(String login, String password) throws DBException;
    List<User> getAllUser() throws DBException;
    void insertUser(User user) throws DBException;
    void updateUser(User user) throws DBException;
    void deleteUser(Long id) throws DBException;
}
