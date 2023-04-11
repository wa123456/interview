package com.lv.juc.collection.map;

public class MyHashMap<K,V> implements MyMap<K,V> {

    final static int DEFAULT_CAPACITY = 16;
    final static float DEFAULT_LOAD_FACTOR = 0.75f;

    int capacity;
    float loadFactor;
    int size = 0;
    int length = 0;
    Entry<K,V>[] table;

    class Entry<K, V>{
        K k;
        V v;
        Entry<K,V> next;

        public Entry(K k, V v, Entry<K, V> next){
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    public MyHashMap(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor){
        this.capacity = upperMinPowerOf2(capacity);
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
    }


    private static int upperMinPowerOf2(int n){
        int power = 1;
        while(power <= n){
            power *= 2;
        }
        return power;
    }



    @Override
    public V put(K k, V v) {
        // ͨ��hashcodeɢ��
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        // �ж�table[index]�Ƿ��Ѵ���Ԫ��
        // ��
        if(current != null){
            // ���������Ƿ������key, �����滻�ҷ��ؾ�ֵ
            while(current != null){
                if(current.k == k){
                    V oldValue = current.v;
                    current.v = v;
                    return oldValue;
                }
                current = current.next;
            }
            // û����ʹ��ͷ�巨
            table[index] = new Entry<K, V>(k, v, table[index]);
            size++;
            length++;
            return null;
        }
        // table[index]Ϊ�� ֱ�Ӹ�ֵ
        table[index] = new Entry<K, V>(k, v, null);
        size++;
        length++;
        return null;
    }

    @Override
    public V get(K k) {
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        // ��������
        while(current != null){
            if(current.k == k){
                //length--;
                return current.v;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public V remove(K k) {
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        // ���ֱ��ƥ���һ���ڵ�
        if(current.k == k){
            table[index] = null;
            size--;
            length--;
            return current.v;
        }
        // ��������ɾ���ڵ�
        while(current.next != null){
            if(current.next.k == k){
                V oldValue = current.next.v;
                current.next = current.next.next;
                length--;
                size--;
                return oldValue;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void clear() {
        //��������Ȳ�д��
    }
}
