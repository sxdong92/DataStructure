package cache;

import java.util.HashMap;

public class LRUCache {
    private int capacity;
    private HashMap<Integer, DoubleLinkedListNode> map = new HashMap<Integer, DoubleLinkedListNode>();
    private DoubleLinkedListNode head;
	private DoubleLinkedListNode end;
	private int len;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        len = 0;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            DoubleLinkedListNode latest = map.get(key);
            removeNode(latest);
            setHead(latest);
            return latest.val;
        }
        else return -1;
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
			DoubleLinkedListNode oldNode = map.get(key);
			oldNode.val = value;
			removeNode(oldNode);
			setHead(oldNode);
		}
		else {
		    DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, value);
		    if (len < capacity) {
				setHead(newNode);
				map.put(key, newNode);
				len++;
			} 
			else {
				map.remove(end.key);
				end = end.pre;
				if (end != null) end.next = null;
 
				setHead(newNode);
				map.put(key, newNode);
			}
		}
    }
    
    public void removeNode(DoubleLinkedListNode node) {
		DoubleLinkedListNode cur = node;
		DoubleLinkedListNode pre = cur.pre;
		DoubleLinkedListNode post = cur.next;
 
		if (pre != null) pre.next = post;
		else head = post;
 
		if (post != null) post.pre = pre;
		else end = pre;
	}
	
	public void setHead(DoubleLinkedListNode node) {
		node.next = head;
		node.pre = null;
		
		if (head != null) head.pre = node;
		head = node;
		if (end == null) end = node;
	}
	
	
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(10);
		cache.set(1991, 1991);
		cache.set(1992, 1992);
		cache.set(1998, 1998);
		cache.set(2000, 2000);
		cache.set(2008, 2008);
		System.out.println(cache.len);
	}
}

class DoubleLinkedListNode {
	public int val;
	public int key;
	public DoubleLinkedListNode pre;
	public DoubleLinkedListNode next;
 
	public DoubleLinkedListNode(int key, int value) {
		val = value;
		this.key = key;
	}
}
