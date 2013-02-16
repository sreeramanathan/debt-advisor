package debt_advisor.models;

public class Expense {
    private float amount;
    private String description;
    private static final String EMPTY_EXPENSE_AMOUNT = "Amount";
    private static final String EMPTY_EXPENSE_DESCRIPTION = "Description";
    public static final Expense EMPTY_EXPENSE = new Expense();

    public Expense() {
        this.description = EMPTY_EXPENSE_DESCRIPTION;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        if (amount == 0) return EMPTY_EXPENSE_AMOUNT;
        return String.valueOf(amount);
    }
}