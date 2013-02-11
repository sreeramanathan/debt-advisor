package debt_advisor.utils;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import static java.lang.Runtime.getRuntime;

public class GraphDatabase {
    private static GraphDatabaseService graphDb;

    public static GraphDatabaseService instance() {
        if (graphDb == null) {
            graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("target/db");
            getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    graphDb.shutdown();
                }
            });
        }
        return graphDb;
    }
}
