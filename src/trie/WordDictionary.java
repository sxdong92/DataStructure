package trie;

import java.util.LinkedList;

public class WordDictionary {
    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode(' ');
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        if(word == null || word.length() == 0) return;
        if(search(word)) return;
        
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++) {
            TrieNode child = cur.subNode(word.charAt(i));
            if(child != null) {
                cur = child;
            }
            else {
                cur.childList.add(new TrieNode(word.charAt(i)));
                cur = cur.subNode(word.charAt(i));
            }
        }
        cur.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if(word == null || word.length() == 0) return false;
        
        TrieNode cur = root;
        if(!cur.hasChildren()) return false;
        if(word.charAt(0) != '.') {
            if(cur.subNode(word.charAt(0)) == null) return false;
            else return bfs(word, 1, cur.subNode(word.charAt(0)));
        }
        else {
            boolean res = false;
            for(int i=0; i<cur.childList.size(); i++) {
                res = res || bfs(word, 1, cur.childList.get(i));
            }
            return res;
        }
    }
    
    public boolean bfs(String word, int index, TrieNode cur) {
        if(index == word.length()) {
        	if(cur.isEnd) return true;
        	else return false;
        }
        
        if(!cur.hasChildren()) return false;
        if(word.charAt(index) != '.') {
            if(cur.subNode(word.charAt(index)) == null) return false;
            else return bfs(word, index+1, cur.subNode(word.charAt(index)));
        }
        else {
            boolean res = false;
            for(int i=0; i<cur.childList.size(); i++) {
                res = res || bfs(word, index+1, cur.childList.get(i));
            }
            return res;
        }
    }
    
    public static void main(String[] args) {
    	WordDictionary wd = new WordDictionary();
    	
    	wd.addWord("a");
    	wd.addWord("ab");
    	System.out.println(wd.search("a"));
    	System.out.println(wd.search("a."));
    	System.out.println(wd.search("ab"));
    	System.out.println(wd.search(".a"));
    	System.out.println(wd.search(".b"));
    	System.out.println(wd.search("ab."));
    	System.out.println(wd.search("."));
    	System.out.println(wd.search(".."));
    }
}


class TrieNode {
    // Initialize your data structure here.
    char content;
    boolean isEnd;
    public LinkedList<TrieNode> childList;
    
    public TrieNode(char c) {
        childList = new LinkedList<TrieNode>();  
        isEnd = false;  
        content = c;  
    }
        
    public boolean hasChildren() {
        if(childList == null) return false;
        else return true;
    }
    
    public TrieNode subNode(char c) {  
        if(childList != null) {  
            for(TrieNode eachChild : childList) {  
                if(eachChild.content == c) {  
                    return eachChild;  
                }  
            }  
        }  
        return null;  
    } 
}
// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");