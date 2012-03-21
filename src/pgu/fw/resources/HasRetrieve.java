package pgu.fw.resources;

import org.restlet.resource.Get;

import pgu.fw.rep.Rep;

public interface HasRetrieve<R extends Rep> {

    @Get
    R retrieve();

}
