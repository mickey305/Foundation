package com.mickey305.foundation.v3.gen;

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.math3.fraction.BigFraction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void knownImmutableClasses() throws Exception {
    Log.i("-- test start");
    Log.i("status:" + R.knownImmutableClasses().contains(String.class));
    Log.i("status:" + R.knownImmutableClasses().contains(Double.class));
    Log.i("status:" + R.knownImmutableClasses().contains(Integer.class));
    Log.i("status:" + R.knownImmutableClasses().contains(String.class));
    Log.i("status:" + R.knownImmutableClasses().contains(String.class));
    Log.i("status:" + R.knownImmutableClasses().contains(BigFraction.class));
    Log.i("status:" + R.knownImmutableClasses().contains(Log.class));
    Log.i("-- test finish");
  }
}