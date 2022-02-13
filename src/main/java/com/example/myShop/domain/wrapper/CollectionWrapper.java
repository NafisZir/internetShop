package com.example.myShop.domain.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * @author nafis
 * @since 13.02.2022
 */

@Getter
@Setter
@AllArgsConstructor
public class CollectionWrapper<T> {
    Collection<T> collection;
}
