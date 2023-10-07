package edu.imagine;

import edu.imagine.domain.entity.company.Company;
import edu.imagine.util.HibernateUtil;

public class Runner {

    public static void main(String[] args) {

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
            var session =  sessionFactory.openSession()) {

            var company = session.get(Company.class, 3);
            System.out.println(company);

        }
    }

}
