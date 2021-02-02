package leetcode.Feb02;

/**
 * 292.
 * 
 * Initially, there is a heap of stones on the table.
 * 
 * You and your friend will alternate taking turns, and you go first.
 * 
 * On each turn, the person whose turn it is will remove 1 to 3 stones from the
 * heap.
 * 
 * The one who removes the last stone is the winner.
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午5:46:08
 */
public class NimGame {
	public boolean canWinNim(int n) {
		int a = n % 4;
		if (a >= 1 && a < 4) {
			return true;
		}
		return false;
	}
}
