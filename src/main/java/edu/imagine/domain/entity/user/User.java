package edu.imagine.domain.entity.user;


import edu.imagine.domain.entity.base.BaseEntity;
import edu.imagine.domain.entity.company.Company;
import edu.imagine.domain.entity.profile.Profile;
import edu.imagine.domain.entity.userchat.UserChat;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;


@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(of = "username", callSuper = false)
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "users", schema = "public")
public class User extends BaseEntity<Long> implements Comparable<User> {

    @Enumerated(value = EnumType.STRING)
    Role role;

    @Column(name = "info")
    @Type(JsonBinaryType.class)
    String info;

    @Column(name = "username")
    String username;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;

    @OneToOne(cascade = ALL, optional = false, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    Profile profile;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = ALL)
    List<UserChat> userChats;

    public void setCompany(Company company) {
        this.company = company;
        company.getUsers().put(username, this);
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
        profile.setUser(this);
    }

    public void addUserChat(UserChat userChat) {
        this.userChats.add(userChat);
        userChat.setUser(this);
    }

    public User(Role role, String info, String username, Company company, Profile profile, List<UserChat> userChats) {
        this.role = role;
        this.info = info;
        this.username = username;
        this.company = company;
        this.profile = profile;
        this.userChats = (userChats != null) ? userChats : new ArrayList<>();
    }

    @Override
    public int compareTo(User o) {
        return (int) (this.id - o.id);
    }
}
