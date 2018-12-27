/*
 * Copyright (c) 2018. K.Misaki
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

public abstract class Component<T> {
  private T object;
  private Composite<T> parent;
  private SearchLogic<Component<T>> callback;
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Constructor                                                                                                    //
  //===----------------------------------------------------------------------------------------------------------===//
  
  /**
   * コンストラクタ
   *
   * @param object コンテナ対象のオブジェクト
   */
  public Component(T object) {
    this.setObject(object);
    this.setCallback(new SearchLogic<Component<T>>() {
      /**
       * {@inheritDoc}
       */
      @Override
      public boolean search(Component<T> component, Component<T> targetComponent) {
        // デフォルト検索ロジック
        return component.equals(targetComponent);
      }
    });
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  public Component<T> compare(SearchLogic<Component<T>> callback) {
    this.setCallback(callback);
    return this;
  }
  
  /**
   * ルートコンテナを取得する
   * <p>他コンテナとバインドされているコンテナ群の中で、最も親となるコンテナ（ルートコンテナ）まで
   * 芋づる式にコンテナを走査し、取得する。</p>
   *
   * @return ルートコンテナ
   */
  public Component<T> getRoot() {
    Component<T> root = this;
    Component<T> parent = this;
    while (parent != null) {
      root = parent;
      parent = parent.getParent();
    }
    return root;
  }
  
  /**
   * コンテナ検索用メソッド
   * <p>ルートコンテナまでの検索途中で検索対象コンテナを検出した場合は、trueを返却し、
   * それ以外の場合は、falseを返却する。自コンテナが引数の場合は、falseを返却する</p>
   *
   * @param targetComponent 検索対象コンテナ
   * @return 検索結果
   */
  public boolean belongsTo(Component<T> targetComponent) {
    // 自分自身には属していない
    if (this.equals(targetComponent))
      return false;
    
    Component<T> component = this;
    while (component != null) {
      // 検索ロジック
      if (this.getCallback().search(component, targetComponent))
        return true;
      component = component.getParent();
    }
    return false;
  }
  
  /**
   * コンテナバインドメソッド
   *
   * @param targetParent バインド対象親コンテナ
   * @return 処理結果
   */
  public abstract boolean addParent(Composite<T> targetParent);
  
  /**
   * コンテナリリースメソッド
   *
   * @return 処理結果
   */
  public abstract boolean removeParent();
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Accessor                                                                                                       //
  //===----------------------------------------------------------------------------------------------------------===//
  public T getObject() {
    return this.object;
  }
  
  public void setObject(T object) {
    this.object = object;
  }
  
  public Composite<T> getParent() {
    return this.parent;
  }
  
  public SearchLogic<Component<T>> getCallback() {
    return callback;
  }
  
  public void setCallback(SearchLogic<Component<T>> callback) {
    this.callback = callback;
  }
  
  protected void setParent(Composite<T> parent) {
    this.parent = parent;
  }
}
