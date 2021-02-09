package leetcode.Feb08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 127.
 * advanced: 在已知起点和终点的前提下， 可以进行双向BFS搜索，降低时间复杂度
 * @author Johnny
 * 
 *         2021年2月8日 上午11:29:58
 */
public class WordLadder {

	public static void main(String[] args) {
		String beginWord = "hit", endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(new WordLadder().ladderLengthBFS2(beginWord, endWord, wordList));
	}

	public int ladderLengthBFS2(String beginWord, String endWord, List<String> wordList) {
		// 先将词典中的单词加入HashSet，可以快速判断目标单词是否在词典中
		HashSet<String> wordSet = new HashSet<>(wordList);
		// 如果词典中不包含endWord 直接返回0
		if (!wordSet.contains(endWord)) {
			return 0;
		}
		// 移除beginWord
		wordSet.remove(beginWord);

		// 准备开始BFS
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		// 记录访问状态
		Set<String> visited = new HashSet<>();
		visited.add(beginWord);

		int step = 1;
		int wordLen = endWord.length();
		while (!queue.isEmpty()) {
			// 按层遍历
			int currentLevelSize = queue.size();
			for (int i = 0; i < currentLevelSize; ++i) {

				String currentWord = queue.poll();
				char[] ss = currentWord.toCharArray();
				// 将单词的没一个位置都枚举一遍， 看看能否找到符合转换条件的目标单词。
				for (int j = 0; j < wordLen; ++j) {
					// 保留第i位的原始char，后面要进行恢复
					char oriChar = ss[j];
					for (char k = 'a'; k <= 'z'; ++k) {
						if (k == oriChar) {
							continue;
						}
						ss[j] = k;
						String nextWord = new String(ss);
						if (!wordSet.contains(nextWord)) {
							continue;
						}
						if (nextWord.equals(endWord)) {
							return step + 1;
						}
						if (!visited.contains(nextWord)) {
							queue.offer(nextWord);
							visited.add(nextWord);
						}
					}
					ss[j] = oriChar;
				}
			}
			// 本层没找到，进入下一层
			++step;
		}
		return 0;

	}

	// 给词典中的每个单词一个id号。
	Map<String, Integer> word2Id = new HashMap<>();
	List<List<Integer>> edges = new ArrayList<>();
	int vertexNum;

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		for (String word : wordList) {
			addEdge(word);
		}
		addEdge(beginWord);
		if (!word2Id.containsKey(endWord)) {
			return 0;
		}
		int[] dis = new int[vertexNum];
		Arrays.fill(dis, Integer.MAX_VALUE);
		int startId = word2Id.get(beginWord), endId = word2Id.get(endWord);
		dis[startId] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(startId);
		while (!queue.isEmpty()) {
			int x = queue.poll();
			if (x == endId) {
				return dis[x] / 2 + 1;
			}
			for (int it : edges.get(x)) {
				if (dis[it] == Integer.MAX_VALUE) {
					dis[it] = dis[x] + 1;
					queue.offer(it);
				}
			}
		}
		return 0;
	}

	private void addEdge(String word) {
		addWord(word);
		int id1 = word2Id.get(word);
		char[] array = word.toCharArray();
		int length = array.length;
		for (int i = 0; i < length; ++i) {
			char tmp = array[i];
			array[i] = '*';
			String newWord = new String(array);
			addWord(newWord);
			int id2 = word2Id.get(newWord);
			edges.get(id1).add(id2);
			edges.get(id2).add(id1);
			array[i] = tmp;
		}
	}

	private void addWord(String w) {
		if (!word2Id.containsKey(w)) {
			word2Id.put(w, vertexNum++);
			edges.add(new ArrayList<>());
		}
	}
}
