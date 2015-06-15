package leetLockQuestion;

/**
 * Given a string, find the length of the longest substring T that 
 * contains at most 2 distinct characters. 
 * For example, Given s = ¡°eceba¡±, T is "ece" which its length is 3.
 * 
 * @author sxd
 *
 */
public class LongestSubstringwithAtMostTwoDistinctCharacters {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int res = 0;
		if(s == null || s.length() == 0) return res;
		if(s.length() <= 2) return s.length();
		
		int[] dp = new int[2];
		dp[0] = 0;
		int nearst = 0;
		for(int i = 1; i < s.length(); i++) {
			nearst++;
			if(s.charAt(i) != s.charAt(dp[0])) {
				dp[1] = i;
				break;
			}
		}
		res = nearst + 1;
		
		for(int i = nearst+1; i < s.length(); i++) {
			if(s.charAt(i) != s.charAt(dp[0]) && s.charAt(i) != s.charAt(dp[1])) {
				dp[0] = nearst;
				dp[1] = i;
				nearst = i;
			}
			else {
				if(s.charAt(i) != s.charAt(i-1)) nearst = i;
			}
			res = Math.max(res, i - dp[0] + 1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		LongestSubstringwithAtMostTwoDistinctCharacters obj = new LongestSubstringwithAtMostTwoDistinctCharacters();
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct(""));//0
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("a"));//1
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ab"));//2
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("abc"));//2
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ecebd"));//3
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ececcbb"));//5
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ececcbbbb"));//6
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("eecbbbb"));//5
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ececececec"));//10
		System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ec"));//2
	}
}
