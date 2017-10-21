package com.mickey305.foundation.v3.ansi.code;

import com.mickey305.foundation.v3.validation.BeanValidationException;
import com.mickey305.foundation.v3.validation.ValidationInvoker;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class AnsiStringBuilderTest {

    /**
     * <p>CASE 1-1</>
     * <p>コンストラクタのバリデーションチェック</>
     *
     * @throws Exception 例外
     */
    @Test
    public void testCase_01_01() throws Exception {
        Constructor<AnsiStringBuilder> bean = AnsiStringBuilder.class.getConstructor(String.class);
        Escape[] escapeLst = Escape.values();
        for (Escape escape : escapeLst) {
            try {
                // escape string insertion check
                ValidationInvoker.validateConstructor(bean, new Object[]{ escape.code() });

                Assert.assertTrue(escape.code(), false);
            } catch (BeanValidationException e) {
                Assert.assertTrue(true); // assertion step
            } catch (Exception e) {
                Assert.assertTrue(escape.code(), false);
            }
        }

        String[] stmt = new String[] {
                "apple", "orange", "#d1@asq)", "[]^&%$!\"", "*(){}?><ASKI~`\\|.,-+_", null, "", " ", "　" ,"日本語"};
        for (String str : stmt) {
            try {
                // non-escape string insertion check
                ValidationInvoker.validateConstructor(bean, new Object[]{ str });

                Assert.assertTrue(true); // assertion step
            } catch (Exception e) {
                Assert.assertTrue(str, false);
            }
        }
    }

}
