import ru.spbau.mit.kazakov.HashTable.*;

public class Main {

    public static void main(String[] args) {
        HashTable a = new HashTable();
        a.put("1", "44");
        a.put("2", "55");
        System.out.println(a.put("3", "66"));
        System.out.println(a.remove("2"));
        System.out.println(a.contains("3"));
        System.out.println(a.get("1"));
        System.out.println(a.get("1d"));
        System.out.println(a.put("3", "33"));
        System.out.println(a.get("3"));
        System.out.println(a.size());
        a.clear();
        System.out.println(a.size());
        System.out.println(a.contains("3"));

        System.out.println();
        for (int i = 0; i < 16; i++) {
            a.put(Integer.toString(i), Integer.toString(i));
        }
        System.out.println(a.size());
        HashTable olya = new HashTable();
        olya.remove("2");
    }
}
