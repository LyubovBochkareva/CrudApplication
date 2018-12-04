package factory;

import dao.UserDao;
import dao.UserDaoJDBCimpl;

public class UserDaoJDBCFactory implements UserDaoFactory {

    public UserDao createUserDao() {
        return new UserDaoJDBCimpl();
    }
}
