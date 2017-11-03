package cloud.dqn.leetcode;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 Given a string, find the length of the longest substring without repeating
 characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3.
    Note that the answer must be a substring, "pwke" is a
    subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingChars {
    class Solution {
        /**
         subsequence is bad
         so order does matter
         Solution 1: aweful (sp)
         generate every sub string;
         Order descending
         for each substring in order descending:
         if no duplicate chars
         return length(substring)
         return 0; // solution for an empty string

         Solution 2:
         p, q walker keeping only the largest substring that is not repeating
         and a hashset of the characters that have been used

         largestLength = 4
         found = {}
         q
         p
         asdfajkl

         */
        public int lengthOfLongestSubstring(String s) {
            int largest = 0;
            int currentLength = 0;

            int p = 0;
            int q = 0;

            java.util.HashSet<Character> found = new java.util.HashSet<>();

        /* test set

        s = aasdjd
        sLength = 6
        largest = 4
        currentLength = 0
        p = 2;
        q = 2;
        found = {a,s,d,j};

        qChar = d

        */
            int sLength = s.length(); // SYNTAX; move to while loop if not a method
            while (q < sLength) {
                char qChar = s.charAt(q);
                if (found.contains(qChar)) {
                    if (largest < currentLength) {
                        largest = currentLength;
                    }

                    // special case break early
                    if (q == sLength -1) {
                        break;
                    }

                    currentLength = 0;
                    p += 1;
                    q = p;
                    found.clear();
                } else {
                    found.add(qChar);
                    currentLength++;
                    q += 1;
                }
            }
            if (largest < currentLength) {
                largest = currentLength;
            }

            return largest;
        }
    }
}
