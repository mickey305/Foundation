package com.mickey305.foundation.v3.util.pattern;

public abstract class Component<T> {
    private T object;
    private Component<T> parent;
    private SearchLogic<Component<T>> callback;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    public Component(T object) {
        this.setObject(object);
        this.setCallback(new SearchLogic<Component<T>>() {
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

    public Component<T> getRoot() {
        Component<T> root = this;
        Component<T> parent = this;
        while (parent != null) {
            root = parent;
            parent = parent.getParent();
        }
        return root;
    }

    public boolean belongsTo(Component<T> targetComponent) {
        // 自分自身には属していない
        if (this.equals(targetComponent))
            return false;

        Component<T> component = this;
        while(component != null) {
            // 検索ロジック
            if (this.getCallback().search(component, targetComponent))
                return true;
            component = component.getParent();
        }
        return false;
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Accessor                                                                                                       //
    //===----------------------------------------------------------------------------------------------------------===//
    public T getObject() {
        return this.object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Component<T> getParent() {
        return this.parent;
    }

    public SearchLogic<Component<T>> getCallback() {
        return callback;
    }

    public void setCallback(SearchLogic<Component<T>> callback) {
        this.callback = callback;
    }

    protected void setParent(Component<T> parent) {
        this.parent = parent;
    }
}
