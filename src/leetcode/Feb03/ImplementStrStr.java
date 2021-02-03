package leetcode.Feb03;

/**
 * 28.
 * 
 * @author Johnny
 * 
 *         2021年2月3日 下午6:23:42
 */
public class ImplementStrStr {

	public static void main(String[] args) {
		String haystack = "mississippi";
		String needle = "pi";
		System.out.println(new ImplementStrStr().strStr(haystack, needle));
	}

	public int strStr(String haystack, String needle) {
		if (needle == null || needle.length() > haystack.length()) {
			return -1;
		}
		if ("".equals(needle)) {
			return 0;
		}
		Integer head = null;
		Integer nextHead = null;
		for (int i = 0; i < haystack.length(); ++i) {
			if (head == null && haystack.charAt(i) == needle.charAt(0)) {
				head = i;
				if (needle.length() == 1) {
					return head;
				}
				if (haystack.length() - i < needle.length()) {
					return -1;
				}
			} else if (head != null) {
				if (haystack.charAt(i) == needle.charAt(i - head)) {
					if (nextHead == null && haystack.charAt(i) == needle.charAt(0)) {
						nextHead = i;
					}
					if (i - head == needle.length() - 1) {
						return head;
					}
				} else {
					if (nextHead != null) {
						i = nextHead - 1;
					} else {
						--i;
					}
					head = null;
					nextHead = null;
				}
			}
		}

		return head == null ? -1 : head;
	}
}
