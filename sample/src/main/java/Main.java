import com.mickey305.foundation.v3.util.CollectionUtil;
import com.mickey305.foundation.v3.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IOException {
//        for (int i = 0; i < 1000; i++) {
//            Log.update("message is sample. ["+i+"]");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        List<String> list = new ArrayList<>();
        list.add("hoge");
        list = CollectionUtil.protectedList(list);
        list.forEach(Log::i);
        Log.i(list.size() + "");
    }


}
