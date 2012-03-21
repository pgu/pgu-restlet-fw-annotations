package pgu.fw.resources.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.restlet.resource.ServerResource;

import pgu.fw.rep.Rep;
import pgu.fw.resources.HasRetrieve;

public abstract class EntityServerResource<R extends Rep> extends ServerResource implements HasRetrieve<R> {

    private static final String ID = "id";

    @SuppressWarnings("unchecked")
    @Override
    public R retrieve() {
        final String id = (String) getRequestAttributes().get(ID);
        System.out.println(id);

        final Type type = getClass().getGenericSuperclass();
        final ParameterizedType paramType = (ParameterizedType) type;
        final Class<R> clazz = (Class<R>) paramType.getActualTypeArguments()[0];

        R r = null;
        try {
            r = clazz.newInstance();
        } catch (final InstantiationException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        r.setCode(id);
        return r;
    }

}
