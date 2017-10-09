package com.mickey305.foundation.v3.util;

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
    public boolean add(Component<T> component) {
        component.setParent(this);
        return this.getChildren().add(component);
    }

    public void removeChildren() {
        this.getChildren().clear();
    }

    public boolean contains(Component<T> targetComponent) {
        // 自分自身を包含しない
        if (this.equals(targetComponent))
            return false;

        // 全ノードを再帰的に検索
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
