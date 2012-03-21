package pgu.modules.moduleB.rep;

import com.thoughtworks.xstream.XStream;

public class BRepTest {

    public static void main(final String[] args) {
        final XStream xstream = new XStream();
        xstream.processAnnotations(BRep.class);

        final BRep brep = new BRep();
        brep.setCode("999");
        brep.setName("b");

        System.out.println(xstream.toXML(brep));
    }

}
