package com.mickey305.foundation.v3.util.pattern;

class CompositeBindAlgorithm {
  
  private CompositeBindAlgorithm() {
  }
  
  /**
   * コンテナバインドメソッド
   *
   * @param parent バインド対象親コンテナ
   * @param child  バインド対象子コンテナ
   * @return 処理結果
   */
  static <T> boolean bind(Composite<T> parent, Component<T> child) {
    // childに任意の親ノードが存在する場合は、その親ノードからchildを削除する
    Composite<T> parentOfChild = child.getParent();
    if (parentOfChild != null) {
      if (!parentOfChild.remove(child))
        return false;
    }
    if (parent.getChildren().add(child)) {
      assert child.getParent() == null;
      child.setParent(parent);
      return true;
    }
    if (parentOfChild != null)
      parentOfChild.add(child);
    return false;
  }
  
  /**
   * コンテナリリースメソッド
   *
   * @param parent リリース対象親コンテナ
   * @param child  リリース対象子コンテナ
   * @return 処理結果
   */
  static <T> boolean release(Composite<T> parent, Component<T> child) {
    // childの親ノードからchildを削除できた場合に、ロジックを実行する
    Composite<T> parentOfChild = child.getParent();
    if (parentOfChild != null && parentOfChild.equals(parent)) {
      if (parent.getChildren().remove(child)) {
        child.setParent(null);
        return true;
      }
    }
    return false;
  }
}
