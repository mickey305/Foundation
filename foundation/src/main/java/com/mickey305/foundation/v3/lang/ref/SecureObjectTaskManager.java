package com.mickey305.foundation.v3.lang.ref;

import com.mickey305.foundation.v3.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import static com.mickey305.foundation.v3.EnvConfigConst.IS_DEBUG_MODE;

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
