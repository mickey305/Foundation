package com.mickey305.foundation.tools.maintenance.v3;

import com.mickey305.foundation.tools.maintenance.v3.util.JreLibUtils;
import com.mickey305.foundation.tools.maintenance.v3.util.ReflectionsUtil;
import com.mickey305.foundation.EnvConfigConst;
import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v3.util.SoftHashSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;
import org.apache.commons.lang3.StringUtils;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Workflow {
  private static final String GEN_PKG = "com.mickey305.foundation.v3.gen";
  private static final String TARGET_MODULE = "foundation";
  private static final double JRE_NOW;
  
  private String[] args;
  private Path targetJavaFolder;
  
  static {
    JRE_NOW = (Double.parseDouble(System.getProperty("java.specification.version")));
  }
  
  private Workflow() {
    this.setArgs(new String[]{});
  }
  
  private static class WorkflowHolder {
    private static final Workflow INSTANCE = new Workflow();
  }
  
  public static Workflow getInstance() {
    return WorkflowHolder.INSTANCE;
  }
  
  public Workflow setArgs(String[] args) {
    this.args = args;
    return this;
  }
  
  public Workflow buildPath() {
    final String sp = File.separator;
    if (args.length == 0) {
      // do not have arguments
      targetJavaFolder = Paths.get(System.getProperty("user.dir") + sp
          + TARGET_MODULE + sp + "src" + sp + "main" + sp + "java" + sp);
    } else {
      // have arguments
      String targetRootClassPath = args[0];
      if (!targetRootClassPath.endsWith(sp)) targetRootClassPath += sp;
      targetJavaFolder = Paths.get(targetRootClassPath);
    }
    
    return this;
  }
  
  /**
   *
   */
  public void updateGenerationClasses() {
    try {
      this.buildResourceClass();
    } catch (NoSuchMethodException | IOException e) {
      Log.e(e.getMessage());
      e.printStackTrace();
    }
  }
  
  /**
   *
   * @throws NoSuchMethodException
   * @throws IOException
   */
  private void buildResourceClass() throws NoSuchMethodException, IOException {
    final String resourceClassName = "R";
    final String cacheName = "cache";
    final String cacheSizeName = "cacheSize";
    final String jreFieldName = "JRE";
    final String methodName = "buildImmutableClasses";
    final String getImmutableClassesMethodName = "knownImmutableClasses";
    
    if (!(JRE_NOW >= Jre.SE7.getVersion() && JRE_NOW <= Jre.SE11.getVersion())) {
      throw new UnsupportedOperationException(ExceptionMessageConst.JRE_UNSUPPORTED + " : version " + String.valueOf(JRE_NOW));
    }
    
    final Method dummyMethod = Workflow.class.getDeclaredMethod("dummy", Class.class);
    final Type[] types = dummyMethod.getGenericParameterTypes();
    final ClassName clz = ClassName.get(Class.class);
    final TypeName something = WildcardTypeName.get(((ParameterizedType) types[0]).getActualTypeArguments()[0]);
    final TypeName classElement = ParameterizedTypeName.get(clz, something);
    final ClassName set = ClassName.get(Set.class);
    final TypeName setOfClass = ParameterizedTypeName.get(set, classElement);
    final ClassName softHashSet = ClassName.get(SoftHashSet.class);
    
    FieldSpec immutableClassesField = FieldSpec
        .builder(setOfClass, cacheName, Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
        .build();
    FieldSpec jreField = FieldSpec
        .builder(double.class, jreFieldName, Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
        .initializer("($T.parseDouble($T.getProperty(\"java.specification.version\")))",
            Double.class, System.class)
        .build();
    FieldSpec cacheSizeField = FieldSpec
        .builder(int.class, cacheSizeName, Modifier.PRIVATE, Modifier.STATIC)
        .initializer("0")
        .build();
    MethodSpec getImmutableClassesMethod = MethodSpec
        .methodBuilder(getImmutableClassesMethodName)
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .returns(setOfClass)
        .addStatement("assert " + cacheName + " != null")
        .beginControlFlow("if (" + cacheSizeName + " == 0"
            + " || " + cacheSizeName+ " > (" + cacheName + ".size() * 3.0))")
        .addComment("data SIZE-ZERO(Initial) or less than 1/3 of original collection size")
        .beginControlFlow("synchronized (" + cacheName + ")")
        .addStatement("if ($T.IS_DEBUG_MODE) $T.d(\"build start\")", EnvConfigConst.class, Log.class)
        .addStatement("if (" + jreFieldName + " == " + Jre.SE7.getVersion() + ") " + methodName + Jre.SE7.name() + "()")
        .addStatement("if (" + jreFieldName + " == " + Jre.SE8.getVersion() + ") " + methodName + Jre.SE8.name() + "()")
        .addStatement("if (" + jreFieldName + " == " + Jre.SE9.getVersion() + ") " + methodName + Jre.SE10.name() + "()")
        .addStatement("if (" + jreFieldName + " == " + Jre.SE10.getVersion() + ") " + methodName + Jre.SE10.name() + "()")
        .addStatement("if (" + jreFieldName + " == " + Jre.SE11.getVersion() + ") " + methodName + Jre.SE10.name() + "()")
        .addStatement("if ($T.IS_DEBUG_MODE) $T.d(\"build finish\")", EnvConfigConst.class, Log.class)
        .endControlFlow()
        .endControlFlow()
        .addStatement("return $T.unmodifiableSet(" + cacheName + ")", Collections.class)
        .build();
    MethodSpec privateCons = MethodSpec.constructorBuilder()
        .addModifiers(Modifier.PRIVATE)
        .addComment("This class is static view only class.")
        .build();
    TypeSpec resourceClass = TypeSpec
        .classBuilder(resourceClassName)
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addJavadoc("This class generated by Foundation workflow. Timestamp: " +
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(new Timestamp(System.currentTimeMillis())) + System.lineSeparator())
        .addField(jreField)
        .addField(immutableClassesField)
        .addField(cacheSizeField)
        .addStaticBlock(CodeBlock.builder().addStatement(cacheName + " = new $T<>()", softHashSet).build())
        .addMethod(privateCons)
        .addMethod(getImmutableClassesMethod)
        .addMethod(createMethod(Jre.SE10, methodName, cacheName, cacheSizeName).build())
        .addMethod(createMethod(Jre.SE8, methodName, cacheName, cacheSizeName).build())
        .addMethod(createMethod(Jre.SE7, methodName, cacheName, cacheSizeName).build())
        .build();
    JavaFile javaFile = JavaFile
        .builder(GEN_PKG, resourceClass)
        .build();
    
    javaFile.writeTo(this.targetJavaFolder);
    
    System.out.println("Update class -> " +
        javaFile.packageName + "." + resourceClass.name + " of " + this.targetJavaFolder);
  }
  
  /**
   * @param jre
   * @param methodName
   * @param cacheName
   * @param cacheSizeName
   * @return
   */
  private static MethodSpec.Builder createMethod(Jre jre, String methodName, String cacheName, String cacheSizeName) {
    final Set<Class<?>> jreClasses = JreLibUtils.commonClassesFor(jre);
    final Set<Class<?>> localClasses = createClassesOf(
        "com.mickey305.foundation.v3.",
        "com.mickey305.foundation.v4");
    final Collection<Class<?>> allClasses = Stream.of(jreClasses, localClasses)
        .flatMap(Collection::stream)
        .sorted(Comparator.comparing(Class::getName))
        .collect(Collectors.toUnmodifiableList());
    
    MethodSpec.Builder methodBuilder = MethodSpec
        .methodBuilder(methodName + jre.name())
        .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
        .returns(void.class);
    if (allClasses.size() > 0) {
      methodBuilder
          .beginControlFlow("try");
    }
    for (Class<?> elm : allClasses) {
      if (!elm.getName().contains("$")
          && java.lang.reflect.Modifier.isPublic(elm.getModifiers())
          && !Cloneable.class.isAssignableFrom(elm)
          && checkImmutableClass(elm)) {
        try {
          methodBuilder
              .addStatement(cacheName + ".add(Class.forName(\"" + elm.getName() + "\"))");
        } catch (RuntimeException e) {
          Log.e(e.getMessage());
        }
      }
    }
    if (allClasses.size() > 0) {
      methodBuilder
          .nextControlFlow("catch (ClassNotFoundException e)")
          .addStatement("$T.e(e.getMessage())", Log.class)
          .endControlFlow();
    }
    methodBuilder
        .addStatement(cacheSizeName + " = " + cacheName + ".size()");
    
    return methodBuilder;
  }
  
  /**
   *
   * @param prefixes
   * @return
   */
  private static Set<Class<?>> createClassesOf(String... prefixes) {
    final Set<Class<?>> targetClasses = new HashSet<>();
    final Function<String, Set<Class<?>>> searcher = ReflectionsUtil.getInstance().classSearcher();
    
    Log.d("class search start. data:" + Arrays.toString(prefixes));
    Arrays.stream(prefixes).forEach(prefix -> targetClasses.addAll(searcher.apply(prefix)));
    Log.d("class search end.");
    
    return targetClasses;
  }
  
  /**
   * @param target
   * @return
   */
  private static boolean checkImmutableClass(Class<?> target) {
    // interface check
    if (target.isInterface())
      return false;
    
    // known immutable-class check
    List<Class<?>> knownClasses = new ArrayList<>();
    knownClasses.add(String.class);
    knownClasses.add(Integer.class);
    knownClasses.add(Long.class);
    knownClasses.add(Boolean.class);
    knownClasses.add(Class.class);
    knownClasses.add(Float.class);
    knownClasses.add(Double.class);
    knownClasses.add(Character.class);
    knownClasses.add(Byte.class);
    knownClasses.add(Short.class);
    knownClasses.add(Void.class);
    knownClasses.add(BigDecimal.class);
    knownClasses.add(BigInteger.class);
    knownClasses.add(URI.class);
    knownClasses.add(URL.class);
    knownClasses.add(UUID.class);
    knownClasses.add(Pattern.class);
    if (knownClasses.contains(target))
      return true;
    
    // unknown immutable-class check
    try {
      // Simply Rule
      Field[] fields = target.getDeclaredFields();
      for (Field field : fields) {
        if (!java.lang.reflect.Modifier.isFinal(field.getModifiers()))
          return false;
      }
      /////////////////////////////////////////
      // Todo: Oracle rule implementation
      /////////////////////////////////////////
      //// return checkImmutableClassByOracleRule(target);
      return true;
    } catch (RuntimeException | NoClassDefFoundError e) {
      Log.e(e.getMessage());
      return false;
    }
  }
  
  /**
   * @param target
   * @return
   * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html"/>
   */
  private static boolean checkImmutableClassByOracleRule(Class<?> target) {
    Field[] fields = target.getDeclaredFields();
    // Rule1
    List<String> targetMethodNames = new ArrayList<>();
    for (Field field : fields)
      targetMethodNames.add("set" + StringUtils.capitalize(field.getName()));
    for (Method method : target.getDeclaredMethods()) {
      if (targetMethodNames.contains(method.getName()))
        return false;
    }
    // Rule2
    for (Field field : fields) {
      if (!(java.lang.reflect.Modifier.isFinal(field.getModifiers())
          && java.lang.reflect.Modifier.isPrivate(field.getModifiers())))
        return false;
    }
    // Rule3
    if (!java.lang.reflect.Modifier.isFinal(target.getModifiers())) {
      for (Constructor constructor : target.getDeclaredConstructors()) {
        if (!java.lang.reflect.Modifier.isPrivate(constructor.getModifiers()))
          return false;
      }
    }
    // Rule4
    // Skip: Contains Rule2
    return true;
  }
  
  private void dummy(Class<?> clz) {
  }
}
