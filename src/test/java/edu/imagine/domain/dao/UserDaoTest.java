package edu.imagine.domain.dao;

import edu.imagine.domain.entity.user.Role;
import edu.imagine.domain.filter.UserFilter;
import edu.imagine.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
    void findAllUsersByCompanyNameTest() {
        assertThat(userDao.findAllUsersByCompanyName(session, COMPANY_NAME))
                .hasSize(FIND_BY_COMPANY_NAME_LIST_SIZE);
    }

    @Test
    void findAllUsersByChatName() {
        assertThat(userDao.findAllUsersByChatName(session, CHAT_NAME))
                .hasSize(FIND_BY_CHAT_NAME_LIST_SIZE);
    }

    @Test
    void findAllUsersByRole() {
        assertThat(userDao.findAllUsersByRole(session, Role.User))
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



}