/*
 * Copyright (c) 2019. K.Misaki
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

import com.mickey305.foundation.v3.lang.math.MathFun;
import com.mickey305.foundation.v3.util.CollectionUtil;
import com.mickey305.foundation.v3.util.collections.IStack;
import com.mickey305.foundation.v3.util.collections.StackAdapter;
import com.mickey305.foundation.v4.lang.math.Matrix;
import com.mickey305.foundation.v4.lang.math.SquareMatrix;
import com.mickey305.foundation.v4.lang.math.builder.AbstractMatrixBuilder;
import com.mickey305.foundation.v4.lang.math.builder.BuilderSquareMatrix;
import com.mickey305.foundation.v4.lang.math.factory.ElementInitializerFactory;
import com.mickey305.foundation.v4.lang.math.factory.OperationFactory;
import org.apache.commons.math3.fraction.BigFraction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main4Se7 {
    public static void main(String[] args) {
//        for (int i = 0; i < 1000; i++) {
//            Log.update("message is sample. ["+i+"]");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

      List<String> list = new ArrayList<String>();
      list.add("hoge");
      list = CollectionUtil.protectedList(list);
//        for(String str: list) {
//            Log.i(str);
//        }
      System.out.println(list.size() + "");
  
  
      SquareMatrix<BigFraction> matrix11 = new BuilderSquareMatrix<BigFraction>()
          .operator(OperationFactory.getFactory())
          .initializer(ElementInitializerFactory.getFactory())
          .cookbook(new AbstractMatrixBuilder.CookBook<BigFraction>() {
            @Override
            public BigFraction[][] tableDef() {
              return new BigFraction[][]{
                  {new BigFraction(51, 10), new BigFraction(25, 10), new BigFraction(12, 10)},
                  {new BigFraction(11, 10), new BigFraction(15, 10), new BigFraction(12, 10)},
                  {new BigFraction(23, 10), new BigFraction(25, 10), new BigFraction(62, 10)}
              };
//                    IElementInitializer<BigDecimal> ini = ElementInitializerFactory.bigDcmlIni();
//                    BigDecimal[][] table = ini.table(4, 4);
//                    ArrayUtil.fill(table, ini.one());
//                    return table;
            }
          })
          .build();
      Matrix<BigFraction> matrix13;
      matrix13 = matrix11.createInverseMatrix();
      System.out.println("matrix13:" + Arrays.deepToString(matrix13.getTable()));
      
      IStack<String> stack = new StackAdapter<String>(new ArrayDeque<String>());
      stack.push("test1");
      System.out.println(MathFun.fib(10));
    }


}
