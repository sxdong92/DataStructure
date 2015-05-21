package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class MyHeap<T> {
    ArrayList<T> items;
    int cursor; //���ڼ���
    private Comparator comparator;
     
    public MyHeap() {
        items = new ArrayList<T>();
        cursor = -1;
    }
    
    public MyHeap(int size) {
        items = new ArrayList<T>(size);
        cursor = -1;
    }
    
    public MyHeap(Comparator comparator) {
        items = new ArrayList<T>();
        cursor = -1;
        this.comparator = comparator;
    }
    
    /**
     * ���Ʋ���
     * @param index ������Ԫ�ص���ʼλ�á�
     */
    void siftUp(int index) {
        T intent = items.get(index);
        while(index > 0) {
            int pindex = (index - 1) / 2;
            T parent = items.get(pindex);
            //���Ƶ��������ȸ��ڵ��
            if(compare(intent, parent) > 0) { 
                items.set(index, parent);
                index = pindex;
            }
            else break;
        }
        items.set(index, intent);
    }
    
    /**
     * ���Ʋ���
     * @param index �����Ƶ�Ԫ�ص���ʼλ��
     */
    void siftDown(int index) {
        T intent = items.get(index);
        int l_index = 2 * index + 1;
        while(l_index < items.size()) {
            T maxChild = items.get(l_index);
            int max_index = l_index;
             
            int r_index = l_index + 1;
            if(r_index < items.size()) {
                T rightChild = items.get(r_index);
                if(compare(rightChild, maxChild) > 0) {
                    maxChild = rightChild;
                    max_index = r_index;
                }
            }
             
            if(compare(maxChild, intent) > 0) {
                items.set(index, maxChild);
                index = max_index;
                l_index = index * 2 + 1 ;
            }
            else break;
        }
        items.set(index, intent);
    }
     
    public void add(T item) {
        //����ӵ����
        items.add(item);
        //ѭ�����ƣ�������ع�
        siftUp(items.size() - 1);
    }
    
    public T peek() {
    	if(items.isEmpty()) {
            throw new NoSuchElementException();
        }
    	return items.get(0);
    }
    
    public T poll() {
        if(items.isEmpty()) {
            throw new NoSuchElementException();
        }
        //�Ȼ�ȡ�����ڵ�
        T maxItem = items.get(0);
        T lastItem = items.remove(items.size() - 1);
        if(items.isEmpty()) {
            return lastItem;
        }
        //��β���Ľڵ���ö��������ƣ�����ع�
        items.set(0, lastItem);
        siftDown(0);
        return maxItem;
    }
    
    protected int compare(T element, T other) {
        if(comparator == null) {
            Comparable e = (Comparable) element;
            Comparable o = (Comparable) other;
            return o.compareTo(e);
        } 
        else return comparator.compare(other, element);
    }
    
    public T next() {
        if(cursor < 0 || cursor == (items.size() - 1)) {
            return null;
        }
        cursor++;
        return items.get(cursor);
    }
     
    public T first() {
        if(items.size() == 0) return null;
        cursor = 0;
        return items.get(0);
    }
     
    public boolean isEmpty() {
        return items.isEmpty();
    }
     
    public int size() {
        return items.size();
    }
     
    public void clear() {
        items.clear();
    }
    
    
    public static void main(String[] args) {
    	MyHeap<Double> heap = new MyHeap<Double>();
		double a = 1.1;
		double b = 2.2;
		double c = 3.3;
    	heap.add(b);
    	heap.add(c);
    	heap.add(a);
    	System.out.println(heap.peek());
    }
}