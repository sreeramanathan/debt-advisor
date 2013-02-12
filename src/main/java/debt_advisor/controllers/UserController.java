package debt_advisor.controllers;

import debt_advisor.models.User;
import debt_advisor.repositories.UserRepository;
import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static debt_advisor.models.User.EMPTY_USER;
import static debt_advisor.utils.StringTemplate.template;
import static java.util.Arrays.asList;

public class UserController extends BaseController {
    private UserRepository userRepository;

    @Override
    protected void init() {
        userRepository = new UserRepository();
    }

    public void index(HttpServletResponse response) throws IOException {
        ST template = template("user");
        List<User> users = userRepository.fetchAll();
        template.add("users", backfillEmptyUsers(users));
        response.getWriter().write(template.render());
    }

    public void create(HttpServletRequest request) {
        userRepository.create(user(request));
    }

    public void delete(HttpServletRequest request) {
        userRepository.delete(user(request));
    }

    private User user(HttpServletRequest request) {
        return new User((String) request.getAttribute("forename"), (String) request.getAttribute("surname"));
    }

    private List<User> backfillEmptyUsers(List<User> users) {
        List<User> backFilledUsers = new ArrayList<User>(users);
        if (users.size() < 5) {
            for (int count = 0; count < 5 - users.size(); count++) {
                backFilledUsers.add(EMPTY_USER);
            }
        }
        return backFilledUsers;
    }
}