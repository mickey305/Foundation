package com.mickey305.foundation.v3.util.bean;

import java.util.List;

public class LightCsvDto {
  public static final Character DLM = ',';
  public static final Character ESCAPE_CHAR = '"';
  
  private List<String> elements;
  private String line;
  
  public List<String> getElements() {
    return elements;
  }
  
  public void setElements(List<String> elements) {
    this.elements = elements;
  }
  
  public String getLine() {
    return line;
  }
  
  public void setLine(String line) {
    this.line = line;
  }
}
