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

package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MathFunTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    final List<Pair<Integer, String>> data = new ArrayList<>();
    data.add(Pair.of(0,  "0000000000000000000000000000000"));
    data.add(Pair.of(1,  "0000000000000000000000000000001"));
    data.add(Pair.of(2,  "0000000000000000000000000000001"));
    data.add(Pair.of(3,  "0000000000000000000000000000002"));
    data.add(Pair.of(4,  "0000000000000000000000000000003"));
    data.add(Pair.of(5,  "0000000000000000000000000000005"));
    data.add(Pair.of(6,  "0000000000000000000000000000008"));
    data.add(Pair.of(7,  "0000000000000000000000000000013"));
    data.add(Pair.of(8,  "0000000000000000000000000000021"));
    data.add(Pair.of(9,  "0000000000000000000000000000034"));
    data.add(Pair.of(10, "0000000000000000000000000000055"));
    data.add(Pair.of(11, "0000000000000000000000000000089"));
    data.add(Pair.of(12, "0000000000000000000000000000144"));
    data.add(Pair.of(13, "0000000000000000000000000000233"));
    data.add(Pair.of(14, "0000000000000000000000000000377"));
    data.add(Pair.of(15, "0000000000000000000000000000610"));
    data.add(Pair.of(16, "0000000000000000000000000000987"));
    data.add(Pair.of(17, "0000000000000000000000000001597"));
    data.add(Pair.of(18, "0000000000000000000000000002584"));
    data.add(Pair.of(19, "0000000000000000000000000004181"));
    data.add(Pair.of(20, "0000000000000000000000000006765"));
    data.add(Pair.of(21, "0000000000000000000000000010946"));
    data.add(Pair.of(22, "0000000000000000000000000017711"));
    data.add(Pair.of(23, "0000000000000000000000000028657"));
    data.add(Pair.of(24, "0000000000000000000000000046368"));
    data.add(Pair.of(25, "0000000000000000000000000075025"));
    data.add(Pair.of(26, "0000000000000000000000000121393"));
    data.add(Pair.of(27, "0000000000000000000000000196418"));
    data.add(Pair.of(28, "0000000000000000000000000317811"));
    data.add(Pair.of(29, "0000000000000000000000000514229"));
    data.add(Pair.of(30, "0000000000000000000000000832040"));
    data.add(Pair.of(31, "0000000000000000000000001346269"));
    data.add(Pair.of(32, "0000000000000000000000002178309"));
    data.add(Pair.of(33, "0000000000000000000000003524578"));
    data.add(Pair.of(34, "0000000000000000000000005702887"));
    data.add(Pair.of(35, "0000000000000000000000009227465"));
    data.add(Pair.of(36, "0000000000000000000000014930352"));
    data.add(Pair.of(37, "0000000000000000000000024157817"));
    data.add(Pair.of(38, "0000000000000000000000039088169"));
    data.add(Pair.of(39, "0000000000000000000000063245986"));
    data.add(Pair.of(40, "0000000000000000000000102334155"));
    data.add(Pair.of(41, "0000000000000000000000165580141"));
    data.add(Pair.of(42, "0000000000000000000000267914296"));
    data.add(Pair.of(43, "0000000000000000000000433494437"));
    data.add(Pair.of(44, "0000000000000000000000701408733"));
    data.add(Pair.of(45, "0000000000000000000001134903170"));
    data.add(Pair.of(46, "0000000000000000000001836311903"));
    data.add(Pair.of(47, "0000000000000000000002971215073"));
    data.add(Pair.of(48, "0000000000000000000004807526976"));
    data.add(Pair.of(49, "0000000000000000000007778742049"));
    data.add(Pair.of(50, "0000000000000000000012586269025"));
    data.add(Pair.of(51, "0000000000000000000020365011074"));
    data.add(Pair.of(52, "0000000000000000000032951280099"));
    data.add(Pair.of(53, "0000000000000000000053316291173"));
    data.add(Pair.of(54, "0000000000000000000086267571272"));
    data.add(Pair.of(55, "0000000000000000000139583862445"));
    data.add(Pair.of(56, "0000000000000000000225851433717"));
    data.add(Pair.of(57, "0000000000000000000365435296162"));
    data.add(Pair.of(58, "0000000000000000000591286729879"));
    data.add(Pair.of(59, "0000000000000000000956722026041"));
    data.add(Pair.of(60, "0000000000000000001548008755920"));
    data.add(Pair.of(61, "0000000000000000002504730781961"));
    data.add(Pair.of(62, "0000000000000000004052739537881"));
    data.add(Pair.of(63, "0000000000000000006557470319842"));
    data.add(Pair.of(64, "0000000000000000010610209857723"));
    data.add(Pair.of(65, "0000000000000000017167680177565"));
    data.add(Pair.of(66, "0000000000000000027777890035288"));
    data.add(Pair.of(67, "0000000000000000044945570212853"));
    data.add(Pair.of(68, "0000000000000000072723460248141"));
    data.add(Pair.of(69, "0000000000000000117669030460994"));
    data.add(Pair.of(70, "0000000000000000190392490709135"));
    data.add(Pair.of(71, "0000000000000000308061521170129"));
    data.add(Pair.of(72, "0000000000000000498454011879264"));
    data.add(Pair.of(73, "0000000000000000806515533049393"));
    data.add(Pair.of(74, "0000000000000001304969544928657"));
    data.add(Pair.of(75, "0000000000000002111485077978050"));
    data.add(Pair.of(76, "0000000000000003416454622906707"));
    data.add(Pair.of(77, "0000000000000005527939700884757"));
    data.add(Pair.of(78, "0000000000000008944394323791464"));
    data.add(Pair.of(79, "0000000000000014472334024676221"));
    data.add(Pair.of(80, "0000000000000023416728348467685"));
    data.add(Pair.of(81, "0000000000000037889062373143906"));
    data.add(Pair.of(82, "0000000000000061305790721611591"));
    data.add(Pair.of(83, "0000000000000099194853094755497"));
    data.add(Pair.of(84, "0000000000000160500643816367088"));
    data.add(Pair.of(85, "0000000000000259695496911122585"));
    data.add(Pair.of(86, "0000000000000420196140727489673"));
    data.add(Pair.of(87, "0000000000000679891637638612258"));
    data.add(Pair.of(88, "0000000000001100087778366101931"));
    data.add(Pair.of(89, "0000000000001779979416004714189"));
    data.add(Pair.of(90, "0000000000002880067194370816120"));
    data.add(Pair.of(91, "0000000000004660046610375530309"));
    data.add(Pair.of(92, "0000000000007540113804746346429"));
    data.add(Pair.of(93, "0000000000012200160415121876738"));
    data.add(Pair.of(94, "0000000000019740274219868223167"));
    data.add(Pair.of(95, "0000000000031940434634990099905"));
    data.add(Pair.of(96, "0000000000051680708854858323072"));
    data.add(Pair.of(97, "0000000000083621143489848422977"));
    data.add(Pair.of(98, "0000000000135301852344706746049"));
    data.add(Pair.of(99, "0000000000218922995834555169026"));
    data.add(Pair.of(100,"0000000000354224848179261915075"));
    
    for (Pair<Integer, String> pair : data) {
      Assert.assertEquals(new BigInteger(pair.getRight()), MathFun.fib(pair.getLeft()));
    }
  }
  
  @Test
  public void testCase_01_02() throws Exception {
    // big data running
    final int index = 5_000;
    BigInteger result = MathFun.fib(index+1);
    Log.i("n[" + String.format("%07d", index + 1) + "] " + result);
  }
  
  @Test
  public void testCase_01_03() throws Exception {
    // big data running
    BigInteger result = null;
    final int index = 10_000;
    int lastIndex = 0;
    for (int i = 0; i < index; i++) {
      result = MathFun.fib(i+1);
      //Log.i("n[" + String.format("%07d", i + 1) + "] " + result);
      lastIndex = i;
    }
    Log.i("n[" + String.format("%07d", lastIndex + 1) + "] " + result);
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    // big data running
    final int index = 50;
    long result;
    try {
      result = MathFun.Primitive.fib(index+1);
    } catch (Exception e) {
      Log.d(e.getMessage());
      return;
    }
    Log.i("n[" + String.format("%07d", index + 1) + "] " + result);
  }
  
  @Test
  public void testCase_02_02() throws Exception {
    // big data running
    long result = 0;
    final int index = 92;
    int lastIndex = 0;
    for (int i = 0; i < index; i++) {
      try {
        result = MathFun.Primitive.fib(i + 1);
      } catch (Exception e) {
        Log.d(e.getMessage());
        return;
      }
      //Log.i("n[" + String.format("%07d", i + 1) + "] " + result);
      lastIndex = i;
    }
    Log.i("n[" + String.format("%07d", lastIndex + 1) + "] " + result);
  }
}