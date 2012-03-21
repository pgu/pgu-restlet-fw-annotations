package pgu.modules.moduleA.rep;

import com.thoughtworks.xstream.XStream;

public class ARepTest {

    public static void main(final String[] args) {
        final XStream xstream = new XStream();
        xstream.processAnnotations(ARep.class);

        final ARep arep = new ARep();
        arep.setCode("999");
        arep.setReference("qsdf");

        System.out.println(xstream.toXML(arep));
    }

}
