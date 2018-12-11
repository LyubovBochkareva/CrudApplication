package dao;

import model.User;
import result.Executor;
import service.DBException;
import util.DBHelper;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class UserDaoJDBCimpl implements UserDao{

    private Executor executor;
    private Connection connection = DBHelper.getInstance().getPostgresqlConnection();

    public UserDaoJDBCimpl() {
        this.executor = new Executor(connection);
    }


    public List<User> getUserById(Long id) throws DBException {
        String query = "SELECT * FROM public.users WHERE id=";
        try {
            return executor.execQuery(query + id, ResultUser::handle);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        }
    }

    @Override
    public List<User> getUserByLoginPassword(String login, String password) throws DBException {
        return null;
    }

    public List<User> getAllUser() throws DBException {
        String query = "SELECT * FROM public.users";
        try {
            return executor.execQuery(query, ResultUser::handle);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        }
    }


    public void insertUser(User user) throws DBException {
        try {
            connection.setAutoCommit(false);
            createTable();
            String query = "INSERT INTO public.users (login, password, name, age)\n" +
                    "values ('" + user.getLogin() + "', '" + user.getPassword() + "', '" + user.getName() + "', " + user.getAge() + ");";
            System.out.println("\u001B[32m" + query);
            executor.execUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void updateUser(User user) throws DBException {
        try {
            connection.setAutoCommit(false);
            String query = "UPDATE public.users SET login = '" + user.getLogin() + "', password = '" + user.getPassword() + "', name = '" + user.getName() + "', age = " + user.getAge() + " WHERE id = " + user.getId() + "";
            executor.execUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void deleteUser(Long id) throws DBException {
        try {
            connection.setAutoCommit(false);
            String query = "DELETE FROM public.users WHERE id =";
            executor.execUpdate(query + id);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    private void createTable() throws DBException {
        String query = "" +
                "CREATE TABLE IF NOT EXISTS public.users" +
                "(" +
                "  id       BIGSERIAL NOT NULL" +
                "    CONSTRAINT table_name_pkey" +
                "    PRIMARY KEY," +
                "  login    VARCHAR   NOT NULL," +
                "  password VARCHAR   NOT NULL," +
                "  name     VARCHAR   NOT NULL," +
                "  age      INTEGER   NOT NULL," +
                "  role     VARCHAR   NOT NULL" +
                ");" +
                "" +
                "ALTER TABLE public.users" +
                "  OWNER TO postgres;" +
                "" +
                "CREATE UNIQUE INDEX IF NOT EXISTS table_name_id_uindex" +
                "  ON public.users (id);" +
                "" +
                "CREATE UNIQUE INDEX IF NOT EXISTS table_name_login_uindex" +
                "  ON public.users (login);" +
                "";
        System.out.println("\u001B[32m" + query);
        try {
            executor.execUpdate(query);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /*public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }*/

    public static class ResultUser {
        static List<User> handle(ResultSet resultSet) throws SQLException {
            List<User> userLinkedList = new LinkedList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6));
                userLinkedList.add(user);
            }
            return userLinkedList;
        }
    }
}
