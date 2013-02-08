package debt_advisor.utils;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;

public class StringTemplate {
    public static STGroup stGroup;

    public static ST template(String name) {
//        if (stGroup == null) {
            stGroup = stGroup();
//        }
        return stGroup.getInstanceOf(name);
    }

    private static STGroup stGroup() {
        return new STRawGroupDir("templates", '$', '$');
    }
}