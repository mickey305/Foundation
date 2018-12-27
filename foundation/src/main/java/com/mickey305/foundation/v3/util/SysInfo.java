package com.mickey305.foundation.v3.util;

import java.util.Enumeration;
import java.util.Properties;

public class SysInfo {
  
  private SysInfo() {
  }
  
  public static void printEnv() {
    Properties properties = System.getProperties();
    Enumeration<Object> enumeration = properties.keys();
    for (int i = 0; i < properties.size(); i++) {
      Object obj = enumeration.nextElement();
      System.out.println(obj + "=[" + System.getProperty(obj.toString()) + "]");
    }
  }
}
