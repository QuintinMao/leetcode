package leetcode;


/**
 * 交换字符串使得字符串相同
 * 
 * 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
 * 
 * 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
 * 
 * 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i]
 * 和 s1[j]。
 * 
 * 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-swaps-to-make-strings-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class N1247 {
	public static void main(String[] args) {
		String s1 = "yyyxxxyxyy";
		String s2 = "xxyxyxyxxy";
		System.out.println(minimumSwap(s1, s2));
	}

	public static int minimumSwap(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		//交換次數
		int counter = 0;
		//开始遍历
		for (int i = 0; i < c1.length; i++) {
			//字母相同跳过
			if (c1[i] == c2[i]) {
				continue;
				
			} else {
				//如果字母不同， 择从c1 的当前位置往后查找一个与c2不同的值
				int j = i + 1;
				boolean find = false;
				for (; j < c1.length; j++) {
					if (c1[j] != c2[j] && c1[j] == c1[i]) {
						//找到了进行一次交换
						char temp = c2[i];
						c2[i] = c1[j];
						c1[j] = temp;
						counter++;
						find = true;
						break;
					}
				}
				//没找到的话， 在C2中进行查找
				if (!find) {
					for (int k = i; k < c2.length; k++) {
						if (c1[k] != c2[k] && c2[k] == c1[i]) {

							//如果找到了，择查找这个值应该放在c1的什么位置
							int m = i;
							for (; m < c1.length;) {
								if (c1[m] != c2[k]) {
									find = true;
									break;
								}
								m++;
							}
							//如果没有合适的位置直接返回-1
							if (!find) {
								return -1;
							}
							//有合适的位置，进行一次交换
							char temp = c2[k];
							c2[k] = c1[m];
							c1[m] = temp;
							counter++;
							//将最外层循环计数器跳回到上一次检查
							--i;
							break;
						}
					}
				}
				if (!find) {
					return -1;
				}
			}
		}
		return counter;
	}
}
