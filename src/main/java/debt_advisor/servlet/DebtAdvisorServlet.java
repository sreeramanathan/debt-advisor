package debt_advisor.servlet;

import debt_advisor.utils.GraphDatabase;
import debt_advisor.utils.StringTemplate;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.tuckey.web.filters.urlrewrite.UrlRewriteServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DebtAdvisorServlet extends UrlRewriteServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        GraphDatabase.init();
        StringTemplate.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GraphDatabaseService graphDb = GraphDatabase.instance();
        Transaction transaction = graphDb.beginTx();
        try {
            super.doGet(req, resp);
        } finally {
            transaction.success();
            transaction.finish();
        }
    }
}
