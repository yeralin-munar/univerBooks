package com.epam.dao;

import com.epam.entity.BaseEntity;

import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public interface DAO<T extends BaseEntity> {

    boolean delete(int id);
    T find(int id);
    T insert(T object);
    boolean update(T object);
    List<T> all();
}
