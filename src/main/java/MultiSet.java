import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class MultiSet<E extends Comparable> implements Set<E>, Comparable<MultiSet<E>>{

    private List<E> values;
    int size = 0;
    private List<Integer> frequency;
    private final String ERROR_MSG = "Count cannot be negative: ";


    public MultiSet() {
        values = new ArrayList<>();
        frequency = new ArrayList<>();

    }


    public int add(E element, int count) {

        if (count < 0) {
            throw new IllegalArgumentException(ERROR_MSG + count);
        }
        int index = values.indexOf(element);
        int prevCount = 0;

        if (index != -1) {
            prevCount = frequency.get(index);
            frequency.set(index, prevCount + count);
        }

        else if (count != 0) {
            values.add(element);
            frequency.add(count);
        }
        return prevCount;
    }

    public boolean add(E element) {
        return add(element, 1) >= 0;
    }
    public boolean addAll(Collection<? extends E> c){
        for (E element: c){
            add(element, 1);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) { //доработать
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(values.get(i))) {
                values.set(j++, values.get(i));
                j++;
            }
        }
        if (j == size) {
            return false;
        }
        for (; j < size; j++) {
            values.set(j, null);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ret = false;
        for (final Object t : c) {
            ret |= remove(t);
        }
        return ret;
    }

    @Override
    public void clear() {
        values.isEmpty();
        for (int i = 0; i < values.size(); i++){
            Collection<E> collection = values;
            Collection<Integer> collection2 = frequency;
            collection2.clear();
            size = 0;
            collection.clear();

        }

    }


    public boolean remove(Object element) {
        return remove(element, 1) > 0;
    }

    public int remove(Object element, int count) {

        if (count < 0) {
            throw new IllegalArgumentException(ERROR_MSG + count);
        }
        int index = values.indexOf(element);

        if (index == -1) {
            return 0;
        }
        int prevCount = frequency.get(index);

        if (prevCount > count) {
            frequency.set(index, prevCount - count);
        }
        else {
            values.remove(index);
            frequency.remove(index);
        }
        return prevCount;
    }

    public boolean contains(Object element) {
        return values.contains(element);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }


//    private class SetIterator implements Iterator<E> {
//        private int position = 0;
//
//        @Override
//        public boolean hasNext() {
//            if (position < values.size() && position  < frequency.size()){
//                return true;}
//            else{
//                return false;}
//        }
//
//        @Override
//        public E next() {
//            E element;
//            element = values.get(0);
//            int a = frequency.size();
//            int b = values.size();
//            int index = values.indexOf(element);
//            if (this.hasNext()){
//                index++;
//                return values.get(index);
//
//            }
//            else{
//                return null;}
//        }
//    }
    @Override
    public Object[] toArray() {
        return toArray((E[]) new Comparable[size]);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        System.arraycopy(values.toArray(), 0, a, 0, size);
        return a;
    }


    public boolean containsAll(Collection<?> c) {
        return values.containsAll(c);
    }

    public int setCount(E element, int count) {
        int index = values.indexOf(element);
        if (count < 0) {
            throw new IllegalArgumentException(ERROR_MSG + count);

        }
        if (count == 0) {
            remove(element, size());
        }

        if (index == -1){
            return add(element, count);
        }
        int prevCount = frequency.get(index);
        frequency.set(index, count);
        return prevCount;
    }

    public int count(Object element) {
        int index = values.indexOf(element);
        return (index == -1) ? 0 : frequency.get(index);
    }


    public void forEach(Consumer<? super E> action) {
        List<E> all = new ArrayList<>();
        for (int i = 0; i < values.size(); i++){
            for (int j = 0; j < frequency.get(i); j++){
                all.add(values.get(i));
            }
        }
        all.forEach(action);
    }

    public MultiSet<E> union(MultiSet<E> second){
            MultiSet<E> union = new MultiSet<E>();
            MultiSet<E> join = new MultiSet<E>();
            join.addAll(this.values);
            join.addAll(second.values);

            for(E o : join){
                int i = this.count(o);
                int j = second.count(o);
                i = i > j ? i : j;
                for(int c = 0; c < i; c++){
                    union.add(o);

                }
            }

            return union;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < values.size(); i++) {
            sb.append(values.get(i));
            if (frequency.get(i) > 1)
                sb.append(" x ").append(frequency.get(i));
            if (i != values.size() - 1)
                sb.append(", ");
        }
        return sb.append("]").toString();
    }

    @Override
    public int compareTo(MultiSet<E> o) {
        int c = 0;
        int i = 0;
        while (c == 0) {
            if (i == size) {
                return i == o.size ? 0 : -1;
            }
            if (i == o.size) {
                    return 1;
            }
            c = values.get(i).compareTo(o.values.get(i));
            i++;
            }
        return c;
    }
    public Set<E> uniqueSet() {
        return values.stream().collect(Collectors.toSet());
    }
    public boolean isEmpty() {
        return values.size() == 0;
    }

    public int size() {
        for (Integer i: frequency){
            size += i;
        }
        return size;
    }
}