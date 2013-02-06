package tour_planner;

import com.thoughtworks.inproctester.jetty.HttpAppTester;
import com.thoughtworks.inproctester.webdriver.InProcessHtmlUnitDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class CreateUsersTest {
    HttpAppTester connection;
    InProcessHtmlUnitDriver driver;

    @Before
    public void setUp() throws Exception {
        connection = new HttpAppTester("src/main/webapp", "/");
        connection.start();
        driver = new InProcessHtmlUnitDriver(connection);
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
    }
}