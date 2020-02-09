/*
 * Copyright (c) 2017 - 2020 K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
