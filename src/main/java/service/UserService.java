package service;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import util.DBUtil;

import java.util.List;

public class UserService {

    private UserDaoFactory userDaoFactory;

    {
        try {
            userDaoFactory = DBUtil.getProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserDao userDao = userDaoFactory.createUserDao();

    public UserService() {
        
    }

    public List<User> getUser(Long id) {
        try {
            return userDao.getUserById(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserByLoginPassword(String login, String password) {
        List<User> userList = null;
        try {
            userList = userDao.getUserByLoginPassword(login, password);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List getAllUser() {
        List userList = null;
        try {
            userList = userDao.getAllUser();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return userList;
    }


     public void updateUser(User user) {
         try {
             userDao.updateUser(user);
         } catch (DBException e) {
             e.printStackTrace();
         }
     }

    public void deleteUser(Long id) {
        try {
            userDao.deleteUser(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user){
        try {
           // userDaoHibernateImpl.createTable();
            userDao.insertUser(user);
            System.out.println(user);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /*public void cleanUp() throws DBException {
        UserDaoJDBCimpl dao = new UserDaoJDBCimpl(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }*/

    /*public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}
