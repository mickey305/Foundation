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

package com.mickey305.foundation.v3.lang.ref;

import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.ref.Reference;

public class SecureObjectTaskManagerTest implements ITask<Reference<String>, Void> {
  private ISecureObjectInitializer<String> ini;
  private String cache;
  
  @Before
  public void setUp() throws Exception {
    ini = new ISecureObjectInitializer<String>() {
      @Override
      public String ini() {
        return new String("test statement!!"); // OK
//        return "test statement!!"; // Err
//        cache = "test statement!!"; return new String(cache); // Err
//        cache = new String("test statement!!"); return cache; // Err
//        cache = "test statement!!"; return cache; // Err
      }
    };
  }
  
  @After
  public void tearDown() throws Exception {
    Log.d("cache:" + cache);
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    SecureObjectTaskManager<String, Void> sotm;
    sotm = new SecureObjectTaskManager<>(ini, this);
    sotm.invoke();
  }
  
  @Override
  public Void impl(Reference<String> input) {
    // secure task impl
    final String target = input.get();
    Log.i("impl:" + target);
    return null;
  }
}