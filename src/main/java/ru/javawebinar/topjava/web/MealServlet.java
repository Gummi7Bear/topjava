package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

//Сделать отображения списка еды в JSP, цвет записи в таблице зависит от параметра excess (красный/зеленый)
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealsRepository mealsRepository;
    private List<MealTo> mealsTo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");

        if (action == null) {
            log.debug("get meal list");
            request.setAttribute("mealsToList" , MealsUtil.mealToExcessList(MealsRepository.getInstance().getMeals(), 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
        else if(action == "delete") {
            log.debug("delete meal");
            MealsRepository.delete(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("mealsToList" , MealsUtil.mealToExcessList(MealsRepository.getInstance().getMeals(), 2000));
            response.sendRedirect("meals");
        }
    }
}
