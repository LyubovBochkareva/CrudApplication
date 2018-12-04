package factory;

import dao.UserDao;
import dao.UserDaoHibernateImpl;

public class UserDaoHibernateFactory implements UserDaoFactory {

    public UserDao createUserDao() {
         return new UserDaoHibernateImpl();
    }
}
