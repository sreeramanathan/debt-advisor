package debt_advisor;

import com.thoughtworks.inproctester.jetty.HttpAppTester;
import com.thoughtworks.inproctester.webdriver.InProcessHtmlUnitDriver;
import debt_advisor.utils.GraphDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static debt_advisor.neo4j.RelationshipType.USER;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CreateUsersTest {
    HttpAppTester connection;
    InProcessHtmlUnitDriver driver;
    GraphDatabaseService graphDb;
    Iterable<Node> createdNodes;
    Iterable<Relationship> createdRelationships;

    @Before
    public void setUp() throws Exception {
        connection = new HttpAppTester("src/main/webapp", "/");
        connection.start();
        driver = new InProcessHtmlUnitDriver(connection);
        GraphDatabase.init();
        graphDb = GraphDatabase.instance();
        registerDatabaseChangeTracker();
        setupUsers();
    }

    @After
    public void tearDown() throws Exception {
        deleteUsers();
        driver.close();
        connection.stop();
    }

    @Test
    public void shouldRenderCreateUsersPage() throws Exception {
        driver.get("http://localhost/user");

        assertThat(driver.findElement(By.id("users")), is(not(nullValue())));
        List<WebElement> users = driver.findElements(By.className("user"));
        assertThat(users.size(), is(5));
        assertTrue(users.get(0).getAttribute("class").contains("submitted"));
        assertThat(users.get(0).findElement(By.className("forename")).getAttribute("value"), is("Ramanathan"));
        assertThat(users.get(0).findElement(By.className("surname")).getAttribute("value"), is("Balakrishnan"));
        for (int count = 1; count < 5; count++) {
            assertTrue(users.get(count).getAttribute("class").contains("empty"));
            assertThat(users.get(count).findElement(By.className("forename")).getAttribute("value"), is("Forename"));
            assertThat(users.get(count).findElement(By.className("surname")).getAttribute("value"), is("Surname"));
        }
    }

    private void setupUsers() {
        Transaction transaction = graphDb.beginTx();
        Node user = graphDb.createNode();
        user.setProperty("forename", "Ramanathan");
        user.setProperty("surname", "Balakrishnan");
        Node userReference = graphDb.index().forNodes("references").get("reference", "user").getSingle();
        userReference.createRelationshipTo(user, USER);
        transaction.success();
        transaction.finish();
    }

    private void deleteUsers() {
        Transaction transaction = graphDb.beginTx();
        for (Relationship createdRelationship : createdRelationships) {
            createdRelationship.delete();
        }
        for (Node createdNode : createdNodes) {
            createdNode.delete();
        }
        transaction.success();
        transaction.finish();
    }

    private void registerDatabaseChangeTracker() {
        graphDb.registerTransactionEventHandler(new TransactionEventHandler<Object>() {
            public Object beforeCommit(TransactionData data) throws Exception {
                return null;
            }

            public void afterCommit(TransactionData data, Object state) {
                createdNodes = data.createdNodes();
                createdRelationships = data.createdRelationships();
            }

            public void afterRollback(TransactionData data, Object state) {
            }
        });
    }
}