package edu.imagine.entity.user;


import edu.imagine.entity.base.BaseEntity;
import edu.imagine.entity.company.Company;
import edu.imagine.entity.payment.Payment;
import edu.imagine.entity.profile.Profile;
import edu.imagine.entity.userchat.UserChat;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@NamedEntityGraph(name = "withCompany", attributeNodes = @NamedAttributeNode("company"))
@NamedEntityGraph(name = "withChats", attributeNodes = {
        @NamedAttributeNode(value = "userChats", subgraph = "withChat")},
        subgraphs = @NamedSubgraph(name = "withChat", attributeNodes = @NamedAttributeNode("chat")))
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "username", callSuper = false)
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

    @Enumerated(value = EnumType.STRING)
    Role role;

    @Type(JsonBinaryType.class)
    String info;
    String username;

    @ManyToOne(optional = false, fetch = LAZY)
    Company company;

    @OneToOne(cascade = ALL, optional = false, fetch = LAZY)
    Profile profile;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = ALL)
    @Fetch(FetchMode.SUBSELECT)
    List<UserChat> userChats;

    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "receiver")
    @Fetch(FetchMode.SUBSELECT)
    List<Payment> payments;

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

    @Builder
    public User(Long id, Role role, String info, String username, Company company, Profile profile) {
        super(id);
        this.role = role;
        this.info = info;
        this.username = username;
        this.company = company;
        this.profile = profile;
        this.userChats = new ArrayList<>();
        this.payments = new ArrayList<>();
    }
}
