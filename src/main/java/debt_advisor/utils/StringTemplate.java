package debt_advisor.utils;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;

public class StringTemplate {
    public static STGroup stGroup;

    public static void init() {
        if (stGroup == null) {
            stGroup = new STRawGroupDir("templates", '$', '$');
        }
    }

    public static ST template(String name) {
        return stGroup.getInstanceOf(name);
    }

}