package com.mickey305.foundation.v3.io;

import com.mickey305.foundation.v3.resource.test.SerialTestBean;
import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class SerialFileManagerTest {
    private String filePath;
    private File file;

    @Before
    public void setUp() throws Exception {
        this.filePath = System.getProperty("java.io.tmpdir") + SerialTestBean.class.getName() + ".txt";
        this.file = new File(this.filePath);
        this.file.delete();
    }

    @After
    public void tearDown() throws Exception {
        boolean status = this.file.delete();
        if (status)
            Log.i("serialized file deletion success.");
        else
            Log.i("serialized file deletion failed.");
    }

    @Test
    public void testCase_01_01() throws Exception {
        SerialTestBean bean = new SerialTestBean(112, "hoge");

        SerialFileManager<SerialTestBean> manager = new SerialFileManager<>();
        boolean status;
        status = manager.filePath(this.filePath)
                .serialize(bean);
        manager = new SerialFileManager<>();
        bean = manager.filePath(this.filePath)
                .deserialize();

        Log.i("serialized file -> " + filePath);
        Assert.assertTrue(status);
        Assert.assertEquals(112, bean.getId());
        Assert.assertEquals("hoge", bean.getName());
    }

}

