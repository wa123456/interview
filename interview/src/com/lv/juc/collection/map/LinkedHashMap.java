/*
package com.lv.juc.collection.map;

import sun.reflect.generics.visitor.Visitor;

import java.util.HashMap;
import java.util.function.BiConsumer;

*/
/**
 * <p>
 * LinkedHashMap,�������������н�㶼��������������
 * </p>
 *
 * @since: 2022/7/21 9:35
 * @author: Eureka
 *//*

public class MyLinkedHashMap<K, V> extends HashMap<K, V> {

    */
/* ��Ҫ��¼�����ͷβ��� *//*

    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    */
/**
     * LinkedHashMap���
     *//*

    protected static class LinkedNode<K, V> extends Node<K, V> {
        // ָ����һ������ָ���ָ����һ������ָ��
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        // ��ͷ��㿪ʼ����
        LinkedNode<K, V> node = first;
        while (node != null) {
            action.accept(node.key, node.value);
            node = node.next;
        }
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }
    }

    */
/**
     * ������Ҫ�ڴ�������ʱ����ָ�뽫�䴮����
     *//*

    @Override
    protected Node<K, V> createNode(K k, V v, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode<>(k, v, parent);
        // ���ͷ���
        if (first == null) {
            first = last = node;
        } else {
            // ��ͷ���
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    */
/**
     * ��ս����Ҫ��first��lastȥ������Ȼ�������GC
     *//*

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }
}
*/
