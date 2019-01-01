/*
 * Copyright (c) 2019. K.Misaki
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
    this.filePath = this.createTmpPath() + SerialTestBean.class.getName() + ".txt";
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

  private String createTmpPath() {
    String tmpPath = System.getProperty("java.io.tmpdir");
    if (!tmpPath.endsWith(File.separator))
      tmpPath += File.separator;
    return tmpPath;
  }
}

