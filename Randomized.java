package comprehensive;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Randomized {
    // store all words as hashmap(word, arraylist(followers))
    // set seed as entry
    // loop k times
    // pick random follower out of the entry's associated arraylist, set as entry
    public static void execute(String file, String seed, int k) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
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
                map.get(prevWord).add(s);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                map.put(prevWord, list);
                list.add(s);
            }
        }

        if (!map.containsKey(s)) {
            ArrayList<String> list = new ArrayList<String>();
            map.put(s, list);
            list.add(seed);
        }

        fileInput.close();

        String entry = seed;
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            System.out.print(entry + " ");
            ArrayList<String> list = map.get(entry);
            entry = list.get(r.nextInt(list.size()));
        }
    }
}
