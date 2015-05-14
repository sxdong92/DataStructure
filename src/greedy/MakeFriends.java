package greedy;

public class MakeFriends {
	public boolean[][] solve(int n, int f) {
		if(n <= 1 || f < 0 || n <= f) {
			throw new IllegalArgumentException("wrong arguments!");
		}
		if(n*f % 2 != 0) {
			return null;
		}
		
		boolean[][] matrix = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				matrix[i][j] = true;
			}
		}
		for(int i=0; i<matrix.length; i++) {
			matrix[i][i] = false;
		}
		
		int[] degrees = new int[n];
		for(int i=0; i<n; i++) {
			degrees[i] = n-1;
		}
		
		
		for(int i=0; i<n; i++) {
			int degree = degrees[i];
			if(degree == f) continue;
			
			int left = i;
			int right = i;
			while(degree > f) {
				left = left-1 == -1 ? n-1 : left-1;
				if(degrees[left] > f && matrix[i][left]) {
					matrix[i][left] = false;
					matrix[left][i] = false;
					degree--;
					degrees[i]--;
					degrees[left]--;
					if(degree == f) break;
				}
				right = right+1 == n ? 0 : right+1;
				if(degrees[right] > f && matrix[i][right]) {
					matrix[i][right] = false;
					matrix[right][i] = false;
					degree--;
					degrees[i]--;
					degrees[right]--;
					if(degree == f) break;
				}
			}
		}
		
		return matrix;
	}

	
	public static void main(String[] args) {
		MakeFriends mf = new MakeFriends();
		boolean[][] matrix = mf.solve(13, 12);
		if(matrix == null) {
			System.out.println("n*f should be even !");
			return;
		}
		
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
