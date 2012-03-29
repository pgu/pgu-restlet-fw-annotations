package pgu.fw.resources.impl;

import org.restlet.resource.ServerResource;

import pgu.fw.rep.Rep;
import pgu.fw.resources.HasAddItem;

public abstract class EntitiesServerResource<R extends Rep> extends ServerResource implements HasAddItem<R> {
    //    implements HasAddItem<R> 

    private static int counter = 0;

    //    @Override
    public String addItem(final R rep) {
        System.out.println("code " + rep.getCode());
        return Integer.toString(counter++);
    }

}
