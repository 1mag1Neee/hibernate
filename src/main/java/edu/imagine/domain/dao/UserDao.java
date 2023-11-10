package edu.imagine.domain.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import edu.imagine.domain.entity.user.Role;
import edu.imagine.domain.entity.user.User;
import edu.imagine.domain.filter.UserFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static edu.imagine.domain.entity.chat.QChat.chat;
import static edu.imagine.domain.entity.company.QCompany.company;
import static edu.imagine.domain.entity.user.QUser.user;
import static edu.imagine.domain.entity.userchat.QUserChat.userChat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {
    public static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .fetch();
    }

    public List<User> findAllByFirstName(Session session, String firstName) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.profile.personalInfo.firstname.eq(firstName))
                .fetch();
    }

    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .limit(limit)
                .orderBy(user.profile.personalInfo.birthDate.asc())
                .fetch();
    }

    public List<User> findAllUsersByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .join(user.company, company)
                .where(company.name.eq(companyName))
                .fetch();
    }

    public List<User> findAllUsersByChatName(Session session, String chatName) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .join(user.userChats, userChat)
                .join(userChat.chat, chat)
                .where(chat.name.eq(chatName))
                .fetch();
    }

    public List<User> findAllUsersByRole(Session session, Role role) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.role.eq(role))
                .fetch();
    }

    public List<User> findAllByFilter(Session session, UserFilter filter) {
        Predicate predicates = QPredicate.builder()
                .add(filter.getUserName(), user.username::eq)
                .add(filter.getFirstName(), user.profile.personalInfo.firstname::eq)
                .add(filter.getCompanyName(), user.company.name::eq)
                .add(filter.getRole(), user.role::eq)
                .add(filter.getChatName(), userChat.chat.name::eq)
                .buildAnd();

        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .innerJoin(user.userChats, userChat)
                .innerJoin(userChat.chat, chat)
                .where(predicates)
                .fetch();
    }






}
