package com.mickey305.foundation.tools.maintenance.v3.util;

import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.net.URL;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public class ReflectionsUtil {
  private SubTypesScanner scanner;
  
  private ReflectionsUtil() {
    this.setScanner(this.createScanner());
  }
  
  private static class ReflectionsUtilHolder {
    private static final ReflectionsUtil INSTANCE = new ReflectionsUtil();
  }
  
  public static ReflectionsUtil getInstance() {
    return ReflectionsUtilHolder.INSTANCE;
  }
  
  private SubTypesScanner createScanner() {
    return new SubTypesScanner(false);
  }
  
  public Function<String, Set<Class<?>>> classSearcher() {
    return packagePrefix -> {
      final Configuration config = ConfigurationBuilder
          .build(packagePrefix, this.getScanner());
      return new Reflections(config).getSubTypesOf(Object.class);
    };
  }
  
  public Function<String, Set<Class<?>>> classSearcher(Collection<URL> urls) {
    return packagePrefix -> {
      final Configuration config = ConfigurationBuilder
          .build(packagePrefix, this.getScanner())
          .setUrls(urls);
      return new Reflections(config).getSubTypesOf(Object.class);
    };
  }
  
  private SubTypesScanner getScanner() {
    return scanner;
  }
  
  private void setScanner(SubTypesScanner scanner) {
    this.scanner = scanner;
  }
}
