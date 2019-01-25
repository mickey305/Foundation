/*
 * Copyright (c) 2019. K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mickey305.foundation.v3.util.pattern;

import com.mickey305.foundation.v3.util.Assert;

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
      //assert child.getParent() == null;
      Assert.requireNull(child.getParent());
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
