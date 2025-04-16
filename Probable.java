package comprehensive;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Probable {
    public static void execute(String file, String seed, int k) {
        // parse all words, store everything following seed as hashmap(follower, count)
        HashMap<String, Integer> map = new HashMap<>();
        seed = seed.toLowerCase();
        try {
            Scanner fileInput = new Scanner(file);
            
            fileInput.useDelimiter("\\s*[^a-zA-Z0-9\\_\\']\\s*");
            String prevWord;
            String s = null;
            while (fileInput.hasNext()) {
                prevWord = s;
                s = fileInput.next().toLowerCase();
                if(prevWord == null) continue;
                if (prevWord.equals(seed)) {
                    if (map.containsKey(s))
                        map.put(s, map.get(s) + 1);
                    else
                        map.put(s, 1);
                }
            }
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.err.println("File " + file + " cannot be found.");
        }
        // find the top k items according to count
        final int THRESHOLD = 10000;
        if (map.size() > THRESHOLD && k < map.size()/10) {
            PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<Map.Entry<String, Integer>>(new MapEntryComparator());
            for (Map.Entry<String, Integer> m: map.entrySet())
                queue.add(m);
            ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                if (queue.isEmpty()) return;
                System.out.println(queue.poll().getKey() + " ");
            }
        } else {
            ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
            list.sort(new MapEntryComparator());
            ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                System.out.print(list.get(i).getKey() + " ");
            }
        }
    }
    class MapEntryComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            int value = o2.getValue() - o1.getValue();
            if (value == 0)
                return o1.getKey().compareTo(o2.getKey());
            else
                return value;
        }
    }
}

