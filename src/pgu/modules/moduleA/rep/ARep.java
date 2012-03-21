package pgu.modules.moduleA.rep;

import pgu.fw.rep.Rep;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ARep")
public class ARep extends Rep {

    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

}
