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
