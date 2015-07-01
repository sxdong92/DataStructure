package leetLockQuestion;

public class TwoSumII {
	public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		int p = 0; 
		int q = numbers.length - 1;
		while(p < q) {
			if(numbers[p] + numbers[q] == target) {
				res[0] = p;
				res[1] = q;
				break;
			}
			else if(numbers[p] + numbers[q] < target) {
				p++;
			}
			else {
				q--;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		TwoSumII ts = new TwoSumII();
		int[] A = {2, 7, 11, 15};
		int[] a = ts.twoSum(A, 9);
		int[] B = {1, 1};
		int[] b = ts.twoSum(B, 2);
		int[] C = {1, 2, 3};
		int[] c = ts.twoSum(C, 5);
		System.out.println(a[0] + " " + a[1]);
		System.out.println(b[0] + " " + b[1]);
		System.out.println(c[0] + " " + c[1]);
	}
}
