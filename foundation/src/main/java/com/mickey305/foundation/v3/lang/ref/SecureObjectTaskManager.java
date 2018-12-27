/*
 * Copyright (c) 2018. K.Misaki
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

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class SecureObjectTaskManager<I, O> {
  private ISecureObjectInitializer<I> ini;
  private ITask<Reference<I>, O> task;
  
  public SecureObjectTaskManager(ISecureObjectInitializer<I> ini, ITask<Reference<I>, O> task) {
    this.ini = ini;
    this.task = task;
  }
  
  public synchronized O invoke() {
    I obj = ini.ini();
    WeakReference<I> weakObj = new WeakReference<>(obj);
    if (IS_DEBUG_MODE) Log.d("ref:" + weakObj.get());
    O result = task.impl(weakObj);
    obj = null;
    if (IS_DEBUG_MODE) Log.d("ref:" + weakObj.get());
    System.gc();
    if (IS_DEBUG_MODE) Log.d("ref:" + weakObj.get());
    return result;
  }
  
}
