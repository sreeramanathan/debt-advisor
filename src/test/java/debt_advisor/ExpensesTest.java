package debt_advisor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ExpensesTest extends InProcessFunctionalTest {
    @Before
    public void setUp() {
        driver.get("http://localhost/user");
        List<WebElement> users = driver.findElements(By.className("user"));
        WebElement firstUser = users.get(0);
        firstUser.findElement(By.className("forename")).sendKeys("Ramanathan");
        firstUser.findElement(By.className("surname")).sendKeys("Balakrishnan");
        firstUser.findElement(By.className("action")).click();
    }

    @After
    public void tearDown() {
        driver.get("http://localhost/user");
        driver.findElements(By.className("user")).get(0).findElement(By.className("action")).click();
    }

    @Test
    public void shouldViewAddAndDeleteExpenses() throws Exception {
        driver.get("http://localhost/expense");
        List<WebElement> expenses = driver.findElements(By.className("expense"));
        assertThat(expenses.size(), is(5));
        for (int count = 0; count < 5; count++) {
            assertEmpty(expenses.get(count));
        }
    }

    private void assertEmpty(WebElement expense) {
        assertTrue(expense.getAttribute("class").contains("empty"));
        assertThat(expense.findElement(By.className("description")).getAttribute("value"), is("Description"));
        assertThat(expense.findElement(By.className("amount")).getAttribute("value"), is("Amount"));
    }
}