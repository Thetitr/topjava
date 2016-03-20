package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Thetitr on 19.03.2016.
 */
@RestController
@RequestMapping("/ajax/meals")
public class AjaxUserMealController extends AbstractUserMealController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestParam ("dateTime") @DateTimeFormat (pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTime,
                           @RequestParam ("description") String description,
                           @RequestParam ("calories") int calories)
    {
        UserMeal meal = new UserMeal(dateTime, description, calories);
        super.create(meal);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete (@RequestParam(value = "id") int id)
    {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll()
    {
        return super.getAll();
    }

}


