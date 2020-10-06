/*
 * Copyright (c) 2017 - 2020 K.Misaki
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SerialFileManager<T> {
  private String filePath;
  
  public boolean serialize(T obj) {
    try {
      return Serializer.serialize(obj, new FileOutputStream(this.getFilePath()), null);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public T deserialize() {
    try {
      return Serializer.deserialize(new FileInputStream(this.getFilePath()));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public SerialFileManager<T> filePath(String filePath) {
    this.setFilePath(filePath);
    return this;
  }
  
  public String getFilePath() {
    return filePath;
  }
  
  private void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  
}
