package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 700),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,1), "Завтрак", 700),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,2), "Завтрак", 700),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29,11,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29,17,0), "Ужин", 499),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List <UserMealWithExceed> testList=getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        for (UserMealWithExceed meal: testList)
        {
            System.out.println(meal);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> resultList= new ArrayList();
        Map<LocalDate, List<UserMeal>> groupedMealMap = mealList.stream().collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate()));
        Map<LocalDate, Boolean> exceedMap = new HashMap<>();
        groupedMealMap.forEach((localDate, userMeals) -> exceedMap.put(localDate, userMeals.stream().mapToInt(UserMeal::getCalories).sum() > caloriesPerDay));
        mealList.stream().filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .forEach(meal -> resultList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),exceedMap.get(meal.getDateTime().toLocalDate()))));

        return resultList;
    }
}
