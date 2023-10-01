package AlgotithmExercise.DataStructure;

import java.util.*;


public class LRU {

    private int capacity = 0;
    private Map<Integer, Integer> cache;
    private TreeMap<UsageCount, Integer> tm;

    public LRU(int capacity) {
        cache = new HashMap<>();
        tm = new TreeMap<>(new Comparator<UsageCount>() {
            @Override
            public int compare(UsageCount p1 , UsageCount p2) {
                return p1.usage - p2.usage;
            }
        });
        this.capacity = capacity;
    }

    public LRU(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        cache = new HashMap<>();
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            UsageCount p = new UsageCount(key, 0);
            p.usage = tm.get(p) +1;
            tm.put(p, p.usage);
        } else {
            if(cache.size() >= capacity) {
                UsageCount p = tm.firstEntry().getKey();
                tm.remove(p);
                cache.remove(p.key);
                capacity--;
            }
            cache.put(key, value);
            tm.put(new UsageCount(key, 0), 0);
            capacity++;

        }
    }

    public int get(int key) {
        if(!cache.containsKey(key)) return -1;
        else {
            UsageCount p = new UsageCount(key, 0);
            p.usage = tm.get(p) +1;
            tm.put(p, p.usage);
            return cache.get(key);
        }
    }

    class UsageCount {
        int key;
        int usage;

        public UsageCount(int key, int usage) {
            this.key = key;
            this.usage = usage;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UsageCount pair = (UsageCount) o;
            return key == pair.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

    }


    /**
     *
     * https://leetcode.cn/problems/lru-cache/solutions/259678/lruhuan-cun-ji-zhi-by-leetcode-solution/
     * LRU： 就是每访问一次某个节点就把他放到链表的最前面，超出容量时淘汰最尾部的数据
     *
     * LinkedHashMap直接就可以当做LRU cache来使用
     *
     */

    public class LRUCache {
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
            public DLinkedNode() {}
            public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            }
            else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }

}
