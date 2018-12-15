package com.mickey305.foundation.tools.maintenance.v3;

public enum Jre {
  /**
   * Java SE7
   */
  SE7(1.7),
  
  /**
   * Java SE8
   */
  SE8(1.8),
  
  /**
   * Java SE9
   */
  SE9(9.0),
  
  /**
   * Java SE10
   */
  SE10(10.0),
  
  /**
   * Java SE11
   */
  SE11(11.0);
  
  Jre(double version) {
    this.version = version;
  }
  
  public double getVersion() {
    return version;
  }
  
  private final double version;
}
