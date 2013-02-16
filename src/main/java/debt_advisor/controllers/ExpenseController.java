package debt_advisor.controllers;

import debt_advisor.models.Expense;
import debt_advisor.repositories.ExpenseRepository;
import debt_advisor.repositories.UserRepository;
import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static debt_advisor.models.Expense.EMPTY_EXPENSE;
import static debt_advisor.utils.StringTemplate.template;

public class ExpenseController extends BaseController {
    private UserRepository userRepository;
    private ExpenseRepository expenseRepository;

    @Override
    protected void init() {
        userRepository = new UserRepository();
        expenseRepository = new ExpenseRepository();
    }

    public void index(HttpServletResponse response) throws IOException {
        ST template = template("expense");
        List<Expense> expenses = expenseRepository.fetchAll();
        template.add("users", userRepository.fetchAll());
        template.add("expenses", backfillEmptyExpenses(expenses));
        response.getWriter().write(template.render());
    }

    private List<Expense> backfillEmptyExpenses(List<Expense> expenses) {
        List<Expense> backFilledExpenses = new ArrayList<Expense>(expenses);
        if (expenses.size() < 5) {
            for (int count = 0; count < 5 - expenses.size(); count++) {
                backFilledExpenses.add(EMPTY_EXPENSE);
            }
        }
        return backFilledExpenses;
    }
}