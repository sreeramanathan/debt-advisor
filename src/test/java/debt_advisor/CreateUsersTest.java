package debt_advisor;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.thoughtworks.inproctester.jetty.HttpAppTester;
import com.thoughtworks.inproctester.webdriver.InProcessHtmlUnitDriver;
import debt_advisor.utils.GraphDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CreateUsersTest {
    HttpAppTester connection;
    InProcessHtmlUnitDriver driver;

    @Before
    public void setUp() throws Exception {
        connection = new HttpAppTester("src/main/webapp", "/");
        connection.start();
        driver = new InProcessHtmlUnitDriver(connection) {
            @Override
            protected WebClient modifyWebClient(WebClient client) {
                client.setAjaxController(new NicelyResynchronizingAjaxController());
                return client;
            }
        };
        driver.setJavascriptEnabled(true);
        GraphDatabase.init();
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        connection.stop();
    }

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