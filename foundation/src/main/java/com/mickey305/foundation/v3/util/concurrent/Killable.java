package com.mickey305.foundation.v3.util.concurrent;

public interface Killable {
    /**
     * プロセスの終了処理
     */
    void shutdown();

    /**
     * プロセスの生存確認
     * @return 生存している場合はtrueで、そうでない場合はfalseを返却する
     */
    boolean isAlive();
}
