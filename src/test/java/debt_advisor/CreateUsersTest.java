package debt_advisor;

import com.thoughtworks.inproctester.jetty.HttpAppTester;
import com.thoughtworks.inproctester.webdriver.InProcessHtmlUnitDriver;
import debt_advisor.models.User;
import debt_advisor.utils.GraphDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.cypher.internal.symbols.RelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.tooling.GlobalGraphOperations;
import org.openqa.selenium.By;

import java.util.List;

import static debt_advisor.models.User.RelTypes.USER;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

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
        assertThat(driver.findElement(By.className("forename")).getAttribute("value"), is("Ramanathan"));
        assertThat(driver.findElement(By.className("surname")).getAttribute("value"), is("Balakrishnan"));
    }

    private void setupUsers() {
        Transaction transaction = graphDb.beginTx();
        Node userReference = graphDb.createNode();
        Node user = graphDb.createNode();
        user.setProperty("forename", "Ramanathan");
        user.setProperty("surname", "Balakrishnan");
        userReference.createRelationshipTo(user, USER);
        Index<Node> referencesIndex = graphDb.index().forNodes("references");
        referencesIndex.add(userReference, "reference", "user");
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