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
    final List<Pair<Long, String>> data = new ArrayList<>();
    data.add(Pair.of(0L,  "0000000000000000000000000000000"));
    data.add(Pair.of(1L,  "0000000000000000000000000000001"));
    data.add(Pair.of(2L,  "0000000000000000000000000000001"));
    data.add(Pair.of(3L,  "0000000000000000000000000000002"));
    data.add(Pair.of(4L,  "0000000000000000000000000000003"));
    data.add(Pair.of(5L,  "0000000000000000000000000000005"));
    data.add(Pair.of(6L,  "0000000000000000000000000000008"));
    data.add(Pair.of(7L,  "0000000000000000000000000000013"));
    data.add(Pair.of(8L,  "0000000000000000000000000000021"));
    data.add(Pair.of(9L,  "0000000000000000000000000000034"));
    data.add(Pair.of(10L, "0000000000000000000000000000055"));
    data.add(Pair.of(11L, "0000000000000000000000000000089"));
    data.add(Pair.of(12L, "0000000000000000000000000000144"));
    data.add(Pair.of(13L, "0000000000000000000000000000233"));
    data.add(Pair.of(14L, "0000000000000000000000000000377"));
    data.add(Pair.of(15L, "0000000000000000000000000000610"));
    data.add(Pair.of(16L, "0000000000000000000000000000987"));
    data.add(Pair.of(17L, "0000000000000000000000000001597"));
    data.add(Pair.of(18L, "0000000000000000000000000002584"));
    data.add(Pair.of(19L, "0000000000000000000000000004181"));
    data.add(Pair.of(20L, "0000000000000000000000000006765"));
    data.add(Pair.of(21L, "0000000000000000000000000010946"));
    data.add(Pair.of(22L, "0000000000000000000000000017711"));
    data.add(Pair.of(23L, "0000000000000000000000000028657"));
    data.add(Pair.of(24L, "0000000000000000000000000046368"));
    data.add(Pair.of(25L, "0000000000000000000000000075025"));
    data.add(Pair.of(26L, "0000000000000000000000000121393"));
    data.add(Pair.of(27L, "0000000000000000000000000196418"));
    data.add(Pair.of(28L, "0000000000000000000000000317811"));
    data.add(Pair.of(29L, "0000000000000000000000000514229"));
    data.add(Pair.of(30L, "0000000000000000000000000832040"));
    data.add(Pair.of(31L, "0000000000000000000000001346269"));
    data.add(Pair.of(32L, "0000000000000000000000002178309"));
    data.add(Pair.of(33L, "0000000000000000000000003524578"));
    data.add(Pair.of(34L, "0000000000000000000000005702887"));
    data.add(Pair.of(35L, "0000000000000000000000009227465"));
    data.add(Pair.of(36L, "0000000000000000000000014930352"));
    data.add(Pair.of(37L, "0000000000000000000000024157817"));
    data.add(Pair.of(38L, "0000000000000000000000039088169"));
    data.add(Pair.of(39L, "0000000000000000000000063245986"));
    data.add(Pair.of(40L, "0000000000000000000000102334155"));
    data.add(Pair.of(41L, "0000000000000000000000165580141"));
    data.add(Pair.of(42L, "0000000000000000000000267914296"));
    data.add(Pair.of(43L, "0000000000000000000000433494437"));
    data.add(Pair.of(44L, "0000000000000000000000701408733"));
    data.add(Pair.of(45L, "0000000000000000000001134903170"));
    data.add(Pair.of(46L, "0000000000000000000001836311903"));
    data.add(Pair.of(47L, "0000000000000000000002971215073"));
    data.add(Pair.of(48L, "0000000000000000000004807526976"));
    data.add(Pair.of(49L, "0000000000000000000007778742049"));
    data.add(Pair.of(50L, "0000000000000000000012586269025"));
    data.add(Pair.of(51L, "0000000000000000000020365011074"));
    data.add(Pair.of(52L, "0000000000000000000032951280099"));
    data.add(Pair.of(53L, "0000000000000000000053316291173"));
    data.add(Pair.of(54L, "0000000000000000000086267571272"));
    data.add(Pair.of(55L, "0000000000000000000139583862445"));
    data.add(Pair.of(56L, "0000000000000000000225851433717"));
    data.add(Pair.of(57L, "0000000000000000000365435296162"));
    data.add(Pair.of(58L, "0000000000000000000591286729879"));
    data.add(Pair.of(59L, "0000000000000000000956722026041"));
    data.add(Pair.of(60L, "0000000000000000001548008755920"));
    data.add(Pair.of(61L, "0000000000000000002504730781961"));
    data.add(Pair.of(62L, "0000000000000000004052739537881"));
    data.add(Pair.of(63L, "0000000000000000006557470319842"));
    data.add(Pair.of(64L, "0000000000000000010610209857723"));
    data.add(Pair.of(65L, "0000000000000000017167680177565"));
    data.add(Pair.of(66L, "0000000000000000027777890035288"));
    data.add(Pair.of(67L, "0000000000000000044945570212853"));
    data.add(Pair.of(68L, "0000000000000000072723460248141"));
    data.add(Pair.of(69L, "0000000000000000117669030460994"));
    data.add(Pair.of(70L, "0000000000000000190392490709135"));
    data.add(Pair.of(71L, "0000000000000000308061521170129"));
    data.add(Pair.of(72L, "0000000000000000498454011879264"));
    data.add(Pair.of(73L, "0000000000000000806515533049393"));
    data.add(Pair.of(74L, "0000000000000001304969544928657"));
    data.add(Pair.of(75L, "0000000000000002111485077978050"));
    data.add(Pair.of(76L, "0000000000000003416454622906707"));
    data.add(Pair.of(77L, "0000000000000005527939700884757"));
    data.add(Pair.of(78L, "0000000000000008944394323791464"));
    data.add(Pair.of(79L, "0000000000000014472334024676221"));
    data.add(Pair.of(80L, "0000000000000023416728348467685"));
    data.add(Pair.of(81L, "0000000000000037889062373143906"));
    data.add(Pair.of(82L, "0000000000000061305790721611591"));
    data.add(Pair.of(83L, "0000000000000099194853094755497"));
    data.add(Pair.of(84L, "0000000000000160500643816367088"));
    data.add(Pair.of(85L, "0000000000000259695496911122585"));
    data.add(Pair.of(86L, "0000000000000420196140727489673"));
    data.add(Pair.of(87L, "0000000000000679891637638612258"));
    data.add(Pair.of(88L, "0000000000001100087778366101931"));
    data.add(Pair.of(89L, "0000000000001779979416004714189"));
    data.add(Pair.of(90L, "0000000000002880067194370816120"));
    data.add(Pair.of(91L, "0000000000004660046610375530309"));
    data.add(Pair.of(92L, "0000000000007540113804746346429"));
    data.add(Pair.of(93L, "0000000000012200160415121876738"));
    data.add(Pair.of(94L, "0000000000019740274219868223167"));
    data.add(Pair.of(95L, "0000000000031940434634990099905"));
    data.add(Pair.of(96L, "0000000000051680708854858323072"));
    data.add(Pair.of(97L, "0000000000083621143489848422977"));
    data.add(Pair.of(98L, "0000000000135301852344706746049"));
    data.add(Pair.of(99L, "0000000000218922995834555169026"));
    data.add(Pair.of(100L,"0000000000354224848179261915075"));
    
    for (Pair<Long, String> pair : data) {
      Assert.assertEquals(new BigInteger(pair.getRight()), MathFun.fib(pair.getLeft()));
    }
  }
  
  @Test
  public void testCase_01_02() throws Exception {
    // big data running
    for (int i = 0; i < 10_000; i++) {
      final BigInteger result = MathFun.fib(i+1);
      //Log.i("n[" + String.format("%07d", i+1) + "] " + result);
    }
  }
}