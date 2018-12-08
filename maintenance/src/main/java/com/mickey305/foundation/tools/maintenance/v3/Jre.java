package com.mickey305.foundation.tools.maintenance.v3;

public enum Jre {
  /**
   *
   */
  SE7(1.7),
  
  /**
   *
   */
  SE8(1.8),
  
  /**
   *
   */
  SE9(9.0),
  
  /**
   *
   */
  SE10(10.0),
  
  /**
   *
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
