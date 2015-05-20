package arrayList;

import java.util.Arrays;

public class MyArrayList<E> {
	private int size;
	private transient Object[] elementData;
	
	// ArrayList带容量大小的构造函数。    
    public MyArrayList(int initialCapacity) {    
        super();    
        if (initialCapacity < 0)    
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);    
        // 新建一个数组    
        this.elementData = new Object[initialCapacity];    
    }    
   
    // ArrayList无参构造函数。默认容量是10。    
    public MyArrayList() {    
        this(10);    
    }
    
    
    // 用指定的元素替代此列表中指定位置上的元素，并返回以前位于该位置上的元素。  
    public E set(int index, E element) {  
       RangeCheck(index);  
     
       E oldValue = (E) elementData[index];  
       elementData[index] = element;  
       return oldValue;  
    }    
    
    // 将指定的元素添加到此列表的尾部。  
    public boolean add(E e) {  
       ensureCapacity(size + 1);   
       elementData[size++] = e;  
       return true;  
    }    
    
    // 将指定的元素插入此列表中的指定位置。  
    // 如果当前位置有元素，则向右移动当前位于该位置的元素以及所有后续元素（将其索引加1）。  
    public void add(int index, E element) {
    	RangeCheck(index); 
        // 如果数组长度不足，将进行扩容。  
        ensureCapacity(size+1);
        // 将 elementData中从Index位置开始、长度为size-index的元素，  
        // 拷贝到从下标为index+1位置开始的新的elementData数组中。  
        // 即将当前位于该位置的元素以及所有后续元素右移一个位置。  
        System.arraycopy(elementData, index, elementData, index + 1, size - index);  
        elementData[index] = element;  
        size++;  
    }
    
    // 返回此列表中指定位置上的元素。  
    public E get(int index) {  
       RangeCheck(index);   
       return (E) elementData[index];  
    }
    
    // 移除此列表中指定位置上的元素。  
    public E remove(int index) {  
       RangeCheck(index);  
       E oldValue = (E) elementData[index];  
     
       int numMoved = size - index - 1;  
       if(numMoved > 0)  
           System.arraycopy(elementData, index+1, elementData, index, numMoved);  
       elementData[--size] = null; // Let gc do its work  
     
       return oldValue;  
    }
    
    
    public boolean RangeCheck(int index) {
    	if(index < 0 || index > size) {
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	}
    	return true;
    }
    
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;  
        if(minCapacity > oldCapacity) {  
            int newCapacity = (oldCapacity * 3)/2 + 1;  //增加50%+1
            if(newCapacity < minCapacity) {
            	newCapacity = minCapacity;  
            }
            // minCapacity is usually close to size, so this is a win:  
            elementData = Arrays.copyOf(elementData, newCapacity);  
        }  
    }
    
    
    public static void main(String[] args) {
    	MyArrayList<Integer> list = new MyArrayList<Integer>();
    	// Initial the list with more than 10 elements
    	for(int i=0; i<15; i++) {
    		list.add(i);
    	}
    	for(int i=0; i<list.size; i++) {
    		System.out.print(list.get(i) + " ");
    	}
    	System.out.print("//The size is : " + list.size);
    	System.out.println();
    	
    	// Remove an element in a specific position
    	list.remove(5);
    	for(int i=0; i<list.size; i++) {
    		System.out.print(list.get(i) + " ");
    	}
    	System.out.print("//The size is : " + list.size);
    	System.out.println();
    	
    	// Insert an element in a specific position
    	list.add(7, 1000);
    	for(int i=0; i<list.size; i++) {
    		System.out.print(list.get(i) + " ");
    	}
    	System.out.print("//The size is : " + list.size);
    	System.out.println();
    }
}
