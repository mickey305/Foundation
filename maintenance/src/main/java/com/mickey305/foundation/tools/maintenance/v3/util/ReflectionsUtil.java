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
  
  /*
   *
   *
   */
  private SubTypesScanner createScanner() {
    return new SubTypesScanner(false);
  }
  
  /**
   * @return
   */
  public Function<String, Set<Class<?>>> classSearcher() {
    return packagePrefix -> {
      final Configuration config = ConfigurationBuilder
          .build(packagePrefix, this.getScanner());
      return new Reflections(config).getSubTypesOf(Object.class);
    };
  }
  
  /**
   * @param urls
   * @return
   */
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
