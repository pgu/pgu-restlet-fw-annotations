package pgu.modules.moduleA.resources;

import org.restlet.resource.Get;

import pgu.modules.moduleA.rep.ARep;

public interface EntityAResource {

    @Get
    ARep retrieve();

}
