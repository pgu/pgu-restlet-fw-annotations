package pgu.modules.moduleA.resources;

import org.restlet.resource.Post;

import pgu.modules.moduleA.rep.ARep;

public interface EntitiesAResource {

    @Post
    String addItem(ARep rep);

}
