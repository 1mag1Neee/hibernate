package edu.imagine.entity.company;


import edu.imagine.entity.base.AuditableEntity;
import edu.imagine.entity.user.User;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.MapKey;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.CascadeType.ALL;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, of = "name")
public class Company extends AuditableEntity<Integer> {

    String name;

    @MapKey(name = "username")
    @OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true)
    Map<String, User> users = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "company_locale")
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    Map<String, String> locales = new HashMap<>();

    @Builder
    public Company(LocalDateTime createdAt, String createdBy, String name) {
        super(createdAt, createdBy);
        this.name = name;
    }

    public void addLocale(String lang, String descr) {
        this.locales.put(lang, descr);
    }

    public void addUser(User user) {
        user.setCompany(this);
    }

}
