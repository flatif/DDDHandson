package dddhandson.domain.support;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainEntityState {

    private static final String MUTATOR_METHOD_NAME = "when";

    private static Map<String, Method> mutatorMethods =
            new HashMap<String, Method>();

    private int unmutatedVersion;

    public int mutatedVersion() {
        return this.unmutatedVersion() + 1;
    }

    public int unmutatedVersion() {
        return this.unmutatedVersion;
    }

    


}
