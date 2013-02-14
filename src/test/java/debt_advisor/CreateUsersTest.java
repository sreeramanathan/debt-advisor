package debt_advisor;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CreateUsersTest extends InProcessFunctionalTest {
    @Test
    public void shouldRenderCreateUsersPage() throws Exception {
        driver.get("http://localhost/user");
        assertThat(driver.findElement(By.id("users")), is(not(nullValue())));
        List<WebElement> users = driver.findElements(By.className("user"));
        assertThat(users.size(), is(5));
        for (int count = 0; count < 5; count++) {
            assertEmpty(users.get(count));
        }

        WebElement firstUser = users.get(0);
        firstUser.findElement(By.className("forename")).sendKeys("Ramanathan");
        firstUser.findElement(By.className("surname")).sendKeys("Balakrishnan");
        firstUser.findElement(By.className("action")).click();
        driver.get("http://localhost/user");
        users = driver.findElements(By.className("user"));
        assertThat(users.size(), is(5));
        firstUser = users.get(0);
        assertTrue(firstUser.getAttribute("class").contains("submitted"));
        assertThat(firstUser.findElement(By.className("forename")).getAttribute("value"), is("Ramanathan"));
        assertThat(firstUser.findElement(By.className("surname")).getAttribute("value"), is("Balakrishnan"));
        for (int count = 1; count < 5; count++) {
            assertEmpty(users.get(count));
        }

        firstUser.findElement(By.className("action")).click();
        driver.get("http://localhost/user");
        users = driver.findElements(By.className("user"));
        assertThat(users.size(), is(5));
        for (int count = 0; count < 5; count++) {
            assertEmpty(users.get(count));
        }
    }

    private void assertEmpty(WebElement user) {
        assertTrue(user.getAttribute("class").contains("empty"));
        assertThat(user.findElement(By.className("forename")).getAttribute("value"), is("Forename"));
        assertThat(user.findElement(By.className("surname")).getAttribute("value"), is("Surname"));
    }
}