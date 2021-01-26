package leetcodeTop100;

import java.util.LinkedHashMap;

public class N146 {
	public static void main(String[] args) {
		LRUCache c = new LRUCache(2);
		System.out.println(c.get(2));
		c.put(2, 6);
		System.out.println(c.get(1));
		c.put(1, 5);
		c.put(1, 2);
		System.out.println(c.get(1));
		System.out.println(c.get(2));

	}
}

class LRUCache extends LinkedHashMap<Integer, Integer> {

	private static final long serialVersionUID = 1L;
	private int capacity;

	public LRUCache(int capacity) {
		super(capacity, 0.75F, true);
		this.capacity = capacity;
	}

	public int get(int key) {
		return super.getOrDefault(key, -1);
	}

	public void put(int key, int value) {
		super.put(key, value);
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
		return this.size() > capacity;
	}

}
