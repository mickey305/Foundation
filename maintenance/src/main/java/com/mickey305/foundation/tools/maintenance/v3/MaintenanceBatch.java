package com.mickey305.foundation.tools.maintenance.v3;

public class MaintenanceBatch {
  /*
   *
   *
   */
  public static void main(String[] args) {
    Workflow workflow = Workflow.getInstance();
    workflow.updateGenerationClasses();
  }
}
