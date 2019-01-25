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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SerialByteManager<T> {
  private byte[] data;
  
  public boolean serialize(T obj) {
    return Serializer.serialize(obj, new ByteArrayOutputStream(), new Serializer.Callback<ByteArrayOutputStream>() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void onWritten(ByteArrayOutputStream byteArrayOutputStream) {
        setData(byteArrayOutputStream.toByteArray());
      }
    });
  }
  
  public T deserialize() {
    return Serializer.deserialize(new ByteArrayInputStream(this.getData()));
  }
  
  public SerialByteManager<T> data(byte[] data) {
    this.setData(data);
    return this;
  }
  
  public byte[] getData() {
    return data;
  }
  
  private void setData(byte[] data) {
    this.data = data;
  }
}
