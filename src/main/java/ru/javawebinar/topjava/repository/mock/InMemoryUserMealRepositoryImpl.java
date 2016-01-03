package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private int userId;
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int id, int userId) {
        boolean isDelete=false;
        if (this.userId == userId)
        {
            UserMeal deletedMeal=repository.remove(id);
            if (deletedMeal!=null)
            {
                isDelete = true;
            }
        }

        return isDelete;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return userId == this.userId ? repository.get(id) : null;
    }

    @Override
    public Collection<UserMeal> getAll() {
        List allMeals = new ArrayList<>(repository.values());
        Collections.sort(allMeals, (o1, o2) -> {
            UserMeal thisUser= (UserMeal) o1;
            UserMeal that = (UserMeal) o2;
            return thisUser.getDateTime().compareTo(that.getDateTime());
        });
        return allMeals;
    }
}

