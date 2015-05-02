package unionFind;

public class QuickUnion {
	private int[] id; // access to component id (site indexed)  
    private int count; // number of components  
    private int[] sz;
    
    public QuickUnion(int N) {  
        // Initialize component id array.  
        count = N;  
        id = new int[N];  
        for (int i = 0; i < N; i++)  
            id[i] = i;  
        
        sz = new int[N];
        for (int i = 0; i < N; i++)  
            sz[i] = 1;    // 初始情况下，每个组的大小都是1
    }  
    
    public int count() {
    	return count;
    }  
    
    public boolean connected(int p, int q) {
    	return find(p) == find(q);
    }  
    
    public int find(int p) {
    	while (p != id[p])  
        {  
            // 将p节点的父节点设置为它的爷爷节点  
            id[p] = id[id[p]];  
            p = id[p];  
        }  
        return p;
    }
    
    public void union(int p, int q) {   
    	int i = find(p);  
        int j = find(q);  
        if (i == j) return;  
        // 将小树作为大树的子树  
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }  
        else { id[j] = i; sz[i] += sz[j]; }  
        count--;  
    }  
    
    public static void main(String[] args) {
    	QuickUnion qu = new QuickUnion(5);
    	qu.union(0, 1);
    	qu.union(0, 2);
    	qu.union(3, 4);
    	System.out.println("There are " + qu.count + " subsets.");
    	System.out.println("3rd element is in the subset No." + qu.find(2) + ".");
    	System.out.println("3rd element and 5th element are " + (qu.connected(2, 4) ? "connected." : "unconnected."));
    	System.out.println("=======================\n");
    	
    	QuickUnion quTest = new QuickUnion(8);
    	quTest.union(0, 1);
    	quTest.union(0, 2);
    	quTest.union(0, 3);
    	quTest.union(1, 4);
    	quTest.union(1, 5);
    	quTest.union(6, 7);
    	for(int i=0; i<8; i++) {
    		System.out.println(i + " -> " + quTest.find(i));
    	}
    }
}
