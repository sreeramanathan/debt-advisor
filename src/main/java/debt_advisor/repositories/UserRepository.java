package debt_advisor.repositories;

import debt_advisor.models.User;
import debt_advisor.utils.GraphDatabase;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;

import java.util.ArrayList;
import java.util.List;

import static debt_advisor.neo4j.RelationshipType.USER;
import static org.neo4j.graphdb.Direction.INCOMING;
import static org.neo4j.graphdb.Direction.OUTGOING;

public class UserRepository {
    public List<User> fetchAll() {
        List<User> users = new ArrayList<User>();
        GraphDatabaseService graphDb = GraphDatabase.instance();
        Node userReference = graphDb.index().forNodes("references").get("reference", "user").getSingle();
        for (Relationship relationship : userReference.getRelationships(USER, OUTGOING)) {
            Node node = relationship.getEndNode();
            users.add(new User((String) node.getProperty("forename"), (String) node.getProperty("surname")));
        }
        return users;
    }

    public void create(User user) {
        GraphDatabaseService graphDb = GraphDatabase.instance();
        Node userNode = graphDb.createNode();
        Index<Node> userIndex = graphDb.index().forNodes("user");
        userNode.setProperty("forename", user.getForename());
        userNode.setProperty("surname", user.getSurname());
        userIndex.add(userNode, "username", user.getForename() + user.getSurname());
        Node userReference = graphDb.index().forNodes("references").get("reference", "user").getSingle();
        userReference.createRelationshipTo(userNode, USER);
    }

    public void delete(User user) {
        GraphDatabaseService graphDb = GraphDatabase.instance();
        Index<Node> userIndex = graphDb.index().forNodes("user");
        Node userNode = userIndex.get("username", user.getForename() + user.getSurname()).getSingle();
        userNode.getSingleRelationship(USER, INCOMING).delete();
        userIndex.remove(userNode);
        userNode.delete();
    }
}