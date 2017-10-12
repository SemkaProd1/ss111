import com.sun.org.apache.xpath.internal.operations.And;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;


public class MultiSetTests {
    @Test
    public void MostHappenedAdd(){
        MultiSet multiSet1 = new MultiSet();
        multiSet1.add("Ilya");
        multiSet1.add(19);
        int i = multiSet1.size();

        assertEquals(i, 2);
    }
    @Test
    public void MostHappenedAdAll(){
        MultiSet ms = new MultiSet();
        ms.add("Ilya");
        ms.add(19);
        ms.addAll(Arrays.asList("Ilya", "Ilya"));
        int i = ms.size();

        assertEquals(i, 4);
    }

    @Test
    public void MostHappenedIsEmpty(){
        MultiSet multiSet = new MultiSet();
        assertEquals(multiSet.isEmpty(), true);
        multiSet.add("Ilya");
        assertEquals(multiSet.isEmpty(), false);
    }

    @Test
    public void MostHappenedContains(){
        MultiSet multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList("Ilya", "Igor", "Ivan"));
        assertEquals(multiSet.contains("Ilya"), true);
        assertEquals(multiSet.contains("antiIlya"), false);
    }
    @Test
    public void MostHappenedContainsAll(){
        MultiSet multiSet = new MultiSet();
        multiSet.add("Ilya", 2);
        multiSet.add("Andrey");
        assertEquals(multiSet.containsAll(Arrays.asList("Ilya", "Andrey")), true);
    }
    @Test
    public void MostHappenedRemove(){
        MultiSet multiSet = new MultiSet();
        multiSet.add("Ilya", 2);
        multiSet.add("Andrey", 5);
        multiSet.remove("Ilya");
        multiSet.remove("Andrey", 2);
        assertEquals(multiSet.contains("Ilya"), true);
        multiSet.remove("Ilya");
        assertEquals(multiSet.contains("Ilya"), false);
        assertEquals(multiSet.containsAll(Arrays.asList("Andrey", "Andrey")),true);
    }
    @Test
    public void MostHappenedRemoveAll(){
        MultiSet multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList(1, 1, 2, 3, 5));
        multiSet.removeAll(Arrays.asList(1, 1));
        int i = multiSet.size();
        assertEquals(i, 3);

    }
    @Test
    public void MostHappenedClear(){
        MultiSet multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList(1, 1, 2, 3, 5));
        multiSet.clear();
        int i = multiSet.size();
        assertEquals(i,0);
    }
    @Test
    public void MostHappenedToString(){
        MultiSet multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList(1, 1, 1, 2, 3, 5, "Ilya"));
        String s = "[1 x 3, 2, 3, 5, Ilya]";
        assertEquals(multiSet.toString(), s);
    }
    @Test
    public void MostHappenedToUniqueSet(){
        MultiSet multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList(1, 1, 1, 2, 3, 5, "Ilya"));
        assertEquals(multiSet.uniqueSet().size(), 5);
        String s = "[1, 2, 3, 5, Ilya]";
        assertEquals(s, multiSet.uniqueSet().toString());
    }
    @Test
    public void MostHappenedSetCount(){
        MultiSet multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList(1, 1, 1, 2, 3, 5, "Ilya"));
        multiSet.setCount(1, 5);
        String s = "[1 x 5, 2, 3, 5, Ilya]";
        assertEquals(s, multiSet.toString());

    }
    @Test
    public void MostHappenedSetCountnune() {
        MultiSet multiSet = new MultiSet();
        multiSet.addAll(Arrays.asList(1, 1, 1, 2, 3, 5, "Ilya"));
        multiSet.setCount(1, 0); //remove all "1"
        String s = "[2, 3, 5, Ilya]";
        assertEquals(s, multiSet.toString());
    }
//    @Test
//    public void MostHappenedRetainAll(){ // не работает %(
//        MultiSet multiSet = new MultiSet();
//        multiSet.addAll(Arrays.asList(1, 1, 1, 2, 3, 5, "Ilya"));
//        multiSet.retainAll(Arrays.asList(1, "Ilya"));
//        String s = "[1 x 3, Ilya]";
//        assertEquals(s,  multiSet.retainAll(Arrays.asList(1, "Ilya")));
//    }
}

