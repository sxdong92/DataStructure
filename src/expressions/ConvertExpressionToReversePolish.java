package expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author lmiky
 * 
 */
public class ConvertExpressionToReversePolish {

	/**
	 * ���ȼ��Ƚ�
	 * @param operator1 �Ƚ�ֵ
	 * @param operator2 ���Ƚ�ֵ
	 * @return С�ڵ��ڷ���false,���ڷ���true
	 */
	public boolean comparePrior(String operator1, String operator2) {
		if(operator2.equals("(")) {
			return true;
		}
		if (operator1.equals("*") || operator1.equals("/")) {
			if (operator2.equals("+") || operator2.equals("-")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * תΪ��׺���ʽ:
	 * 1�������"("ֱ��ѹ��stackջ��
 	 * 2�������")"�����δ�stackջ����������ӵ�����newExpressionStrs��ĩβ��֪������"("��
 	 * 3������Ƿ����ţ��Ƚ�ɨ�赽�����������stackջ��������������ɨ�赽����������ȼ�����ջ��������򣬰������ѹ��ջ������Ļ��������ΰ�ջ������������ӵ�����newExpressionStrs��ĩβ��ֱ���������ȼ�����ɨ�赽���������ջ�գ����Ұ�ɨ�赽�������ѹ��ջ�С�����������ɨ�裬֪������Ϊֹ�����ɨ�������ջ�л���Ԫ�أ������ε����ӵ�����newExpressionStrs��ĩβ���͵õ��˺�׺���ʽ��
	 * @param expressionStrs
	 * @return
	 */
	public String[] toSuffixExpression(String[] expressionStrs) {
		//����ɵı��ʽ
		List<String> newExpressionStrs = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		
		for (int i = 0; i < expressionStrs.length; i++) {
			if (expressionStrs[i].equals("(")) { // �����������,����ջ
				stack.push(expressionStrs[i]);
			} 
			else if (expressionStrs[i].equals("+") || expressionStrs[i].equals("-") || expressionStrs[i].equals("*") || expressionStrs[i].equals("/")) {
				while (!stack.empty()) { // ȡ������ջ�������
					String s = stack.pop();
					if(comparePrior(expressionStrs[i], s)) { //���ջֵ���ȼ�С��Ҫ��ջ��ֵ,�����ѹ��ջ
						stack.push(s);
						break;
					} 
					else {  //����ȡ��ֵ
						newExpressionStrs.add(s);
					}
				}
				stack.push(expressionStrs[i]);
			} 
			else if (expressionStrs[i].equals(")")) { //�����")",���ջ,һֱ������"("
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
				newExpressionStrs.add(expressionStrs[i]);
			}
		}
		while (!stack.empty()) {
			String s = stack.pop();
			newExpressionStrs.add(s);
		}
		return newExpressionStrs.toArray(new String[0]);
	}

	public static void main(String[] args) {
		//ǰ̨���������ַ���ʽ,���Բ���Ҳд�������ʽ
		String expressionStr = "5 + ( 14 - 5 + 1 ) - 4 + ( 6 - 5 + 3 ) + 2";
		String[] expressionStrs = expressionStr.split(" "); // �ָ�ɱ��ʽ����
		String[] newExpressionStrs = new ConvertExpressionToReversePolish().toSuffixExpression(expressionStrs);
		for (int i = 0; i < newExpressionStrs.length; i++) {
			System.out.print(newExpressionStrs[i] + " ");
		}
		
		System.out.println();
		
		expressionStr = "5 + ( 14 - 5 * 1 ) - 4 / ( 6 * 5 + 3 ) / 2";
		expressionStrs = expressionStr.split(" ");
		newExpressionStrs = new ConvertExpressionToReversePolish().toSuffixExpression(expressionStrs);
		for (int i = 0; i < newExpressionStrs.length; i++) {
			System.out.print(newExpressionStrs[i] + " ");
		}
		
		System.out.println();
		
		expressionStr = "50 + 4 * 3 / 2 + 799";
		expressionStrs = expressionStr.split(" ");
		newExpressionStrs = new ConvertExpressionToReversePolish().toSuffixExpression(expressionStrs);
		for (int i = 0; i < newExpressionStrs.length; i++) {
			System.out.print(newExpressionStrs[i] + " ");
		}
	}
}
