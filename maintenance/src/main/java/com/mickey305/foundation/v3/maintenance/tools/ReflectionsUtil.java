package com.mickey305.foundation.v3.maintenance.tools;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

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
        return packagePrefix -> new Reflections(packagePrefix, this.getScanner()).getSubTypesOf(Object.class);
    }

    private SubTypesScanner getScanner() {
        return scanner;
    }

    private void setScanner(SubTypesScanner scanner) {
        this.scanner = scanner;
    }
}
