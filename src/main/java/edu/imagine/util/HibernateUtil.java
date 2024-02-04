package edu.imagine.util;

import edu.imagine.dao.UserDao;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.graph.GraphSemantic;

import java.util.Map;


@UtilityClass
public final class HibernateUtil {
    private static final UserDao userDao = UserDao.INSTANCE;

    public static SessionFactory buildSessionFactory() {
        return new Configuration()
                .setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy())
                .configure()
                .buildSessionFactory();
    }

    public static Map<String, Object> buildPropsFromEntityGraph(Session session, String entityGraphName) {
        return Map.of(
                GraphSemantic.FETCH.getJakartaHintName(),
                session.getEntityGraph("withCompany")
        );
    }


}
