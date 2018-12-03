package com.mickey305.foundation.v3;

import com.mickey305.foundation.v3.maintenance.tools.ReflectionsUtil;
import com.squareup.javapoet.ClassName;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;

final class Workflow {
    private static final String GEN_PKG = "com.mickey305.foundation.v3.gen";
    private static final String TARGET_MODULE = "foundation";
    private final Path targetJavaFolder;

    private Workflow() {
        final String sp = File.separator;
        targetJavaFolder = Paths.get(System.getProperty("user.dir") + sp
                + TARGET_MODULE + sp + "src" + sp + "main" + sp + "java" + sp);
    }

    private static class WorkflowHolder {
        private static final Workflow INSTANCE = new Workflow();
    }

    public static Workflow getInstance() {
        return WorkflowHolder.INSTANCE;
    }

    public void updateGenerationClasses() {
        try {
            this.buildResourceClass();
        } catch (NoSuchMethodException | IOException e) {
            e.printStackTrace();
        }
    }

    private void buildResourceClass() throws NoSuchMethodException, IOException {
        final String resourceClassName = "R";
        final String cacheFieldName = "cn1_" + System.currentTimeMillis();
        final String jreFieldName = "JRE";
        final double jre18 = 1.8;
        final String buildImmutableClassesMethodName = "buildImmutableClasses";
        final String buildImmutableClassesMethodNameJre18 = buildImmutableClassesMethodName + "Jre18";
        final String getImmutableClassesMethodName = "knownImmutableClasses";
        final Set<Class<?>> allClasses = new HashSet<>();
        final Function<String, Set<Class<?>>> searcher = ReflectionsUtil.getInstance().classSearcher();
        allClasses.addAll(searcher.apply("java."));
        allClasses.addAll(searcher.apply("javax."));
        allClasses.addAll(searcher.apply("org.omg."));
        allClasses.addAll(searcher.apply("org.w3c.dom."));
        allClasses.addAll(searcher.apply("org.xml.sax."));

        final Method dummyMethod = Workflow.class.getDeclaredMethod("dummy", Class.class);
        final Type[] types = dummyMethod.getGenericParameterTypes();
        final ClassName clz = ClassName.get(Class.class);
        final TypeName something = WildcardTypeName.get(((ParameterizedType) types[0]).getActualTypeArguments()[0]);
        final TypeName classElement = ParameterizedTypeName.get(clz, something);
        final ClassName set = ClassName.get(Set.class);
        final ClassName hashSet = ClassName.get(HashSet.class);
        final TypeName setOfClass = ParameterizedTypeName.get(set, classElement);

        FieldSpec immutableClassesField = FieldSpec
                .builder(setOfClass, cacheFieldName, Modifier.PRIVATE, Modifier.STATIC)
                .build();
        FieldSpec jreField = FieldSpec
                .builder(double.class, jreFieldName, Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("($T.parseDouble($T.getProperty(\"java.specification.version\")))",
                    Double.class, System.class)
                .build();
        MethodSpec getImmutableClassesMethod = MethodSpec
                .methodBuilder(getImmutableClassesMethodName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.SYNCHRONIZED)
                .returns(setOfClass)
                .beginControlFlow("if ("+ cacheFieldName +" == null)")
                    .addStatement(cacheFieldName + " = $T.emptySet()", Collections.class)
                    .addStatement(
                        "if (" + jreFieldName + " == " + jre18 + ") " +
                        buildImmutableClassesMethodNameJre18 + "()"
                    )
                .endControlFlow()
                .addStatement("return $T.unmodifiableSet(" + cacheFieldName + ")", Collections.class)
                .build();
        MethodSpec.Builder buildImmutableClassesJre18MethodBuilder = MethodSpec
                .methodBuilder(buildImmutableClassesMethodNameJre18)
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .returns(void.class)
                .addStatement(cacheFieldName + " = new $T<>()", hashSet);
        for (Class<?> elm: allClasses) {
            if (!elm.getName().contains("$")
                    && java.lang.reflect.Modifier.isPublic(elm.getModifiers())
                    && !Cloneable.class.isAssignableFrom(elm)
                    && checkImmutableClass(elm)) {
                try {
                    buildImmutableClassesJre18MethodBuilder.addStatement(cacheFieldName + ".add($T.class)", elm);
                } catch (RuntimeException ignored) {}
            }
        }
        MethodSpec buildImmutableClassesJre18Method = buildImmutableClassesJre18MethodBuilder
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
                .addMethod(privateCons)
                .addMethod(getImmutableClassesMethod)
                .addMethod(buildImmutableClassesJre18Method)
                .build();
        JavaFile javaFile = JavaFile
                .builder(GEN_PKG, resourceClass)
                .build();

        javaFile.writeTo(this.targetJavaFolder);

        System.out.println("Update class -> " +
                javaFile.packageName + "." + resourceClass.name + " of " + this.targetJavaFolder);
    }

    /**
     *
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
            // Todo: Oracle rule implementation
//            return checkImmutableClassByOracleRule(target);
            return true;
        } catch (RuntimeException | NoClassDefFoundError e) {
            return false;
        }
    }

    /**
     *
     * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html"/>
     * @param target
     * @return
     */
    private static boolean checkImmutableClassByOracleRule(Class<?> target) {
        Field[] fields = target.getDeclaredFields();
        // Rule1
        List<String> targetMethodNames = new ArrayList<>();
        for (Field field: fields)
            targetMethodNames.add("set" + StringUtils.capitalize(field.getName()));
        for (Method method: target.getDeclaredMethods()) {
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
            for (Constructor constructor: target.getDeclaredConstructors()) {
                if (!java.lang.reflect.Modifier.isPrivate(constructor.getModifiers()))
                    return false;
            }
        }
        // Rule4
        // Skip: Contains Rule2
        return true;
    }

    private void dummy(Class<?> clz) { }
}
