package heap;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;  

public class SlidingWindowMedian {
	public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
        if(nums == null || k <= 0) return res;
        if(k == 1) {
        	for(int i : nums) res.add(i);
        	return res;
        }
        if(k > nums.length) k = nums.length;
        
        PriorityQueue<Integer> max = new PriorityQueue<Integer>(k);
        PriorityQueue<Integer> min = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
        	public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        
        for(int i=0; i<k; i++) {
            min.add(nums[i]);
        }
        for(int i=k/2; i<k; i++) {
        	max.add(min.poll());
        }
        res.add(k % 2 == 0 ? min.peek() : max.peek());
        
        int cur;
        int abandon;
        boolean minAddOne;
        for(int i=k; i<nums.length; i++) {
        	cur = nums[i];
        	abandon = nums[i-k];
        	
        	minAddOne = false;
        	if(cur <= min.peek()) {
        		min.add(cur);
        		minAddOne = true;
        	}
        	else {
        		max.add(cur);
        	}
        	
        	if(abandon <= min.peek()) {
        		min.remove(abandon);
        		if(!minAddOne) min.add(max.poll());
        	}
        	else {
        		max.remove(abandon);
        		if(minAddOne) max.add(min.poll());
        	}
        	
        	res.add(k % 2 == 0 ? min.peek() : max.peek());
        }
        
        return res;
    }
	
	public static void main(String[] args) {
//		PriorityQueue<Integer> tmp = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
//        	public int compare(Integer a, Integer b) {
//                return b - a;
//            }
//        });
//		tmp.add(2);
//		tmp.add(1);
//		tmp.add(3);
//		System.out.print(tmp.peek());
		SlidingWindowMedian swm = new SlidingWindowMedian();
		int[] nums = {1,2,7,8,5};
		System.out.println(swm.medianSlidingWindow(nums, 3));
	}
}


