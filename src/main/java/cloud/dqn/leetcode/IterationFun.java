package cloud.dqn.leetcode;

import java.util.*;

public class IterationFun {
    public static void iterateCollection() {
        /* ************************
         *  Collection
         ************************ */
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("List: Advanced For\n\t");
        for (Integer i : list) {
            sb.append(i.toString() + ", ");
        }

        HashSet<Integer> set = new HashSet<>(list);
        sb.append("\nSet: Advanced For\n\t");
        for (Integer i : set) {
            sb.append(i.toString() + ", ");
        }

        sb.append("\nSet: ForEach\n\t");
        set.forEach((Integer temp) -> {
            sb.append(temp.toString() + ", ");
        });

        sb.append("\nSet: Iterator\n\t");
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString() + ", ");
        }
        /* ************************
         *  HashMap
         ************************ */
        HashMap<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, (char) i);
        }

        sb.append("\nHashSet: KeySet\n\t");
        for (Integer key : map.keySet()) {
            sb.append("{" + key + ", " + (map.get(key)) + "}");
        }

        sb.append("\nHashSet: EntryKey\n\t");
        for (Map.Entry entry : map.entrySet()) {
            sb.append("{" + entry.getKey() + ", " + entry.getValue() + "}");
        }

    }
}
