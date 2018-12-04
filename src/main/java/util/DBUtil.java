package util;

import factory.UserDaoFactory;
import factory.UserDaoHibernateFactory;
import factory.UserDaoJDBCFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBUtil {
    public static SessionFactory getSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static UserDaoFactory getProperties() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/home/lyubov/IdeaProjects/appJSP/src/main/resources/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(fileInputStream);
            String dao = properties.getProperty("Dao");
            switch (dao) {
                case "JDBC":
                    return new UserDaoJDBCFactory();
                case "Hibernate":
                    return new UserDaoHibernateFactory();
                default:
                    throw new Exception("Error! Unknown Dao property");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
