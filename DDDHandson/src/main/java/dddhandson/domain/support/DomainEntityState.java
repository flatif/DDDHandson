package dddhandson.domain.support;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DomainEntityState<ID extends Serializable> {

    private static final String MUTATOR_METHOD_NAME = "when";

    private static Map<String, Method> mutatorMethods =
            new HashMap<String, Method>();

    private int unmutatedVersion;

    public abstract ID identity();

    public int mutatedVersion() {
        return this.unmutatedVersion() + 1;
    }

    public int unmutatedVersion() {
        return this.unmutatedVersion;
    }

    public DomainEntityState() {
        this.unmutatedVersion = 0;
    }

    protected void loadFromHistory(EventStream eventStream) {

        for (DomainEvent event : eventStream.events()) {
            this.mutate(event);
        }

        this.unmutatedVersion = eventStream.version();
    }



    /*
     *  PLUMBING, in java we dont have @dynamic
     */

    protected void mutate(DomainEvent aDomainEvent) {

        Class<? extends DomainEntityState> rootType = this.getClass();

        Class<? extends DomainEvent> eventType = aDomainEvent.getClass();

        String key = rootType.getName() + ":" + eventType.getName();

        Method mutatorMethod = mutatorMethod(key, rootType, eventType);

        try {
            mutatorMethod.invoke(this, aDomainEvent);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Method "
                            + MUTATOR_METHOD_NAME
                            + "("
                            + eventType.getSimpleName()
                            + ") failed. See cause: "
                            + e.getMessage(),
                    e);

        }
    }

    private Method mutatorMethod(
            String aKey,
            Class<? extends DomainEntityState> aRootType,
            Class<? extends DomainEvent> anEventType) {

        Method method = mutatorMethods.get(aKey);

        if (method != null) {
            return method;
        }

        synchronized (mutatorMethods) {
            try {
                method = aRootType.getDeclaredMethod(
                        MUTATOR_METHOD_NAME,
                        anEventType);

                method.setAccessible(true);

                mutatorMethods.put(aKey, method);

                return method;

            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "I do not understand "
                                + MUTATOR_METHOD_NAME
                                + "("
                                + anEventType.getSimpleName()
                                + ") because: "
                                + e.getClass().getSimpleName() + ">>>" + e.getMessage(),
                        e);
            }
        }
    }



}
