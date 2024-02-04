package edu.imagine;

import edu.imagine.entity.company.Company;
import edu.imagine.util.HibernateTestUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RunnerTest {
    private final Session session = HibernateTestUtil.buildSession();

    @Test
    void getCompanyTest() {
        var company = session.get(Company.class, 1);
        assertThat(company.getName()).isEqualTo("Company 1");
    }

    public static void main(String[] args)  {
        
    }



}
