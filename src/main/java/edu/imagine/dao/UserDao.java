package edu.imagine.dao;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import edu.imagine.entity.payment.Payment;
import edu.imagine.entity.user.Role;
import edu.imagine.entity.user.User;
import edu.imagine.filter.UserFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static edu.imagine.entity.chat.QChat.chat;
import static edu.imagine.entity.company.QCompany.company;
import static edu.imagine.entity.payment.QPayment.payment;
import static edu.imagine.entity.profile.QProfile.profile;
import static edu.imagine.entity.user.QUser.user;
import static edu.imagine.entity.userchat.QUserChat.userChat;


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
                .innerJoin(user.profile, profile)
                .where(profile.personalInfo.firstname.eq(firstName))
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

    public List<User> findAllByCompanyName(Session session, String companyName) {
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

    public List<User> findAllByRole(Session session, Role role) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.role.eq(role))
                .fetch();
    }

    public List<User> findAllByFilter(Session session, UserFilter filter) {
        var predicate = QPredicate.builder()
                .add(filter.getRole(), user.role::eq)
                .add(filter.getCompanyName(), company.name::eq)
                .add(filter.getChatName(), chat.name::eq)
                .add(filter.getFirstName(), profile.personalInfo.firstname::eq)
                .add(filter.getUserName(), user.username::eq)
                .buildAnd();

        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .innerJoin(user.company, company)
                .innerJoin(user.userChats, userChat)
                .innerJoin(userChat.chat, chat)
                .where(predicate)
                .fetch();
    }

    public List<Payment> findAllPaymentsByCompanyName(Session session, String companyName) {
        return new JPAQuery<Payment>(session)
                .select(payment)
                .from(payment)
                .innerJoin(payment.receiver, user)
                .innerJoin(user.company, company)
                .innerJoin(user.profile, profile)
                .where(company.name.eq(companyName))
                .orderBy(user.profile.personalInfo.firstname.asc(), payment.amount.asc())
                .fetch();
    }

    public Double findAverageAmountByFirstAndLastNames(Session session, String firstname, String lastname) {
        return new JPAQuery<Double>(session)
                .select(payment.amount.avg())
                .from(payment)
                .innerJoin(payment.receiver, user)
                .innerJoin(user.profile, profile)
                .where(profile.personalInfo.firstname.eq(firstname)
                        .and(profile.personalInfo.lastname.eq(lastname)))
                .fetchOne();
    }

    // tuple = dto (когда возвращается не сущность, а поля из разных сущностей)
    public List<Tuple> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(company.name, payment.amount.avg())
                .from(company)
                .innerJoin(company.users, user)
                .innerJoin(user.payments, payment)
                .groupBy(company.name)
                .orderBy(company.name.asc())
                .fetch();
    }


 }
