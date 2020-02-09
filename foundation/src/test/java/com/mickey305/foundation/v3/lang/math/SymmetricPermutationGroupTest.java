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

package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SymmetricPermutationGroupTest {
  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testCase_01_01() throws Exception {
    Integer[][] t1 = {
        {1, 2, 3},
        {2, 3, 1}
    };
//        Integer[][] t2 = {
//                {1, 2, 3},
//                {3, 2, 1}
//        };
    Integer[][] t2 = {
        {1, 3},
        {3, 1}
    };


    // 1 2 3
    // 1 3 2
    //
    // 3 1 2
    // 3 2 1
    //
    SymmetricPermutationGroup p1 = new SymmetricPermutationGroup(t1);
    SymmetricPermutationGroup p2 = new SymmetricPermutationGroup(t2);
    SymmetricPermutationGroup pr = SymmetricPermutationGroup.multi(p1, p2).compact();

    Log.i(ToStringBuilder.reflectionToString(pr.getTable()));
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testCase_01_02() throws Exception {
//        Integer[][] t1 = {
//                {1, 2, 3, 4, 5, 6},
//                {3, 5, 6, 1, 2, 4}
//        };
//        Integer[][] t1 = {
//                {1, 2, 3, 4, 5},
//                {4, 3, 2, 5, 1}
//        };
    Integer[][] t1 = {
        {1, 2, 3},
        {3, 1, 2}
    };

    SymmetricPermutationGroup p1 = new SymmetricPermutationGroup(t1);
    List<? extends SymmetricCycleGroup> plist = p1.convertCycle();
    List<? extends SymmetricCycleGroup> plist2 = p1.convertTransPosition();

    Log.i(SymmetricPermutationGroup.sgn(p1) + "");

    Log.i("cycle");
    for (SymmetricCycleGroup e : plist)
      Log.i(ToStringBuilder.reflectionToString(e.getTable()));
    Log.i("trans position");
    for (SymmetricCycleGroup e : plist2)
      Log.i(ToStringBuilder.reflectionToString(e.getTable()));
  }
}