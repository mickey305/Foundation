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

package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.gen.R;
import com.rits.cloning.Cloner;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class CollectionUtil {
  
  private CollectionUtil() {
  }
  
  /**
   * 要素をダウンキャストする
   *
   * @param subClass   キャスト先のクラス
   * @param castTarget キャスト対象のコレクション
   * @param output     キャスト後のコレクション
   * @param <SUPER>    キャスト対象の総称型
   * @param <SUB>      キャスト先の総称型
   * @return キャスト後のコレクション
   */
  public static <SUPER extends DownCastable, SUB extends SUPER>
  Collection<SUB> downCastElementTo(Class<SUB> subClass, Collection<SUPER> castTarget, Collection<SUB> output) {
    for (SUPER parent : castTarget) {
      if (!parent.getClass().isAssignableFrom(subClass) || parent.getClass().equals(subClass))
        throw new ClassCastException("castTarget#" + parent.toString() + " parameter rejected");
      output.add(parent.downcastTo(subClass));
    }
    return output;
  }
  
  /**
   * コレクション型から配列に変換する
   *
   * @param elements 変換対象のコレクション
   * @param dummy    要素の型パラメータ（未入力可）
   * @param <E>      要素の総称型
   * @return 変換後の配列
   */
  @SuppressWarnings("unchecked")
  public static <E> E[] toArray(Collection<E> elements, E... dummy) {
    Class<E> type = (Class<E>) dummy.getClass().getComponentType();
    return toArray(elements, type);
  }
  
  /**
   * コレクション型から配列に変換する
   * <p>要素クラスがnullの場合、{@link IllegalArgumentException}が発生する</p>
   *
   * @param elements    変換対象のコレクション
   * @param elementType 要素のクラス
   * @param <E>         要素の総称型
   * @return 変換後の配列
   * @throws IllegalArgumentException 引数例外
   */
  @SuppressWarnings("unchecked")
  public static <E> E[] toArray(Collection<E> elements, Class<E> elementType) {
    if (elementType == null)
      throw new IllegalArgumentException("elementType is rejected: reason NULL-VALUE");
    E[] result = (E[]) Array.newInstance(elementType, elements.size());
    return elements.toArray(result);
  }
  
  /**
   * @param collection
   * @param <E>
   * @return
   */
  public static <E> Collection<E> unmodifiableCollection(Collection<? extends E> collection) {
    return Collections.unmodifiableCollection(collection);
  }
  
  /**
   * @param list
   * @param <E>
   * @return
   */
  public static <E> List<E> unmodifiableList(List<? extends E> list) {
    return Collections.unmodifiableList(list);
  }
  
  /**
   * @param set
   * @param <E>
   * @return
   */
  public static <E> Set<E> unmodifiableSet(Set<? extends E> set) {
    return Collections.unmodifiableSet(set);
  }
  
  /**
   * @param map
   * @param <K>
   * @param <V>
   * @return
   */
  public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> map) {
    return Collections.unmodifiableMap(map);
  }
  
  /**
   * @param collection
   * @param <E>
   * @return
   */
  public static <E> E firstNonnullElementOf(Collection<? extends E> collection) {
    E target = null;
    for (E element : collection) {
      target = element;
      if (target != null)
        break;
    }
    if (target == null)
      throw new NullPointerException("all elements is null");
    return target;
  }
  
  /**
   * @param collection
   * @param <E>
   * @return
   */
  public static <E> Collection<E> protectedCollection(@Nonnull Collection<? extends E> collection) {
    Assert.requireNonNull(collection);
    if (collection.isEmpty())
      return new ProtectedCollection<>(collection);
    Class<?> clz = firstNonnullElementOf(collection).getClass();
    if (ClassUtil.isBoxing(clz) || R.knownImmutableClasses().contains(clz))
      return new ProtectedCollection<>(collection);
    // todo: 他のクローンライブラリで代替可能か検討する
    Cloner cloner = new Cloner();
    if (IS_DEBUG_MODE) Log.d("bef hash:" + collection.hashCode());
    collection = cloner.deepClone(collection);
    if (IS_DEBUG_MODE) Log.d("aft hash:" + collection.hashCode());
    return new ProtectedCollection<>(collection);
  }
  
  /**
   * @param list
   * @param <E>
   * @return
   */
  public static <E> List<E> protectedList(@Nonnull List<? extends E> list) {
    Assert.requireNonNull(list);
    if (list.isEmpty())
      return new ProtectedList<>(list);
    Class<?> clz = firstNonnullElementOf(list).getClass();
    if (ClassUtil.isBoxing(clz) || R.knownImmutableClasses().contains(clz))
      return new ProtectedList<>(list);
    // todo: 他のクローンライブラリで代替可能か検討する
    Cloner cloner = new Cloner();
    if (IS_DEBUG_MODE) Log.d("bef hash:" + list.hashCode());
    list = cloner.deepClone(list);
    if (IS_DEBUG_MODE) Log.d("aft hash:" + list.hashCode());
    return new ProtectedList<>(list);
  }
  
  /**
   * @param set
   * @param <E>
   * @return
   */
  public static <E> Set<E> protectedSet(@Nonnull Set<? extends E> set) {
    Assert.requireNonNull(set);
    if (set.isEmpty())
      return new ProtectedSet<>(set);
    Class<?> clz = firstNonnullElementOf(set).getClass();
    if (ClassUtil.isBoxing(clz) || R.knownImmutableClasses().contains(clz))
      return new ProtectedSet<>(set);
    // todo: 他のクローンライブラリで代替可能か検討する
    Cloner cloner = new Cloner();
    if (IS_DEBUG_MODE) Log.d("bef hash:" + set.hashCode());
    set = cloner.deepClone(set);
    if (IS_DEBUG_MODE) Log.d("aft hash:" + set.hashCode());
    return new ProtectedSet<>(set);
  }
  
  /**
   * @param map
   * @param <K>
   * @param <V>
   * @return
   */
  public static <K, V> Map<K, V> protectedMap(@Nonnull Map<? extends K, ? extends V> map) {
    Assert.requireNonNull(map);
    if (map.isEmpty())
      return new ProtectedMap<>(map);
    Class<?> kc = firstNonnullElementOf(map.keySet()).getClass();
    Class<?> vc = firstNonnullElementOf(map.values()).getClass();
    if ((ClassUtil.isBoxing(kc) || R.knownImmutableClasses().contains(kc))
        && (ClassUtil.isBoxing(vc) || R.knownImmutableClasses().contains(vc)))
      return new ProtectedMap<>(map);
    // todo: 他のクローンライブラリで代替可能か検討する
    Cloner cloner = new Cloner();
    if (IS_DEBUG_MODE) Log.d("bef hash:" + map.hashCode());
    map = cloner.deepClone(map);
    if (IS_DEBUG_MODE) Log.d("aft hash:" + map.hashCode());
    return new ProtectedMap<>(map);
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Innerclass                                                                                                     //
  //===----------------------------------------------------------------------------------------------------------===//
  private static class ProtectedCollection<E> implements Collection<E> {
    private Collection<? extends E> collection;
    
    public ProtectedCollection(Collection<? extends E> c) {
      this.setCollection(c);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
      return this.getCollection().size();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
      return this.getCollection().isEmpty();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
      return this.getCollection().contains(o);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
      return this.getCollection().containsAll(c);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E e) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
      throw new UnsupportedOperationException();
    }
    
    Collection<? extends E> getCollection() {
      return collection;
    }
    
    private void setCollection(Collection<? extends E> collection) {
      this.collection = collection;
    }
  }
  
  private static class ProtectedList<E> extends ProtectedCollection<E> implements List<E> {
    private List<? extends E> list;
    
    ProtectedList(List<? extends E> c) {
      super(c);
      this.setList(c);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
      return this.getList().indexOf(o);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
      return this.getList().lastIndexOf(o);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int index) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, E element) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(int index) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator() {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator(int index) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
      throw new UnsupportedOperationException();
    }
    
    List<? extends E> getList() {
      return list;
    }
    
    private void setList(List<? extends E> list) {
      this.list = list;
    }
  }
  
  private static class ProtectedSet<E> extends ProtectedCollection<E> implements Set<E> {
    ProtectedSet(Set<? extends E> c) {
      super(c);
    }
  }
  
  private static class ProtectedMap<K, V> implements Map<K, V> {
    private Map<? extends K, ? extends V> map;
    
    private void setMap(Map<? extends K, ? extends V> map) {
      this.map = map;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
      return this.getMap().size();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
      return this.getMap().isEmpty();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {
      return this.getMap().containsKey(key);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {
      return this.getMap().containsValue(value);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object key) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object key) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<K> keySet() {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<V> values() {
      throw new UnsupportedOperationException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
      throw new UnsupportedOperationException();
    }
    
    ProtectedMap(Map<? extends K, ? extends V> map) {
      this.setMap(map);
    }
    
    Map<? extends K, ? extends V> getMap() {
      return map;
    }
  }
}
