package com.mickey305.foundation.v3.util.pattern;

public interface SearchLogic<T extends Component> {
    /**
     * 二要素ノード検索ロジック
     * @param component 対象ノードと比較する内部ノード
     * @param targetComponent 対象ノード（検索対象ノード）：検索系メソッド上で引数として取得するデータ
     * @return 検索結果
     */
    boolean search(T component, T targetComponent);
}
