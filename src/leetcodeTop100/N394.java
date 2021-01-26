package leetcodeTop100;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串解码
 * 
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class N394 {

	public static void main(String[] args) {
		String s = "abc3[cd]xyz";
		System.out.println(decodeString(s));
	}

	public static String decodeString(String s) {
		StringBuilder builder = new StringBuilder();
		char left = '[';
		char right = ']';

		// 扫描到左括号的次数
		int leftCount = 0;
		// 扫描到右括号的次数
		int rightCount = 0;

		// 记录substring的索引（最外层的一组左右括号）
		int minLeftIndex = -1;
		int maxRightIndex = -1;
		// 记录重复次数
		List<Character> counterTempArray = new ArrayList<>();

		// 等待进入递归的字串
		String subString = null;
		// 当前扫描是否在括号外
		boolean bracketsClosed = true;
		for (int i = 0; i < s.length(); i++) {
			int ch = s.codePointAt(i);
			// 如果是数字， 记录循环次数
			if (Character.isDigit(ch)) {
				// 括号内的数字不记录， 进入递归后处理
				if (bracketsClosed) {
					counterTempArray.add((char) ch);
				}
			} else {
				// 扫描到左括号
				if (ch == left) {
					if (minLeftIndex == -1) {
						minLeftIndex = i;
					}
					leftCount++;
					// 进入括号体内
					bracketsClosed = false;

					// 扫描到右括号
				} else if (ch == right) {
					rightCount++;
					// 如果扫描到的左右括号数量相等， 说明这一组括号已经完全配对关闭。
					if (leftCount == rightCount) {
						maxRightIndex = i;
						// 将状态设置为在括号外
						bracketsClosed = true;
					}
					//只有在括号外的时候，才直接把字符写入builder
				} else if (bracketsClosed) {
					builder.append((char) ch);
				}
			}
			//如果左右括号完全配对了， 处理递归
			if (minLeftIndex != -1 && maxRightIndex != -1) {
				//先截取字串
				subString = s.substring(minLeftIndex + 1, maxRightIndex);
				//计算递归次数
				char[] counterArray = new char[counterTempArray.size()];
				for (int j = 0; j < counterArray.length; j++) {
					counterArray[j] = counterTempArray.get(j);
				}
				int counter = Integer.parseInt(new String(counterArray));
				//递归
				for (int j = 1; j <= counter; j++) {
					builder.append(decodeString(subString));
				}
				
				//重置临时变量
				minLeftIndex = -1;
				maxRightIndex = -1;
				counterTempArray.clear();
				leftCount = 0;
				rightCount = 0;
				subString = null;
			}
		}
		return builder.toString();
	}

}
