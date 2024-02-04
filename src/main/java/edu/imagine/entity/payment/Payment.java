package edu.imagine.entity.payment;

import edu.imagine.entity.base.BaseEntity;
import edu.imagine.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@ToString(callSuper = true, exclude = "receiver")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "payment", schema = "public")
@OptimisticLocking
public class Payment extends BaseEntity<Long> {

    Integer amount;

    @ManyToOne(fetch = LAZY, cascade = PERSIST, optional = false)
    User receiver;

    @Version
    Long version;

}
