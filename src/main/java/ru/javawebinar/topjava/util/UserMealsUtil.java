package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    /*//делает логику вычисления - превышает ли сумма калорий за день заданное пользователем значение и фильтрация записей по дате и времени
    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
       // toLocalDate(); - преобразование LocalDateTime для того чтобы по дням делать сумму по калориям
      // toLocalTime(); - фильтровать записи по времени
     return null;
    }*/

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        //в первом цикле надо суммировать все калории по дням
        //нужно сгруппировать данные калории в датам, т.е. нам приходит список из нескольких дат , а нам надо сгруппировать это по датам
        //groupingBy принимать будет элемент коллекции, а возвращать будет ключ по которому группируем
        //Что в итоге произойдет- наш коллектор сгруппирует список по ключам датам и каждой дате будет соответствовать список из записей, соответствующих этой дате
        //на выходе от groupingBy получим мапу из ключа даты и значения в виде списка с едой, но нам вместо списка нужна сумма
        //summingInt будет суммировать калории, теперь на выходе мапа из даты и суммы калорий за день
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));

        //делаем второй стрим, где уже получим нужный результат
        //фильтр отфильтрует нашу коллекцию по времени
        //после преобразовываем коллекцию к результату
       return meals.stream()
                .filter(um -> TimeUtil.isBetweenInclusive(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExcess(um.getDateTime(), um.getDescription(), um.getCalories(),
                        caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                //был до этого стрим, а нам нужен список
                .collect(Collectors.toList());

    }
}
