package debt_advisor.controllers;

import debt_advisor.repositories.UserRepository;
import org.stringtemplate.v4.ST;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static debt_advisor.utils.StringTemplate.template;

public class UserController extends BaseController {
    private UserRepository userRepository;

    @Override
    protected void init() {
        userRepository = new UserRepository();
    }

    public void index(HttpServletResponse response) throws IOException {
        ST template = template("user");
        template.add("users", userRepository.fetchAll());
        response.getWriter().write(template.render());
    }

    public void create(HttpServletRequest request) {

    }

    public void delete(HttpServletRequest request) {

    }
}