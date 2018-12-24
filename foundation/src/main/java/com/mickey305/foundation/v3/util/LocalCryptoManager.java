package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;
import java.util.UUID;

public class LocalCryptoManager extends AbstractCryptoManager {
  private LocalCryptoManager() {
    super();
  }
  
  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  protected String createSaltKey() {
    // UUIDをSaltキーとして実装
    final UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
  
  public static LocalCryptoManager getInstance() {
    return LocalCryptoManagerHolder.Instance;
  }
  
  private static final class LocalCryptoManagerHolder {
    private static final LocalCryptoManager Instance = new LocalCryptoManager();
  }
}
