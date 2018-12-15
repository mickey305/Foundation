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
