package pgu.fw.resources;

import org.restlet.resource.Post;

import pgu.fw.rep.Rep;

public interface HasAddItem<R extends Rep> {

    @Post
    String addItem(R rep);

}
