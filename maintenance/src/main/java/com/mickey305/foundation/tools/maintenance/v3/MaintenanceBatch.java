package com.mickey305.foundation.tools.maintenance.v3;

public class MaintenanceBatch {
  /*
   * maintenance procedure entry point
   *
   */
  public static void main(String[] args) {
    Workflow workflow = Workflow.getInstance().setArgs(args).buildPath();
    workflow.updateGenerationClasses();
  }
}
