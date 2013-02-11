package debt_advisor.servlet;

import debt_advisor.utils.GraphDatabase;
import debt_advisor.utils.StringTemplate;
import org.tuckey.web.filters.urlrewrite.UrlRewriteServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class DebtAdvisorServlet extends UrlRewriteServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        GraphDatabase.init();
        StringTemplate.init();
    }
}
