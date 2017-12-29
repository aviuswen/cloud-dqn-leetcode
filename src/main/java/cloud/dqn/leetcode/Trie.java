package cloud.dqn.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
    Implement a trie with insert, search, and startsWith methods.

    Note:
    You may assume that all inputs are consist of lowercase letters a-z.

 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
class Trie {
    private class LowerCharMap <T> {
        private static final int SIZE = 26;
        private Object[] data;

        LowerCharMap() {
            this.data = new Object[SIZE];
        }

        public T get(char c) {
            int index = convertToIndex(c);
            if (index >= 0 && index < SIZE) {
                return (T)(data[index]);
            }
            return null;
            // return (T)(data[convertToIndex(c)]);
        }

        boolean put(char c, T t) {
            int index = convertToIndex(c);
            if (index >= 0 && index < SIZE) {
                data[index] = t;
                return true;
            }
            return false;
            // data[convertToIndex(c)] = t;
            // return true;
        }

        boolean containsKey(char c) {
            int index = convertToIndex(c);
            return index >= 0 && index < SIZE && data[index] != null;
            // return data[convertToIndex(c)] != null;
        }

        private int convertToIndex(char c) {
            return c - 'a';
        }
    }
    private class TrieNode {
        private boolean fullWord;
        LowerCharMap<TrieNode> children;

        TrieNode() {
            this.children = new LowerCharMap();
            this.fullWord = false;
        }

        public void append(char[] chars) {
            TrieNode current = this;
            for (int i = 0; i < chars.length; i++) {
                current = current.append(chars[i]);
            }
            current.fullWord = true;
        }

        private TrieNode append(char val) {
            if (children.containsKey(val)) {
                return children.get(val);
            }
            TrieNode newNode = new TrieNode();
            children.put(val, newNode);
            return newNode;
        }

        boolean search(char[] chars) {
            TrieNode current = startsWith(chars);
            return current != null && current.fullWord;
        }

        TrieNode startsWith(char[] prefix) {
            TrieNode current = root;
            int index = 0;
            while (current != null && index < prefix.length) {
                current = current.children.get(prefix[index]);
                index++;
            }
            return current;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root.append(word.toCharArray());
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return root.search(word.toCharArray());
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return root.startsWith(prefix.toCharArray()) != null;
    }
}