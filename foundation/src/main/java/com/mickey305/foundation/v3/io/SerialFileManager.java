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
