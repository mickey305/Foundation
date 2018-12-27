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

import com.mickey305.foundation.tools.maintenance.v3.Jre;
import com.mickey305.foundation.v3.util.FileUtil;
import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v3.util.ResFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.module.ModuleReader;
import java.lang.module.ResolvedModule;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class JreLibUtils {
  private static final Properties properties;
  
  static {
    final String propertiesPath = "jre_libs.properties";
    final File propertiesFile = ResFile.get(propertiesPath);
    properties = new Properties();
    try (InputStream is = new FileInputStream(propertiesFile)) {
      properties.load(is);
    } catch (IOException e) {
      Log.e(e.getMessage());
    }
  }
  
  /**
   * @param jre
   * @return
   */
  public static Set<Class<?>> commonClassesFor(Jre jre) {
    final Set<Class<?>> allClasses = new HashSet<>();
    final Set<URL> urls = new HashSet<>();
    //
    // JRE Version 1.7
    //
    if (jre == Jre.SE7) {
      Log.d("JRE 1.7 standard library class search start");
      FileUtil.findAllFile(properties.getProperty("se7", "")).forEach(file -> convertUrl(file, urls));
      final Function<String, Set<Class<?>>> searcher = ReflectionsUtil.getInstance().classSearcher(urls);
      allClasses.addAll(searcher.apply("java."));
      allClasses.addAll(searcher.apply("javax."));
      allClasses.addAll(searcher.apply("org.omg."));
      allClasses.addAll(searcher.apply("org.w3c.dom."));
      allClasses.addAll(searcher.apply("org.xml.sax."));
      Log.d("JRE 1.7 standard library class search end");
    }
    //
    // JRE Version 1.8
    //
    if (jre == Jre.SE8) {
      Log.d("JRE 1.8 standard library class search start");
      FileUtil.findAllFile(properties.getProperty("se8", "")).forEach(file -> convertUrl(file, urls));
      final Function<String, Set<Class<?>>> searcher = ReflectionsUtil.getInstance().classSearcher(urls);
      allClasses.addAll(searcher.apply("java."));
      allClasses.addAll(searcher.apply("javax."));
      allClasses.addAll(searcher.apply("org.omg."));
      allClasses.addAll(searcher.apply("org.w3c.dom."));
      allClasses.addAll(searcher.apply("org.xml.sax."));
      Log.d("JRE 1.8 standard library class search end");
    }
    //
    // JRE Version 9 to 11
    //
    /////////////////////////////////////////////////
    // todo: 処理をバージョンごとに分割可能か検討する
    /////////////////////////////////////////////////
    if (Arrays.asList(Jre.SE9, Jre.SE10, Jre.SE11).contains(jre)) {
      Log.d("JRE 9to11 standard library class search start");
      ModuleLayer.boot().configuration().modules().stream()
          .map(ResolvedModule::reference)
          .forEach(ref -> {
            try (ModuleReader reader = ref.open()) {
              reader.list().forEach(path -> {
                if (!path.toLowerCase().endsWith(".class")) {
                  return;
                }
                
                final String classPath = path
                    .replace(".class", "")
                    .replace("/", ".");
                
                if (Stream.of(
                    "java.",
                    "javax.",
                    "org.omg.",
                    "org.w3c.dom.",
                    "org.xml.sax.")
                    .noneMatch(s -> classPath.toLowerCase().startsWith(s))) {
                  return;
                }
                
                try {
                  final Class<?> target = Class.forName(classPath);
                  allClasses.add(target);
                } catch (ClassNotFoundException e) {
                  Log.e(e.getMessage());
                  throw new RuntimeException(e);
                }
              });
            } catch (IOException e) {
              Log.e(e.getMessage());
              throw new UnsupportedOperationException(e);
            }
          });
      Log.d("JRE 9to11 standard library class search end");
    }
    return allClasses;
  }
  
  /**
   * @param from
   * @param cache
   */
  private static void convertUrl(File from, Set<URL> cache) {
    try {
      final URL url = from.toURI().toURL();
      final boolean status = cache.add(url);
      if (!status) {
        throw new RuntimeException("data add operation refused");
      }
    } catch (MalformedURLException e) {
      Log.e(e.getMessage());
      throw new RuntimeException(e);
    } catch (RuntimeException e) {
      Log.e(e.getMessage());
      throw e;
    }
  }
}
