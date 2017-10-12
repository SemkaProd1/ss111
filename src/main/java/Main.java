import java.util.Arrays;
import java.util.Collections;


public class Main {
    public static void main(String[] args) {
        MultiSet<String> multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList("1", "2", "3"));
        multiSet.retainAll(Collections.singletonList("2"));
        System.out.println(multiSet);


    }
}
