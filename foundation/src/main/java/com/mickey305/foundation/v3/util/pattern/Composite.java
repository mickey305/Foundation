package com.mickey305.foundation.v3.util.pattern;

import com.mickey305.foundation.v3.compat.stream.Supplier;

import java.util.ArrayList;
import java.util.Collection;

public class Composite<T> extends Component<T> {
    private Collection<Component<T>> children;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    public <E extends Collection<Component<T>>> Composite(T object, Supplier<E> bindDataStructure) {
        super(object);
        this.setChildren(bindDataStructure.get());
        this.getChildren().clear();
    }

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
    public boolean add(Component<T> targetChild) {
        // targetChildに任意の親ノードが存在する場合は、その親ノードからtargetChildを削除する
        Composite<T> targetParent = (Composite<T>) targetChild.getParent();
        if (targetParent != null) {
            boolean status = targetParent.remove(targetChild);
            assert status;
        }

        boolean added = this.getChildren().add(targetChild);
        if (added) {
            assert targetChild.getParent() == null;
            targetChild.setParent(this);
        }
        return added;
    }

    public boolean remove(Component<T> targetChild) {
        boolean removed = this.getChildren().remove(targetChild);
        if (removed) targetChild.setParent(null);
        return removed;
    }

    public boolean removeAll(Collection<Component<T>> targetChildren) {
        for (Component<T> targetChild: targetChildren) {
            if (!this.remove(targetChild))
                return false;
        }
        return true;
    }

    public void clear() {
        for (Component<T> child: this.getChildren()) {
            child.setParent(null);
        }
        this.getChildren().clear();
    }

    public boolean contains(Component<T> targetComponent) {
        // 自分自身を包含しない
        if (this.equals(targetComponent))
            return false;

        // 全子ノードを再帰的に検索（自分自身は上記で排除済みのため、検索されない）
        return contains(this, targetComponent);
    }

    private static <T> boolean contains(final Composite<T> composite, final Component<T> targetComponent) {
        // 検索ロジック
        if (composite.getCallback().search(composite, targetComponent))
            return true;
        for (Component<T> child: composite.getChildren()) {
            // 検索ロジック
            if (child instanceof Leaf && child.getCallback().search(child, targetComponent))
                return true;
            if (child instanceof Composite) {
                Composite<T> castedChild = (Composite<T>) child;
                boolean status = contains(castedChild, targetComponent);
                if (status)
                    return true;
            }
        }
        return false;
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Accessor                                                                                                       //
    //===----------------------------------------------------------------------------------------------------------===//
    public Collection<Component<T>> getChildren() {
        return this.children;
    }

    private void setChildren(Collection<Component<T>> children) {
        this.children = children;
    }
}
