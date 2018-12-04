package dao;

import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import service.DBException;
import util.DBHelper;
import util.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Configuration configuration = DBHelper.getInstance().getPostgreSqlConfiguration();
    private SessionFactory sessionFactory = DBUtil.getSessionFactory(configuration);

    public List<User> getUserById(Long id) throws DBException{
        List<User> userList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
             userList.add(session.get(User.class, id));
        } catch (HibernateException e){
            throw new DBException(e);
        }
        return userList;
    }

    public List<User> getAllUser() throws DBException{
        try (Session session = sessionFactory.openSession()) {
            List<User> userList = session.createQuery(" From User").list();
            if(userList.size() > 0)
            {
                return userList;
            }

            session.close();
            return null;

        } catch (HibernateException e){
            throw new DBException(e);
        }
    }

    public void insertUser(User user) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void updateUser(User user) throws DBException{
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void deleteUser(Long id) throws DBException{
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e){
            throw new DBException(e);
        }
    }
}