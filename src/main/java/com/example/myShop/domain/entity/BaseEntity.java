package com.example.myShop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 23.12.2021
 */

@Getter
@MappedSuperclass
@Setter(value = PRIVATE)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Version
    @JsonIgnore
    @Column(columnDefinition = "integer DEFAULT 0")
    private Integer version = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        final BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
