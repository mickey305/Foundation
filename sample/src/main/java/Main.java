import com.mickey305.foundation.v3.ansi.code.AnsiStringBuilder;
import com.mickey305.foundation.v3.ansi.code.AnsiStringBuilder.Without;
import com.mickey305.foundation.v3.ansi.code.Escape;
import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v3.util.pattern.Component;
import com.mickey305.foundation.v3.util.pattern.Composite;
import com.mickey305.foundation.v3.util.pattern.Leaf;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
//        Composite<String> cmp1 = new Composite<>("cmp1");
//        Composite<String> cmp2 = new Composite<>("cmp2");
//        Composite<String> cmp3 = new Composite<>("cmp3", HashSet::new);
//        Composite<String> cmp4 = new Composite<>("cmp4", HashSet::new);
//        Composite<String> cmp5 = new Composite<>("cmp5");
//        Composite<String> cmp6 = new Composite<>("cmp6", HashSet::new);
//        Component<String> cmp7 = new Composite<>("cmp7", HashSet::new);
//        Component<String> lef1 = new Leaf<>("leaf1");
//        Component<String> lef2 = new Leaf<>("leaf2");
//        Component<String> lef3 = new Leaf<>("leaf3");
//        Component<String> lef4 = new Leaf<>("leaf4");
//        Component<String> lef5 = new Leaf<>("leaf5");
//        Component<String> lef6 = new Leaf<>("leaf6");
//        Component<String> lef7 = new Leaf<>("leaf7");
//
//        cmp1.add(cmp2);
//        cmp1.add(cmp3);
//        cmp1.add(cmp4);
//        cmp4.add(cmp5);
//        cmp4.add(cmp6);
//
//        cmp1.add(lef3);
//        cmp1.add(lef7);
//        cmp4.add(lef1);
//        cmp4.add(lef2);
//        cmp6.add(lef4);
//        cmp6.add(lef5);
//        cmp6.add(lef6);
//
//        System.out.println(lef6.getRoot().getObject());
//        cmp6.getChildren().forEach(child -> System.out.println(child.getObject()));
//
//        System.out.println("F: " + cmp5.belongsTo(cmp5));
//        System.out.println("T: " + cmp5.belongsTo(cmp1));
//        System.out.println("T: " + cmp5.belongsTo(cmp4));
//        System.out.println("F: " + cmp5.belongsTo(cmp3));
//        System.out.println("T: " + lef5.belongsTo(cmp4));
//        System.out.println("F: " + lef7.belongsTo(cmp4));
//
//        System.out.println("F: " + cmp1.contains(cmp1));
//        System.out.println("T: " + cmp1.contains(cmp5));
//        System.out.println("T: " + cmp4.contains(lef1));
//        System.out.println("T: " + cmp4.contains(lef5));
//        System.out.println("F: " + cmp4.contains(lef7));
//        Log.i("F: " + cmp6.contains(lef2));


        for (int a = 0; a < 10; a++) {
            final int loop = 100000;

            Log.i("// count " + a);

            long start2 = System.currentTimeMillis();
            StringBuilder tmp2 = new StringBuilder();
            for (int j = 0; j < loop; j++) { tmp2.append("[").append(j).append("]"); }
//            tmp2.toString();
            long end2 = System.currentTimeMillis();
            Log.i((end2 - start2) + "(ms)");


            long start3 = System.currentTimeMillis();
            AnsiStringBuilder tmp3 = new AnsiStringBuilder();
            for (int k = 0; k < loop; k++) { tmp3.append("[").append(k).append("]"); }
//            tmp3.toString();
            long end3 = System.currentTimeMillis();
            Log.i((end3 - start3) + "(ms)");


            long start1 = System.currentTimeMillis();
            String tmp = "";
            for (int i = 0; i < loop; i++) { tmp += "[" + i + "]"; }
//            tmp.toString();
            long end1 = System.currentTimeMillis();
            Log.i((end1 - start1) + "(ms)");
        }


//        AnsiStringBuilder sb = new AnsiStringBuilder();
//        sb.append(Escape.Red);
//        sb.append(Escape.Yellow);
//        sb.append(Escape.Magenta);
//        sb.append(Escape.Underline);
//        sb.append(Escape.BkgYellow);
//        sb.append("\u001b");
//        sb.append("[33m");
//        sb.append("japanese food");
//        sb.append("寿司");
//        sb.append(Escape.Magenta);
//        sb.append(Escape.Underline);
//        sb.append(Escape.BkgBlue);
//        sb.delete(Escape.Magenta);
//        Log.i(sb.toString().length()+"");
//        Log.i(sb.toString(Without.EscapeCode).length()+"");
    }
}
