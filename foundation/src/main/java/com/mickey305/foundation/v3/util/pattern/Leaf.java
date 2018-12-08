package com.mickey305.foundation.v3.util.pattern;

public class Leaf<T> extends Component<T> {
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Constructor                                                                                                    //
  //===----------------------------------------------------------------------------------------------------------===//
  
  /**
   * コンストラクタ
   *
   * @param object コンテナ対象のオブジェクト
   */
  public Leaf(T object) {
    super(object);
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  
  /**
   * コンテナバインドメソッド
   *
   * @param targetParent バインド対象親コンテナ
   * @return 処理結果
   */
  @Override
  public boolean addParent(Composite<T> targetParent) {
    return CompositeBindAlgorithm.bind(targetParent, this);
  }
  
  /**
   * コンテナリリースメソッド
   *
   * @return 処理結果
   */
  @Override
  public boolean removeParent() {
    return CompositeBindAlgorithm.release(super.getParent(), this);
  }
}
