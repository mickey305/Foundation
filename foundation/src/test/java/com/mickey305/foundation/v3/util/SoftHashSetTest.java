package com.mickey305.foundation.v3.util;

import org.apache.commons.collections4.map.AbstractReferenceMap;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.HARD;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.SOFT;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.WEAK;

public class SoftHashSetTest {
  private static final AbstractReferenceMap.ReferenceStrength CONST_HARD     = HARD;
  private static final AbstractReferenceMap.ReferenceStrength CONST_SOFT     = SOFT;
  private static final AbstractReferenceMap.ReferenceStrength CONST_WEAK     = WEAK;
  
  private static final AbstractReferenceMap.ReferenceStrength DEFAULT_STRENGTH = WEAK;
  
  private Set<String> hardCache;
  private Set<String> softCache;
  private Set<String> weakCache;
  
  @Before
  public void setUp() throws Exception {
    hardCache = new SoftHashSet<>(CONST_HARD);
    softCache = new SoftHashSet<>(CONST_SOFT);
    weakCache = new SoftHashSet<>(CONST_WEAK);
  }
  
  @After
  public void tearDown() throws Exception {
    hardCache = null;
    softCache = null;
    weakCache = null;
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    Set<String> defaultCache;
    
    defaultCache = hardCache;
    createTestData(defaultCache);
    Log.i("hardCache bef: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    Assert.assertNotEquals(0, defaultCache.size());
    Assert.assertNotEquals(0, defaultCache.toArray().length);
    Assert.assertFalse(defaultCache.isEmpty());
    System.gc();
    Assert.assertNotEquals(0, defaultCache.size());
    Assert.assertNotEquals(0, defaultCache.toArray().length);
    Assert.assertFalse(defaultCache.isEmpty());
    Log.i("hardCache aft: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
  
    defaultCache = softCache;
    createTestData(defaultCache);
    Log.i("softCache bef: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    System.gc();
    Log.i("softCache aft: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
  
    defaultCache = weakCache;
    createTestData(defaultCache);
    Log.i("weakCache bef: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    Assert.assertNotEquals(0, defaultCache.size());
    Assert.assertNotEquals(0, defaultCache.toArray().length);
    Assert.assertFalse(defaultCache.isEmpty());
    System.gc();
    Log.d("weakCache aft - size: " + defaultCache.size());
    Assert.assertEquals(0, defaultCache.toArray().length);
    Assert.assertTrue(defaultCache.isEmpty());
    Log.i("weakCache aft: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    
  }
  
  private static void createTestData(Set<String> set) {
    set.add(new String("test01"));
    set.add(new String("test02"));
    set.add(new String("test03"));
    set.add(new String("test04"));
    set.add(new String("test05"));
    set.add(new String("test06"));
    set.add(new String("test07"));
    set.add(new String("test08"));
    set.add(new String("test09"));
    set.add(new String("test10"));
  }
}