package com.mickey305.foundation.v3;

import com.mickey305.foundation.v3.ansi.code.AnsiStringBuilderTest;
import com.mickey305.foundation.v3.lang.tuple.MutablePairTest;
import com.mickey305.foundation.v3.util.ListUtilTest;
import com.mickey305.foundation.v3.util.concurrent.ContainerTest;
import com.mickey305.foundation.v3.util.pattern.ComponentTest;
import com.mickey305.foundation.v3.util.pattern.CompositeTest;
import com.mickey305.foundation.v3.util.pattern.LeafTest;
import com.mickey305.foundation.v3.validation.ValidationInvokerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AnsiStringBuilderTest.class,
        MutablePairTest.class,
        ContainerTest.class,
        ComponentTest.class,
        CompositeTest.class,
        LeafTest.class,
        ListUtilTest.class,
        ValidationInvokerTest.class
})
public class AllTests {}
