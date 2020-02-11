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
    public void init() throws ServletException {
        this.mealsRepository = MealsRepository.getInstance();
        this.mealsTo = MealsUtil.mealToExcessList(mealsRepository.getMeals(), 2000);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        request.setAttribute("mealsToList" , mealsTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        //  response.sendRedirect("meals.jsp");
    }
}
