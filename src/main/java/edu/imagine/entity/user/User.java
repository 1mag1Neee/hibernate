package edu.imagine.entity.user;


import edu.imagine.entity.base.BaseEntity;
import edu.imagine.entity.company.Company;
import edu.imagine.entity.profile.Profile;
import edu.imagine.entity.userchat.UserChat;
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
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "users", schema = "public")
public class User extends BaseEntity<Long> {

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

    @OneToOne(cascade = ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    Profile profile;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = ALL)
    List<UserChat> userChats;

    public void setCompany(Company company) {
        this.company = company;
        company.getUsers().add(this);
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
}
