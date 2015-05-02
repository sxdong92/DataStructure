package unionFind;

public class QuickFind {
	private int[] id; // access to component id (site indexed)  
    private int count; // number of components  
    
    public QuickFind(int N) {  
        // Initialize component id array.  
        count = N;  
        id = new int[N];  
        for (int i = 0; i < N; i++)  
            id[i] = i;  
    }  
    
    public int count() {
    	return count;
    }  
    
    public boolean connected(int p, int q) {
    	return find(p) == find(q);
    }  
    
    public int find(int p) {
    	return id[p];
    }
    
    public void union(int p, int q) {   
        // 获得p和q的组号  
        int pID = find(p);  
        int qID = find(q);  
        
        // 如果两个组号相等，直接返回  
        if (pID == qID) return;  
        
        // 遍历一次，改变组号使他们属于一个组  
        for (int i = 0; i < id.length; i++) {
        	if (id[i] == pID) id[i] = qID; 
        } 
        count--;  
    }  
    
    public static void main(String[] args) {
    	QuickFind qf = new QuickFind(5);
    	qf.union(0, 1);
    	qf.union(0, 2);
    	qf.union(3, 4);
    	System.out.println("There are " + qf.count + " subsets.");
    	System.out.println("3rd element is in the subset No." + qf.find(2) + ".");
    	System.out.println("3rd element and 5th element are " + (qf.connected(2, 4) ? "connected." : "unconnected."));
    }
}
