package deque;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class MaxInSlideWindow {
	/**
	 * 
	 * @param nums
	 * @param k the size of the sliding window.
	 * @return
	 */
	public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(nums == null || k <= 0) return res;
		
		Deque<Integer> dq = new LinkedList<Integer>();
		for(int i=0; i<nums.length; i++) {
			int cur = nums[i];
			while(!dq.isEmpty() && cur > dq.getLast()) {
				dq.removeLast();
			}
			dq.add(cur);
			if(i < k - 1) continue;
			
			res.add(dq.getFirst());
			if(nums[i - k + 1] == res.get(i - k + 1)) dq.removeFirst();
		}
		return res;
	}
	
	public static void main(String[] args) {
		MaxInSlideWindow msw = new MaxInSlideWindow();
		int[] nums = {1,2,7,7,4,2,5};
		System.out.println(msw.maxSlidingWindow(nums, 3));
	}

}
