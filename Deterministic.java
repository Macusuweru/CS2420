package comprehensive;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Deterministic {
    // Parse the whole document
    // store it as hashmap(string, hashmap(follower, count))
    // start with seed as entry
    // create a second hashmap(string, follower)
    // loop: take entry, if isn't in second hashmap, find max of count in first hashmap and store the follower in second hashmap and as entry
    // if it is in second hashmap return follower as entry
    //loop until k
    public static void execute(String file, String seed, int k) {
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        seed = seed.toLowerCase();
        
        Scanner fileInput = new Scanner(file);
        fileInput.useDelimiter("\\s*[^a-zA-Z0-9\\_\\']\\s*");

        String prevWord;
        String s = null;
        while (fileInput.hasNext()) {
            prevWord = s;
            s = fileInput.next().toLowerCase();
            if(prevWord == null) continue;
            if (map.containsKey(prevWord)) {
                HashMap<String, Integer> submap = map.get(prevWord);
                if (submap.containsKey(s))
                    submap.put(s, submap.get(s) + 1);
                else
                    submap.put(s, 1);
            } else {
                HashMap<String, Integer> submap = new HashMap<>();
                map.put(prevWord, submap);
                submap.put(s, 1);
            }
        }

        if (!map.containsKey(s)) {
            HashMap<String, Integer> submap = new HashMap<>();
            map.put(s, submap);
            submap.put(seed, 1);
        }

        fileInput.close();
    
        HashMap<String, String> mostLikely = new HashMap<>();
        String entry = seed;
        for (int i = 0; i < k; i++) {
            System.out.print(entry + " ");
            if (mostLikely.containsKey(entry)) {
                entry = mostLikely.get(entry);
            } else {
                int maxValue = 0;
                String maxKey = "";
                for (Map.Entry<String, Integer> m: map.get(entry).entrySet()) {
                    if (m.getValue() > maxValue) {
                        maxValue = m.getValue();
                        maxKey = m.getKey();
                    }
                }
                mostLikely.put(entry, maxKey);
                entry = maxKey;
            }
        }
    }
}
