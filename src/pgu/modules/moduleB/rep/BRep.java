package pgu.modules.moduleB.rep;

import pgu.fw.rep.Rep;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("BRep")
public class BRep extends Rep {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
