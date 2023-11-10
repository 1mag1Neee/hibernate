package edu.imagine.domain.entity.profile;


import edu.imagine.domain.entity.base.BaseEntity;
import edu.imagine.domain.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.EnumType.STRING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "user")
@ToString(callSuper = true, exclude = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "profile", schema = "public")
public class Profile extends BaseEntity<Long> {

    @Embedded
    PersonalInfo personalInfo;

    @Column(name = "language")
    @Enumerated(STRING)
    Language language;

    @OneToOne(mappedBy = "profile", optional = false, fetch = FetchType.LAZY)
    User user;

}

