package edu.imagine.domain.entity.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
abstract public class BaseEntity<T extends Number> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;
}
