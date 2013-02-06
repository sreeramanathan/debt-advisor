package tour_planner.controllers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static tour_planner.utils.StringTemplate.template;

public class UserController {
    public void index(HttpServletResponse resp) throws IOException {
        resp.getWriter().write(template("user").render());
    }
}
