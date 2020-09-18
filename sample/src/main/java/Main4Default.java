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

import bean.EscapeRejectSample01;
import com.mickey305.foundation.v3.compat.exception.wrap.BeanValidationException;
import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v3.validation.ValidationInvoker;

import java.io.IOException;

public class Main4Default {
    public static final String EscapeData_Reset = "\u001b[0m";
    public static final String EscapeData_BackgroundMagenta = "\u001b[45m";
    
    public static void main(String[] args) throws NoSuchMethodException, IOException {
//        for (int i = 0; i < 1000; i++) {
//            Log.update("message is sample. ["+i+"]");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        // CollectionUtil - sample
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        List<String> list = new ArrayList<>();
//        list.add("hoge");
//        list = CollectionUtil.protectedList(list);
//        Log.i(list.size() + "");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Bean Validation - sample
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        EscapeRejectSample01 sampleObj = new EscapeRejectSample01();

        sampleObj.setEscapeRejectData("test data");
        sampleObj.setNotnullData("test data");
        ValidationInvoker.validateConstructor(sampleObj);

        sampleObj.setEscapeRejectData("test data");
        sampleObj.setNotnullData(null);
        try {
            ValidationInvoker.validateConstructor(sampleObj);
        } catch (BeanValidationException e) {
            Log.d(e);
        }

        sampleObj.setEscapeRejectData(EscapeData_BackgroundMagenta + "test data" + EscapeData_Reset);
        sampleObj.setNotnullData("test data");
        try {
            ValidationInvoker.validateConstructor(sampleObj);
        } catch (BeanValidationException e) {
            Log.d(e);
        }
    }

}
