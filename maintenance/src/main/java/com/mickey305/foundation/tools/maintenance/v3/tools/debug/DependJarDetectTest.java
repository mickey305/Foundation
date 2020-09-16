package com.mickey305.foundation.tools.maintenance.v3.tools.debug;

import com.mickey305.foundation.v3.ansi.code.AnsiStringBuilder;
import com.mickey305.foundation.v3.ansi.code.Escape;
import com.mickey305.foundation.v3.util.Log;

import java.io.File;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * @see <a href="reference">https://oshiete.goo.ne.jp/qa/8840160.html</a>
 */
public class DependJarDetectTest {
    private final Set<String> cache = new TreeSet<>();

    public static void main(String[] args) {
        try {
            if (IS_DEBUG_MODE) Log.d("args count = " + args.length);
            final String[] ignoreCases = args.length == 0 ? new String[0] : args;
            new DependJarDetectTest().execDetect(ignoreCases);
        } catch (Exception e) {
            Log.e(e);
        }
    }

    private void execDetect(final String[] ignoreCases) throws RuntimeException {
        final Properties prop = System.getProperties();
        final String javaClasspath = prop.getProperty("java.class.path", null);

        String[] classPathArray = new String[0];
        try {
            final String separator = System.getProperty("path.separator");
            classPathArray = javaClasspath.split(separator);
            for (int i = 0; i < classPathArray.length; i++) {
                Log.i("java.class.path [" + i + "] = " + classPathArray[i]);
                getJarFileClassListFromClassPath(classPathArray[i], ignoreCases);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Log.i("java.class.path total = " + classPathArray.length + " path(s)");
            Log.i("ignorePatterns = " + Arrays.toString(ignoreCases));
        }
    }

    private void getJarFileClassListFromClassPath(final String classPath, final String[] ignoreCases) throws Exception {
        final File file = new File(classPath);
        if (file.isDirectory()) return;

        try (final JarFile jarFile = new JarFile(file)) {
            // ignore jar file suffix path logic impl
            // eg. test.jar -> ignore sample1/test.jar
            //              -> ignore sample2/test.jar
            //
            final long ignoreMatchedJarCount = Arrays.stream(ignoreCases)
//                    .peek(data -> Log.d("entry:" + jarFile.getName() + ", ignoreCase:" + data))
                    .filter(ignoreCase -> ignoreCase.endsWith(".jar"))
                    .filter(ignoreCase -> jarFile.getName().endsWith(ignoreCase))
                    .count();
            if (ignoreMatchedJarCount > 0) return;

            for (Enumeration<?> e = jarFile.entries(); e.hasMoreElements(); ) {
                final JarEntry entry = (JarEntry) e.nextElement();
                if (entry.isDirectory() || !entry.getName().contains(".class")) continue;

                // ignore path prefix logic
                // eg. co/jp/example -> ignore co.jp.example.... package
                //
                final long ignoreMatchedCount = Arrays.stream(ignoreCases)
//                        .peek(data -> Log.d("entry:" + entry.getName() + ", ignoreCase:" + data))
                        .filter(ignoreCase -> entry.getName().startsWith(ignoreCase))
                        .count();
                if (ignoreMatchedCount > 0) continue;

                if (cache.contains(entry.getName())) {
                    final AnsiStringBuilder outInfo = new AnsiStringBuilder()
                            .append(Escape.Red)
                            .append("[Conflict]: ")
                            .append(entry.getName())
                            .append(Escape.Reset);
                    Log.d(outInfo);
                } else {
                    Log.i(" --> " + entry.getName());
                    cache.add(entry.getName());
                }
            }
        }
    }
}
