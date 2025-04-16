package comprehensive;

public class TextGenerator {
    public static void main(String[] args) {
        if (args[3].equals("probable"))
            Probable.execute(args[0], args[1], Integer.valueOf(args[2]));
        else if (args[3].equals("deterministic"))
            Deterministic.execute(args[0], args[1], Integer.valueOf(args[2]));
        else if (args[3].equals("random"))
            Randomized.execute(args[0], args[1], Integer.valueOf(args[2]));
    }
}
