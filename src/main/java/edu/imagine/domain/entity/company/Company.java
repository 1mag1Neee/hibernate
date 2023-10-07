package edu.imagine.domain.entity.company;


import edu.imagine.domain.entity.base.AuditableEntity;
import edu.imagine.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.CascadeType.ALL;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "name")
@ToString(callSuper = true, exclude = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "company", schema = "public")
public class Company extends AuditableEntity<Integer> {

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true)
    @MapKey(name = "username")
    Map<String, User> users;

    @ElementCollection
    @CollectionTable(name = "company_locale")
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    Map<String, String> locales;

    public void addUser(User user) {
        user.setCompany(this);
    }

    public void addLocale(String lang, String descr) {
        this.locales.put(lang, descr);
    }

    public Company(LocalDateTime createdAt, String createdBy, String name, Map<String, User> users, Map<String, String> locales) {
        super(createdAt, createdBy);
        this.name = name;
        this.users = (users != null) ? users : new HashMap<>();
        this.locales = (locales != null) ? locales : new HashMap<>();
    }
}
