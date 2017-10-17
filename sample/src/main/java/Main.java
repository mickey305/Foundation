import com.mickey305.foundation.v3.util.pattern.Component;
import com.mickey305.foundation.v3.util.pattern.Composite;
import com.mickey305.foundation.v3.util.pattern.Leaf;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Composite<String> cmp1 = new Composite<>("cmp1");
        Composite<String> cmp2 = new Composite<>("cmp2");
        Composite<String> cmp3 = new Composite<>("cmp3", HashSet::new);
        Composite<String> cmp4 = new Composite<>("cmp4", HashSet::new);
        Composite<String> cmp5 = new Composite<>("cmp5");
        Composite<String> cmp6 = new Composite<>("cmp6", HashSet::new);
        Component<String> cmp7 = new Composite<>("cmp7", HashSet::new);
        Component<String> lef1 = new Leaf<>("leaf1");
        Component<String> lef2 = new Leaf<>("leaf2");
        Component<String> lef3 = new Leaf<>("leaf3");
        Component<String> lef4 = new Leaf<>("leaf4");
        Component<String> lef5 = new Leaf<>("leaf5");
        Component<String> lef6 = new Leaf<>("leaf6");
        Component<String> lef7 = new Leaf<>("leaf7");

        cmp1.add(cmp2);
        cmp1.add(cmp3);
        cmp1.add(cmp4);
        cmp4.add(cmp5);
        cmp4.add(cmp6);

        cmp1.add(lef3);
        cmp1.add(lef7);
        cmp4.add(lef1);
        cmp4.add(lef2);
        cmp6.add(lef4);
        cmp6.add(lef5);
        cmp6.add(lef6);

        System.out.println(lef6.getRoot().getObject());
        cmp6.getChildren().forEach(child -> System.out.println(child.getObject()));

        System.out.println("F: " + cmp5.belongsTo(cmp5));
        System.out.println("T: " + cmp5.belongsTo(cmp1));
        System.out.println("T: " + cmp5.belongsTo(cmp4));
        System.out.println("F: " + cmp5.belongsTo(cmp3));
        System.out.println("T: " + lef5.belongsTo(cmp4));
        System.out.println("F: " + lef7.belongsTo(cmp4));

        System.out.println("F: " + cmp1.contains(cmp1));
        System.out.println("T: " + cmp1.contains(cmp5));
        System.out.println("T: " + cmp4.contains(lef1));
        System.out.println("T: " + cmp4.contains(lef5));
        System.out.println("F: " + cmp4.contains(lef7));
        System.out.println("F: " + cmp6.contains(lef2));
    }
}
