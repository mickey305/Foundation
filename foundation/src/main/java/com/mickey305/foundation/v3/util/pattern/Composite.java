package com.mickey305.foundation.v3.util.pattern;

import com.mickey305.foundation.v3.compat.stream.Supplier;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

public class Composite<T> extends Component<T> {
    private Collection<Component<T>> children;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    /**
     * コンストラクタ
     * @param object コンテナ対象のオブジェクト
     * @param bindDataStructure バインド用コレクション供給インターフェース
     * @param <E> コレクションの総称型
     */
    public <E extends Collection<Component<T>>> Composite(T object, Supplier<E> bindDataStructure) {
        super(object);
        this.setChildren(bindDataStructure.get());
        this.getChildren().clear();
    }

    /**
     * コンストラクタ
     * @param object コンテナ対象のオブジェクト
     */
    public Composite(T object) {
        this(object, new Supplier<Collection<Component<T>>>() {
            @Override
            public Collection<Component<T>> get() {
                return new ArrayList<>();
            }
        });
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Methods                                                                                                        //
    //===----------------------------------------------------------------------------------------------------------===//
    /**
     * コンテナバインドメソッド
     * @param targetChild バインド対象子コンテナ
     * @return 処理結果
     */
    public boolean add(Component<T> targetChild) {
        return CompositeBindAlgorithm.bind(this, targetChild);
    }

    /**
     * コンテナリリースメソッド
     * @param targetChild リリース対象子コンテナ
     * @return 処理結果
     */
    public boolean remove(Component<T> targetChild) {
        return CompositeBindAlgorithm.release(this, targetChild);
    }

    /**
     * コンテナリリースメソッド
     * @param targetChildren リリース対象子コンテナコレクション
     * @return 処理結果
     */
    public boolean removeAll(Collection<Component<T>> targetChildren) {
        for (Component<T> targetChild: targetChildren) {
            if (!this.remove(targetChild))
                return false;
        }
        return true;
    }

    /**
     * コンテナリリースメソッド
     * 全子要素を削除する
     * @return 処理結果
     */
    public boolean removeAll() {
        return this.removeAll(this.getChildren());
    }

    /**
     * コンテナバインドメソッド
     * @param targetParent バインド対象親コンテナ
     * @return 処理結果
     */
    @Override
    public boolean addParent(Composite<T> targetParent) {
        return CompositeBindAlgorithm.bind(targetParent, this);
    }

    /**
     * コンテナリリースメソッド
     * @return 処理結果
     */
    @Override
    public boolean removeParent() {
        return CompositeBindAlgorithm.release(super.getParent(), this);
    }

    /**
     * 全子要素を削除する
     * @deprecated 互換性を維持するために残存しているメソッドですが将来的に削除されます。
     *             {@link #removeAll()}への変更を推奨します。
     */
    @Deprecated
    public void clear() {
        for (Component<T> child: this.getChildren()) {
            child.setParent(null);
        }
        this.getChildren().clear();
    }

    /**
     * コンテナ検索用メソッド
     * <p>当コンテナが保持する子コンテナ内で検索対象コンテナを検出した場合は、trueを返却し、
     * それ以外の場合は、falseを返却する。当コンテナが保持する子コンテナが他コンテナとバインドしている場合は、
     * 最も子となるコンテナまで検索する。自コンテナが引数の場合は、falseを返却する</p>
     * @param targetComponent 検索対象コンテナ
     * @return 検索結果
     */
    public boolean contains(Component<T> targetComponent) {
        // 自分自身を包含しない
        if (this.equals(targetComponent))
            return false;

        // 全子ノードを検索（自分自身は上記で排除済みのため、検索されない）
        return this.contains(this, targetComponent);
    }

    /**
     * コンテナ検索用メソッド
     * <p>内部コンテナが保持する子コンテナ内で検索対象コンテナを検出した場合は、trueを返却し、
     * それ以外の場合は、falseを返却する。内部コンテナが保持する子コンテナが他コンテナとバインドしている場合は、
     * 最も子となるコンテナまで検索する</p>
     * @param composite 検索対象コンテナと比較する内部コンテナ
     * @param targetComponent 検索対象コンテナ
     * @return 検索結果
     */
    private boolean contains(Composite<T> composite, Component<T> targetComponent) {
        Deque<Component<T>> stack = new ArrayDeque<>();
        Component<T> component = composite;
        do {
            // 検索ロジック
            if (component.getCallback().search(component, targetComponent))
                return true;
            if (Composite.class.isAssignableFrom(component.getClass())
                    || Composite.class.isInstance(component)) {
                Composite<T> castedComponent = (Composite<T>) component;
                stack.addAll(castedComponent.getChildren());
            }
            component = stack.pollFirst();
        } while (component != null);
        return false;
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Accessor                                                                                                       //
    //===----------------------------------------------------------------------------------------------------------===//
    protected Collection<Component<T>> getChildren() {
        return this.children;
    }

    private void setChildren(Collection<Component<T>> children) {
        this.children = children;
    }
}
