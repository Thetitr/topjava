package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext ()
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId)
    {
        User ref = em.getReference(User.class, userId);
        userMeal.setUser(ref);
        if (userMeal.isNew()) {
            em.persist(userMeal);
            return userMeal;
        } else {
            return em.merge(userMeal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query= em.createQuery("DELETE FROM UserMeal  WHERE id=:id and user.id=:userId");
        return query.setParameter("id", id).setParameter("userId", userId).executeUpdate()!=0;

    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal meal =em.find(UserMeal.class, id);
        if (meal.getUser().getId()!=userId)
            throw new UnsupportedOperationException();
        return meal;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        TypedQuery<UserMeal> query= em.createQuery(" FROM UserMeal  WHERE user.id=:userId", UserMeal.class);
        List<UserMeal> meals= query.setParameter("userId", userId).getResultList();
        return meals;
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        TypedQuery<UserMeal> query= em.createQuery(" FROM UserMeal  WHERE dateTime (between :starttime and :endTime) and user.id=:userId", UserMeal.class);
        List<UserMeal> meals= query.setParameter("userId", userId).setParameter("starttime", startDate).setParameter("endTime", endDate).getResultList();
        return meals;
    }
}