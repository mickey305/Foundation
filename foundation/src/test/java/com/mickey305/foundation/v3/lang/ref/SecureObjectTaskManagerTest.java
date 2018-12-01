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