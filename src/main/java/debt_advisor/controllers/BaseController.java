package debt_advisor.controllers;

import javax.servlet.ServletConfig;

public class BaseController {
    public void init(ServletConfig servletConfig) {
        init();
    }

    protected void init() {
    }

}
