package leetcode.Jan29;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155
 * 
 * @author Johnny
 * 
 *         2021年1月29日 下午6:30:30
 */
public class MinStack {
	Deque<Integer> xStack;
	Deque<Integer> minStack;

	public MinStack() {
		xStack = new LinkedList<Integer>();
		minStack = new LinkedList<Integer>();
		minStack.push(Integer.MAX_VALUE);
	}

	public void push(int x) {
		xStack.push(x);
		minStack.push(Math.min(minStack.peek(), x));
	}

	public void pop() {
		xStack.pop();
		minStack.pop();
	}

	public int top() {
		return xStack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}

}
