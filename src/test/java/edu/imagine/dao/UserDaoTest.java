package edu.imagine.dao;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import edu.imagine.entity.user.Role;
import edu.imagine.entity.user.User;
import edu.imagine.filter.UserFilter;
import edu.imagine.util.HibernateTestUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static edu.imagine.entity.user.QUser.user;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoTest {
    private final Session session = HibernateTestUtil.buildSession();
    private final UserDao userDao = UserDao.INSTANCE;

    private static final int FIND_ALL_LIST_SIZE = 30;
    private static final int FIND_BY_FIRSTNAME_LIST_SIZE = 4;
    private static final int FIRST_USER_ID_ORDER_BY_BIRTHDAY = 20;
    private static final String COMPANY_NAME = "Company 1";
    private static final int FIND_BY_COMPANY_NAME_LIST_SIZE = 6;
    private static final String CHAT_NAME = "Chat 1";
    private static final int FIND_BY_CHAT_NAME_LIST_SIZE = 3;
    private static final int FIND_BY_ROLE_LIST_SIZE = 29;
    private static final int FIND_BY_FILTER_LIST_SIZE = 2;
    private static final int FIND_PAYMENTS_BY_COMPANY_LIST_SIZE = 25;


    @Test
    void findAllTest() {
        assertThat(userDao.findAll(session))
                .hasSize(FIND_ALL_LIST_SIZE);
    }

    @Test
    void findAllByFirstNameTest() {
        assertThat(userDao.findAllByFirstName(session, "Logan"))
                .hasSize(FIND_BY_FIRSTNAME_LIST_SIZE);
    }

    @Test
    void findLimitedUsersOrderedByBirthdayTest() {
        assertThat(userDao.findLimitedUsersOrderedByBirthday(session, 5)
                .get(0)
                .getId())
                .isEqualTo(FIRST_USER_ID_ORDER_BY_BIRTHDAY);
    }

    @Test
    void findAllByCompanyNameTest() {
        assertThat(userDao.findAllByCompanyName(session, COMPANY_NAME))
                .hasSize(FIND_BY_COMPANY_NAME_LIST_SIZE);
    }

    @Test
    void findAllUsersByChatName() {
        assertThat(userDao.findAllUsersByChatName(session, CHAT_NAME))
                .hasSize(FIND_BY_CHAT_NAME_LIST_SIZE);
    }

    @Test
    void findAllByRole() {
        assertThat(userDao.findAllByRole(session, Role.User))
                .hasSize(FIND_BY_ROLE_LIST_SIZE);
    }

    @Test
    void findAllUsersByFilterTest() {
        assertThat(userDao.findAllByFilter(session, UserFilter.builder()
                .chatName("Chat 2")
                .companyName("Company 2")
                .build()))
                .hasSize(FIND_BY_FILTER_LIST_SIZE);
    }

    @Test
    void findAllPaymentsByCompanyNameTest() {
        assertThat(userDao.findAllPaymentsByCompanyName(session, "Company 5"))
                .hasSize(FIND_PAYMENTS_BY_COMPANY_LIST_SIZE);
    }

    @Test
    void findAverageAmountByFirstAndLastNamesTest() {
        Double avg = userDao.findAverageAmountByFirstAndLastNames(session, "Ava", "Wilson");
        assertThat(Math.floor(avg))
                .isEqualTo(1479d);
    }

    @Test
    void findCompanyNamesWithAvgUserPaymentsOrderedByCompanyNameTest() {
        List<Tuple> dtos = userDao.findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(session);
        List<String> companyNames = dtos.stream().map(t -> t.get(0, String.class)).toList();
        List<Double> avgAmounts = dtos.stream().map(t -> t.get(1, Double.class)).toList();

        Assertions.assertAll(
                () -> assertThat(dtos).hasSize(4),
                () -> assertThat(companyNames).contains("Company 1", "Company 2", "Company 3", "Company 5"),
                () -> assertThat(avgAmounts).contains(1500d, 1480d)
        );
    }

    @Test
    void stuff() {
        session.get(User.class, 1L);
    }

    public static void main(String[] args) {
        @Cleanup Session session = HibernateTestUtil.buildSession();
        List<User> fetch = new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .fetch();
    }
}