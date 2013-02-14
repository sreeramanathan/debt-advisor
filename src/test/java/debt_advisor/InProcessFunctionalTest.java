package debt_advisor;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.thoughtworks.inproctester.jetty.HttpAppTester;
import com.thoughtworks.inproctester.webdriver.InProcessHtmlUnitDriver;
import debt_advisor.utils.GraphDatabase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.event.TransactionEventHandler;

import static java.lang.Runtime.getRuntime;

public class InProcessFunctionalTest {
    private static HttpAppTester connection;
    protected static InProcessHtmlUnitDriver driver;

    @BeforeClass
    public static void init() throws Exception {
        if (connection == null) {
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
            getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    driver.close();
                    connection.stop();
                }
            });
        }
    }
}
