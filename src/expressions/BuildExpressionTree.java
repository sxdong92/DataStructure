package expressions;

import java.util.ArrayList;
import java.util.Stack;

//Definition of ExpressionTreeNode:
class ExpressionTreeNode {
    public String symbol;
    public ExpressionTreeNode left, right;
    public ExpressionTreeNode(String symbol) {
        this.symbol = symbol;
        this.left = this.right = null;
    }
}

public class BuildExpressionTree {
    /**
     * @param expression: A string array
     * @return: The root of expression tree
     */
    public ExpressionTreeNode build(String[] expression) {
        // write your code here
        ArrayList<String> rpn = convertToRPN(expression);
        if(rpn == null || rpn.size() == 0) return null;
        Stack<ExpressionTreeNode> stack = new Stack<ExpressionTreeNode>();
        
        for(int i=0; i<rpn.size(); i++) {
            if(rpn.get(i).equals("+") || rpn.get(i).equals("-") || rpn.get(i).equals("*") || rpn.get(i).equals("/")) {
                ExpressionTreeNode right = stack.pop();
                ExpressionTreeNode left = stack.pop();
                ExpressionTreeNode tmp = new ExpressionTreeNode(rpn.get(i));
                tmp.left = left;
                tmp.right = right;
                stack.push(tmp);
            }
            else {
                ExpressionTreeNode tmp = new ExpressionTreeNode(rpn.get(i));
                stack.push(tmp);
            }
        }
        return stack.pop();
    }
    
    public ArrayList<String> convertToRPN(String[] expression) {
        // write your code here
        ArrayList<String> newExpressionStrs = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		
		for (int i = 0; i < expression.length; i++) {
			if (expression[i].equals("(")) { // 如果是左括号,则入栈
				stack.push(expression[i]);
			} 
			else if (expression[i].equals("+") || expression[i].equals("-") || expression[i].equals("*") || expression[i].equals("/")) {
				while (!stack.empty()) { // 取出先入栈的运算符
					String s = stack.pop();
					if(comparePrior(expression[i], s)) { //如果栈值优先级小于要入栈的值,则继续压入栈
						stack.push(s);
						break;
					} 
					else {  //否则取出值
						newExpressionStrs.add(s);
					}
				}
				stack.push(expression[i]);
			} 
			else if (expression[i].equals(")")) { //如果是")",则出栈,一直到遇到"("
				while (!stack.empty()) {
					String s = stack.pop();
					if (!s.equals("(")) {
						newExpressionStrs.add(s);
					} 
					else {
						break;
					}
				}
			} 
			else {
				newExpressionStrs.add(expression[i]);
			}
		}
		while (!stack.empty()) {
			String s = stack.pop();
			newExpressionStrs.add(s);
		}
		return newExpressionStrs;
    }
    
    public boolean comparePrior(String operator1, String operator2) {
		if(operator2.equals("(")) {
			return true;
		}
		if (operator1.equals("*") || operator1.equals("/")) {
			if ("+".equals(operator2) || "-".equals(operator2)) {
				return true;
			}
		}
		return false;
	}
    
    public static void main(String[] args) {
    	BuildExpressionTree buildTree = new BuildExpressionTree();
    	String expressionStr = "5 + ( 14 - 5 + 1 ) - 4 + ( 6 - 5 + 3 ) + 2";
    	String[] expressionStrs = expressionStr.split(" ");
    	
    	ExpressionTreeNode root = buildTree.build(expressionStrs);
    	//TODO: visualize the tree
    }
}
