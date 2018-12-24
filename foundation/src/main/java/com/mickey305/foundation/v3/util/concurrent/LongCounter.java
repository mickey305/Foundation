package com.mickey305.foundation.v3.util.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public final class LongCounter implements ICounter {
  private final AtomicLong counter;
  
  public LongCounter() {
    counter = new AtomicLong(0);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public long count() {
    return this.counter.getAndIncrement();
  }
}
