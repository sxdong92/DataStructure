package arrayList;

import java.util.Arrays;

public class MyArrayList<E> {
	private int size;
	private transient Object[] elementData;
	
	// ArrayList��������С�Ĺ��캯����    
    public MyArrayList(int initialCapacity) {    
        super();    
        if (initialCapacity < 0)    
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);    
        // �½�һ������    
        this.elementData = new Object[initialCapacity];    
    }    
   
    // ArrayList�޲ι��캯����Ĭ��������10��    
    public MyArrayList() {    
        this(10);    
    }
    
    
    // ��ָ����Ԫ��������б���ָ��λ���ϵ�Ԫ�أ���������ǰλ�ڸ�λ���ϵ�Ԫ�ء�  
    public E set(int index, E element) {  
       RangeCheck(index);  
     
       E oldValue = (E) elementData[index];  
       elementData[index] = element;  
       return oldValue;  
    }    
    
    // ��ָ����Ԫ����ӵ����б��β����  
    public boolean add(E e) {  
       ensureCapacity(size + 1);   
       elementData[size++] = e;  
       return true;  
    }    
    
    // ��ָ����Ԫ�ز�����б��е�ָ��λ�á�  
    // �����ǰλ����Ԫ�أ��������ƶ���ǰλ�ڸ�λ�õ�Ԫ���Լ����к���Ԫ�أ�����������1����  
    public void add(int index, E element) {
    	RangeCheck(index); 
        // ������鳤�Ȳ��㣬���������ݡ�  
        ensureCapacity(size+1);
        // �� elementData�д�Indexλ�ÿ�ʼ������Ϊsize-index��Ԫ�أ�  
        // ���������±�Ϊindex+1λ�ÿ�ʼ���µ�elementData�����С�  
        // ������ǰλ�ڸ�λ�õ�Ԫ���Լ����к���Ԫ������һ��λ�á�  
        System.arraycopy(elementData, index, elementData, index + 1, size - index);  
        elementData[index] = element;  
        size++;  
    }
    
    // ���ش��б���ָ��λ���ϵ�Ԫ�ء�  
    public E get(int index) {  
       RangeCheck(index);   
       return (E) elementData[index];  
    }
    
    // �Ƴ����б���ָ��λ���ϵ�Ԫ�ء�  
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
            int newCapacity = (oldCapacity * 3)/2 + 1;  //����50%+1
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
