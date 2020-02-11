package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

//хранилище завтраков
public class MealsRepository {
    private static MealsRepository mealsRepository;
    private List<Meal> meals;

    private MealsRepository() {
        this.meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
    }

    //паттерн Singleton, объект этого класса будет 1 на все приложение
    public static MealsRepository getInstance() {
        if ( mealsRepository == null) {
            mealsRepository = new MealsRepository();
        }
        return mealsRepository;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    //добавит новый объект еды в список
    public void setMealInList(Meal meal) {
        meals.add(meal);
    }
}
