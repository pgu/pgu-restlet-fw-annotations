package pgu;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

import pgu.modules.moduleA.resources.impl.EntitiesAServerResource;
import pgu.modules.moduleA.resources.impl.EntityAServerResource;
import pgu.modules.moduleB.resources.impl.EntitiesBServerResource;
import pgu.modules.moduleB.resources.impl.EntityBServerResource;

public class Run {

    public static void main(final String[] args) throws Exception {

        final Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8183);

        final Application application = new Application(component.getContext().createChildContext()) {

            @Override
            public Restlet createInboundRoot() {
                final Router router = new Router(getContext());

                // http://localhost:8183/entitiesA/
                router.attach("/entitiesA/{id}", EntityAServerResource.class);
                router.attach("/entitiesA/", EntitiesAServerResource.class);

                // http://localhost:8183/entitiesB/
                router.attach("/entitiesB/{id}", EntityBServerResource.class);
                router.attach("/entitiesB/", EntitiesBServerResource.class);
                return router;
            }

        };

        // PguResource.class
        component.getDefaultHost().attach(application);
        component.start();

    }

}
