package cloud.dqn.leetcode;

import java.util.ArrayList;

public class LongestParentheses {
    public static class Solution {
        private class StackNode {
            char val;
            StackNode next;
            StackNode(char val) {
                this.val = val;
                this.next = null;
            }
            StackNode(char val, StackNode next) {
                this.val = val;
                this.next = next;
            }
        }
        private class Stack {
            private StackNode top;
            Stack() {
                this.top = null;
            }
            void push(char val) {
                StackNode node = new StackNode(val, top);
                top = node;
            }
            StackNode pop() {
                StackNode temp = top;
                if (top != null) {
                    top = top.next;
                }
                return temp;
            }

            boolean isEmpty() {
                return top == null;
            }
        }
//        // warning no bounds checking
//        boolean isValid(char[] s, int start, int end) {
//            if (start == end) {
//                return true;
//            } else if (start > end) {
//                return false;
//            } else if (((end - start) & 1) == 0) {
//                return false;
//            }
//
//
//            char current;
//            Node node;
//            Stack stack = new Stack();
//            for(int i = start; i <= end; i++) {
//                current = s[i];
//                if (current == '(') {
//                    stack.push('(');
//                } else {
//                    node = stack.pop();
//                    if (node == null || node.val != '(') {
//                        return false;
//                    }
//                }
//            }
//
//            return stack.isEmpty();
//        }
//
//        private int naivelongestValidParentheses(String s) {
//            char[] sChar = s.toCharArray();
//            if (sChar.length <= 0) {
//                return 0;
//            }
//
//            int max = 0;
//            for (int i = sChar.length - 1; i >= 0; i--) {
//                for (int j = 0; j < i; j++) {
//                    if (i - j + 1 < max) {
//                        break;
//                    }
//                    if (isValid(sChar, j, i)) {
//                        max = Math.max(max, (i - j + 1));
//                    }
//                }
//            }
//            return max;
//        }
//
//        private ArrayList<Character> fromString(String s) {
//            ArrayList<Character> arr = new ArrayList<>(s.length());
//            for (int i = 0; i < s.length(); i++) {
//                arr.add(s.charAt(i));
//            }
//            arr.get(1);
//
//            return arr;
//        }
//


        private static final Integer LEFT = -1;
        private static final Integer RIGHT = -2;

        private class Node {
            Integer val;
            Node left;
            Node right;
            Node(Integer val) {
                this.val = val;
                this.left = null;
                this.right = null;
            }
            public boolean isRight() {
                return val == RIGHT;
            }
            public boolean isLeft() {
                return val == LEFT;
            }
            public boolean isCount() {
                return val > 0;
            }
            public boolean rightIsCount() {
                return right != null && right.isCount();
            }
            public Integer incrementVal(Integer val) {
                this.val = this.val + val;
                return this.val;
            }
        }

        private class NodeList {
            Node leftMost;
            Node rightMost;
            NodeList() {
                leftMost = null;
                rightMost = null;
            }
            public NodeList(String s) {
                leftMost = null;
                rightMost = null;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        addNew(LEFT);
                    } else {
                        addNew(RIGHT);
                    }
                }

            }
            public void addNew(Integer val) {
                Node node = new Node(val);
                if (leftMost == null) {
                    leftMost = node;
                    rightMost = node;
                } else {
                    rightMost.right = node;
                    node.left = rightMost;
                    rightMost = node;
                }
            }
            public void delete(Node node) {
                Node left = node.left;
                Node right = node.right;
                node.left = null;
                node.right = null;

                if (left != null) {
                    left.right = right;
                }
                if (right != null) {
                    right.left = left;
                }
            }
            public int findMax(int defaultValue) {
                int max = defaultValue;
                Node i = leftMost;
                while (i != null) {
                    max = Math.max(max, i.val);
                    i = i.right;
                }
                return max;
            }
            public int compactConsecutiveCounts() {
                int numTimesCompacted = 0;
                Integer temp = 0;
                Node i = leftMost;
                while (i != null) {
                    if (i.isCount() && i.rightIsCount()) {
                        i.incrementVal(i.right.val);
                        delete(i.right);
                    } else {
                        i = i.right;
                    }
                }
                return numTimesCompacted;
            }
            public int compactSingleParentheses() {
                int numTimesCompacted = 0;
                Node i = leftMost;
                while (i != null) {
                    if (i.isLeft() && i.right != null && i.right.isRight()) {
                        delete(i.right);
                        i.val = 2;
                        numTimesCompacted++;
                    }
                    i = i.right;
                }
                return numTimesCompacted;
            }
            public int compactSingleParenthesesWithInnerSingleCount() {
                int numTimesCompacted = 0;
                Node i = leftMost;
                Node middle;
                Node right;
                while (i != null) {
                    if (i.isLeft()) {
                        middle = i.right;
                        if (middle != null && middle.isCount()) {
                            right = middle.right;
                            if (right != null && right.isRight()) {

                                i.val = 2 + middle.val;
                                delete(i.right);
                                delete(i.right);
                                numTimesCompacted++;
                            }
                        }
                    }
                    i = i.right;
                }
                return numTimesCompacted;
            }

            public int longestValidParentheses() {
                if (this.compactSingleParentheses() == 0) {
                    return 0;
                }
                int compactCounts;
                int compactParenthesesWithInnerCount;

                do {
                    compactCounts = this.compactConsecutiveCounts();
                    compactParenthesesWithInnerCount = this.compactSingleParenthesesWithInnerSingleCount();
                } while (compactCounts > 0 || compactParenthesesWithInnerCount > 0);

                this.compactConsecutiveCounts();
                return this.findMax(0);
            }
        }
        // converted
        private ArrayList<Integer> convertToIntArray(String s) {
            ArrayList<Integer> arr = new ArrayList<Integer>(s.length());
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    arr.add(LEFT);
                } else {
                    arr.add(RIGHT);
                }
            }
            return arr;
        }
        // converted
        private int compactConsecutiveCounts(ArrayList<Integer> arr) {
            int numTimesCompacted = 0;
            int i = 0;
            Integer temp;
            while (i < arr.size()) {
                if (arr.get(i) > 0 && i + 1 < arr.size() && arr.get(i+1) > 0) {
                    temp = arr.get(i);
                    arr.set(i, temp + arr.remove(i+1));
                    numTimesCompacted++;
                } else {
                    i++;
                }
            }
            return numTimesCompacted;
        }
        // converted
        private int compactSingleParentheses(ArrayList<Integer> arr) {
            int numTimesCompacted = 0;
            int i = 0;
            while (i < arr.size()) {
                if (arr.get(i).equals(LEFT) && (i+1) < arr.size() && arr.get(i+1) == RIGHT) {
                    arr.remove(i+1);
                    arr.set(i, 2);
                    numTimesCompacted++;
                }
                i++;
            }
            return numTimesCompacted;
        }
        // converted
        private int compactSingleParenthesesWithInnerSingleCount(ArrayList<Integer> arr) {
            int numTimesCompacted = 0;
            int i = 0;
            while (i < arr.size()) {
                if (arr.get(i) == LEFT
                        && i+2 < arr.size()
                        && arr.get(i+1) > 0
                        && arr.get(i+2) == RIGHT) {

                    arr.set(i, 2 + arr.get(i+1));
                    arr.remove(i+1);
                    arr.remove(i+1);
                    numTimesCompacted++;
                }
                i++;
            }
            return numTimesCompacted;
        }
        // converted
        public int longestValidParenthesesHelper(String s) {
            ArrayList<Integer> arr = convertToIntArray(s);
            int numTimesCompactedSingles = compactSingleParentheses(arr);
            if (numTimesCompactedSingles == 0) {
                return 0;
            }
            int compactCounts = 1;
            int compactParenthesesWithInnerCount = 1;
            while (compactCounts > 0 || compactParenthesesWithInnerCount > 0) {
                compactCounts = compactConsecutiveCounts(arr);
                compactParenthesesWithInnerCount = compactSingleParenthesesWithInnerSingleCount(arr);
            }
            compactConsecutiveCounts(arr);
            int max = 0;
            for (int i = 0; i < arr.size(); i++) {
                max = Math.max(max, arr.get(i));
            }
            return max;
        }

       /**
         * Algo 3
         *         *
         * "((()))())"
         * add = 0
         * p = (
         * stack: 6,2
         *
         *  s.forEachChar {
         *      if it == '(',
         *          push it
         *      else {
         *          var add = 0;
         *          while(stack.isNotEmpty())
         *              val p = pop()
         *              if (p == '(')
         *                  if peek == number
         *                    push(2+add+pop())
         *                  else
         *                    push(2 + add)
         *                  updateMax;
         *                  break
         *              else
         *                  add += p
         *
         *
         *      }
         *  }
         */
        private int longestValidParenthesesStack(String s) {
            java.util.Stack stack = new java.util.Stack<Integer>();
            int max = 0;
            char current;
            for (int i = 0; i < s.length(); i++) {
                current = s.charAt(i);
                if (current == '(') {
                    stack.push(-1);
                } else {
                    int add = 0;
                    Integer peek;
                    while (!stack.isEmpty()) {
                        Integer p = (Integer)(stack.pop());
                        if (p.equals(-1)) {
                            if (!stack.isEmpty()) {
                                peek = (Integer)(stack.peek());
                                if (peek != -1) {
                                    add += peek;
                                    stack.pop();
                                }
                            }
                            add += 2;
                            stack.push(add);
                            max = Math.max(max, add);
                            break;
                        } else {
                            add += p;
                        }
                    }
                }
            }
            return max;
        }

        public int longestValidParentheses(String s) {
            // return naivelongestValidParentheses(s); // timeout
            // return longestValidParenthesesHelper(s);

            // NodeList list = new NodeList(s);
            // return list.longestValidParentheses();

            return longestValidParenthesesStack(s);
        }
    }
}
