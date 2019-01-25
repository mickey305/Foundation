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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Serializer {
  
  private Serializer() {
  }
  
  public static <T, OS extends OutputStream> boolean serialize(T obj, OS os, Callback<OS> callback) {
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(os);
      oos.writeObject(obj);
      oos.flush();
      if (callback != null)
        callback.onWritten(os);
      return true;
    } catch (IOException e) {
      return false;
    } finally {
      try {
        if (oos != null) {
          oos.close();
          os.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  @SuppressWarnings("unchecked")
  public static <T, IS extends InputStream> T deserialize(IS is) {
    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(is);
      return (T) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return null;
    } finally {
      try {
        if (ois != null) {
          ois.close();
          is.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public interface Callback<OS extends OutputStream> {
    void onWritten(OS os);
  }
}
