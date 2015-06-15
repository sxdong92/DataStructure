package leetLockQuestion;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
	public List<String> findMissingRanges(int[] A, int lower, int upper) {
		List<String> res = new ArrayList<String>();
		if(A == null || A.length == 0 || lower > upper) return res;
		
		for(int i = 0; i < A.length; i++) {
			if(A[i] == lower) {
				lower++;
				continue;
			}
			
			if(A[i] == lower + 1) {
				res.add(lower + "");
			}
			else {
				res.add(lower + "->" + (A[i]-1));
			}
			lower = A[i] + 1;
		}
		
		if(upper == lower) {
			res.add(lower + "");
		}
		else {
			res.add(lower + "->" + upper);
		}
		return res;
	}
	
	public static void main(String[] args) {
		MissingRanges mr = new MissingRanges();
		int[] A = {0, 1, 3, 50, 75};
		int[] B = {1, 3, 50, 75};
		int[] C = {0, 1, 3, 50, 75};
		System.out.println(mr.findMissingRanges(A, 0, 99));
		System.out.println(mr.findMissingRanges(B, 0, 99));
		System.out.println(mr.findMissingRanges(C, 0, 75));
	}
}
