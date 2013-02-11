package debt_advisor.factories;

import debt_advisor.utils.GraphDatabase;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

public class ReferenceNodeFactory {
    private static GraphDatabaseService graphDb = GraphDatabase.instance();

    public static void createUser() {
        Transaction transaction = graphDb.beginTx();
        Index<Node> referencesIndex = graphDb.index().forNodes("references");
        if (referencesIndex.get("reference", "user").getSingle() == null) {
            Node userReference = graphDb.createNode();
            referencesIndex.add(userReference, "reference", "user");
        }
        transaction.success();
        transaction.finish();
    }
}
