package debt_advisor.controllers;

import debt_advisor.models.User;
import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static debt_advisor.utils.StringTemplate.template;

public class UserController {
    public void index(HttpServletResponse resp) throws IOException {
        ST template = template("user");
        List<User> users = new ArrayList<User>();
        users.add(new User("Rajgopal", "Menon"));
        users.add(new User("Ramanathan", "Balakrishnan"));
        users.add(new User());
        users.add(new User());
        users.add(new User());
        template.add("users", users);
        resp.getWriter().write(template.render());
    }
}
