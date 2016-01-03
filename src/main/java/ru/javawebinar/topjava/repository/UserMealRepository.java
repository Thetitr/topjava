package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    boolean delete(int id, int userId);

    UserMeal get(int idm, int userId);

    Collection<UserMeal> getAll();
}
