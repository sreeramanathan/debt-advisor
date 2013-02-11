package debt_advisor.utils;

import debt_advisor.factories.ReferenceNodeFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import static java.lang.Runtime.getRuntime;

public class GraphDatabase {
    private static GraphDatabaseService graphDb;

    public static void init() {
        if (graphDb == null) {
            graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("target/db");
            getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    graphDb.shutdown();
                }
            });
            ReferenceNodeFactory.createUser();
        }
    }

    public static GraphDatabaseService instance() {
        return graphDb;
    }
}
