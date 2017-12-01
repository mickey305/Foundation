package com.mickey305.foundation.v3.io;

import com.mickey305.foundation.v3.resource.test.SerialTestBean;
import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SerialByteManagerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCase_01_01() throws Exception {
        SerialTestBean bean = new SerialTestBean(112, "hoge");

        SerialByteManager<SerialTestBean> manager = new SerialByteManager<>();
        boolean status;
        status = manager.serialize(bean);
        byte[] data = manager.getData();
        manager = new SerialByteManager<>();
        bean = manager.data(data)
                .deserialize();

        Log.i("serialized data -> " + Arrays.toString(data));
        Log.i("serialized data length -> " + data.length);
        for (int i = 0; i < data.length; i++)
            Log.i(String.format("data[%03d] -> ", i) +
                    "original-value: " + ((char) data[i]) +
                    ", hex-value: " + Integer.toHexString((char) data[i]));
        Assert.assertTrue(status);
        Assert.assertEquals(112, bean.getId());
        Assert.assertEquals("hoge", bean.getName());
    }

}

