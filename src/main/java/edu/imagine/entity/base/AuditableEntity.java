package edu.imagine.entity.base;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {})
@FieldDefaults(level = AccessLevel.PROTECTED)
@MappedSuperclass
abstract public class AuditableEntity<T extends Number> extends BaseEntity<T> {

    LocalDateTime createdAt;
    String createdBy;

}
