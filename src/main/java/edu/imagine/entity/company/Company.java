package edu.imagine.entity.company;


import edu.imagine.entity.base.AuditableEntity;
import edu.imagine.entity.user.User;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "company", schema = "public")
public class Company extends AuditableEntity<Integer> {

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true)
    List<User> users;

    @ElementCollection
    @CollectionTable(name = "company_locale")
    List<LocaleInfo> locales;

    public void addUser(User user) {
        user.setCompany(this);
    }

    public void addLocale(LocaleInfo locale) {
        this.locales.add(locale);
    }

    public Company(LocalDateTime createdAt, String createdBy, String name, List<User> users, List<LocaleInfo> locale) {
        super(createdAt, createdBy);
        this.name = name;
        this.users = (users != null) ? users : new ArrayList<>();
        this.locales = (locales != null) ? locales : new ArrayList<>();
    }
}
