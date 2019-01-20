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

package com.mickey305.foundation.v3.util.collections;

import com.mickey305.foundation.v3.util.Log;

import javax.annotation.Nonnull;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * LRUキャッシュ用マップクラス
 * <p>
 * StreamAPIのメソッドに対するLRUは未対応のため注意する。</p>
 * @param <K> キークラス型（ユニークキー）
 * @param <V> 値クラス型
 */
public class LRUMap<K, V> extends LinkedHashMap<K, V> implements ILRU<K, V>, ILRUMap<K, V> {
  private static final long serialVersionUID = 1709758731307230132L;
  private final int capacity;
  private final CollectionsHolder holder;
  
  public static <K, V> LRUMap<K, V> of(int capacity) {
    return new LRUMap<>(capacity);
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Constructor                                                                                                    //
  //===----------------------------------------------------------------------------------------------------------===//
  
  protected LRUMap(int capacity) {
    super(capacity);
    this.capacity = capacity;
    this.holder = new CollectionsHolder();
    this.holder.entries = new LinkedEntrySet();
    this.holder.keys = new LinkedKeySet();
    this.holder.values = new LinkedValues();
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  
  protected CollectionsHolder getHolder() {
    return holder;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest)  {
    return size() > capacity;
  }
  
  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Set<K> keySet() {
    return holder.keys;
  }
  
  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Collection<V> values() {
    return holder.values;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public V get(Object key) {
    final V value = super.get(key);
    if (value != null) {
      refreshData((K) key, value);
    }
    return value;
  }
  
  /**
   *
   * @param key
   * @param defaultValue
   * @return
   */
  public V getOrDefault(Object key, V defaultValue) {
    final V value = this.get(key);
    return (value == null) ? defaultValue : value;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsKey(Object key) {
    return super.containsKey(key);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsValue(Object value) {
    for (Map.Entry<K,V> e : super.entrySet()) {
      final V v = e.getValue();
      if (v == value || (value != null && value.equals(v))) {
        return true;
      }
    }
    return false;
  }
  
  /**
   *
   * @param key
   * @param oldValue
   * @param newValue
   * @return
   */
  public boolean replace(K key, V oldValue, V newValue) {
    for (Map.Entry<K,V> e : super.entrySet()) {
      final K k = e.getKey();
      final V v = e.getValue();
      if ((k == key || (key != null && key.equals(k)))
          && (v == oldValue || (oldValue != null && oldValue.equals(v)))) {
        refreshData(key, newValue);
        return true;
      }
    }
    return false;
  }

  /**
   *
   * @param key
   * @param value
   * @return
   */
  public V replace(K key, V value) {
    if (this.containsKey(key)) {
      return refreshData(key, value);
    }
    return null;
  }
  
  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Set<Map.Entry<K,V>> entrySet() {
    return holder.entries;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public V refreshData(K key, V value) {
    if (IS_DEBUG_MODE) {
      Log.d("element references method called. key=" + Objects.toString(key));
    }
    final V v = remove(key);
    put(key, value);
    if (IS_DEBUG_MODE && !Objects.equals(value, v)) {
      Log.d("value changed. from " + Objects.toString(v) + " to " + Objects.toString(value));
    }
    return v;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshData(K key) {
    this.refreshData(key, this.get(key));
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Innerclass                                                                                                     //
  //===----------------------------------------------------------------------------------------------------------===//
  
  protected final class CollectionsHolder {
    private AbstractSet<Map.Entry<K,V>> entries;
    private AbstractSet<K> keys;
    private AbstractCollection<V> values;
  
    public AbstractSet<Map.Entry<K, V>> getEntries() {
      return entries;
    }
  
    public void setEntries(AbstractSet<Map.Entry<K, V>> entries) {
      this.entries = entries;
    }
  
    public AbstractSet<K> getKeys() {
      return keys;
    }
  
    public void setKeys(AbstractSet<K> keys) {
      this.keys = keys;
    }
  
    public AbstractCollection<V> getValues() {
      return values;
    }
  
    public void setValues(AbstractCollection<V> values) {
      this.values = values;
    }
  }
  
  private final class EntryProxy implements Map.Entry<K,V> {
    private final Map.Entry<K,V> entry;
    
    EntryProxy(Map.Entry<K, V> entry) {
      this.entry = entry;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public K getKey() {
      final K k = entry.getKey();
      final V v = entry.getValue();
      LRUMap.this.refreshData(k, v);
      return k;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public V getValue() {
      final K k = entry.getKey();
      final V v = entry.getValue();
      LRUMap.this.refreshData(k, v);
      return v;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public V setValue(V value) {
      final K k = entry.getKey();
      final V v = entry.getValue();
      LRUMap.this.refreshData(k, v);
      return entry.setValue(value);
    }
  }
  
  protected class LinkedEntrySet extends AbstractSet<Map.Entry<K,V>> {
    /**
     * {@inheritDoc}
     */
    @Override
    public final int size() {
      return LRUMap.this.size();
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public final void clear() {
      LRUMap.this.clear();
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public final Iterator<Map.Entry<K,V>> iterator() {
      final Set<Map.Entry<K,V>> set = new LinkedHashSet<>();
      for (Map.Entry<K,V> e : LRUMap.super.entrySet()) {
        set.add(new EntryProxy(e));
      }
      return new LinkedEntryIteratorProxy(set.iterator());
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public final boolean contains(Object o) {
      if (o instanceof Map.Entry) {
        final Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
        final Object key = e.getKey();
        final Object value = e.getValue();
        for (Map.Entry<K, V> entry : this) {
          if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
            return true;
          }
        }
      }
      return false;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean remove(Object o) {
      if (this.contains(o)) {
        if (o instanceof Map.Entry) {
          final Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
          return LRUMap.this.remove(e.getKey()) != null;
        }
      }
      return false;
    }
  }
  
  private final class LinkedEntryIteratorProxy implements Iterator<Map.Entry<K,V>> {
    private final Iterator<Map.Entry<K,V>> it;
  
    LinkedEntryIteratorProxy(Iterator<Map.Entry<K,V>> it) {
      this.it = it;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
      return it.hasNext();
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public final Map.Entry<K,V> next() {
      return it.next();
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
      it.remove();
    }
  }
  
  protected class LinkedKeySet extends AbstractSet<K> {
    /**
     * {@inheritDoc}
     */
    @Override
    public final int size() {
      return LRUMap.this.size();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final void clear() {
      LRUMap.this.clear();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public final Iterator<K> iterator() {
      final Set<Map.Entry<K,V>> set = new LinkedHashSet<>();
      for (Map.Entry<K,V> e : LRUMap.super.entrySet()) {
        set.add(new EntryProxy(e));
      }
      return new LinkedKeyIteratorProxy(set.iterator());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean contains(Object o) {
      return LRUMap.this.containsKey(o);
      
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean remove(Object key) {
      return LRUMap.this.remove(key) != null;
    }
  }
  
  private final class LinkedKeyIteratorProxy implements Iterator<K> {
    private final Iterator<Map.Entry<K,V>> it;
    
    LinkedKeyIteratorProxy(Iterator<Map.Entry<K,V>> it) {
      this.it = it;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
      return it.hasNext();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final K next() {
      return it.next().getKey();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
      it.remove();
    }
  }
  
  protected class LinkedValues extends AbstractCollection<V> {
    /**
     * {@inheritDoc}
     */
    @Override
    public final int size() {
      return LRUMap.this.size();
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public final void clear() {
      LRUMap.this.clear();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final Iterator<V> iterator() {
      final Set<Map.Entry<K,V>> set = new LinkedHashSet<>();
      for (Map.Entry<K,V> e : LRUMap.super.entrySet()) {
        set.add(new EntryProxy(e));
      }
      return new LinkedValueIteratorProxy(set.iterator());
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean contains(Object o) {
      return LRUMap.this.containsValue(o);
    }
  }
  
  private final class LinkedValueIteratorProxy implements Iterator<V> {
    private final Iterator<Map.Entry<K,V>> it;
  
    LinkedValueIteratorProxy(Iterator<Map.Entry<K,V>> it) {
      this.it = it;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
      return it.hasNext();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final V next() {
      return it.next().getValue();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
      it.remove();
    }
  }
}
