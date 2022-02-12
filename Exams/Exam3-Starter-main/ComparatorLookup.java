import java.util.List;
import java.util.NoSuchElementException;
import tester.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

class ComparatorLookupTable<K,V> {
    List<K> keys;
    List<V> values;
    
    ComparatorLookupTable(List<K> keys, List<V> values) {
        this.keys = keys;
        this.values = values; 
    }

    ComparatorLookupTable(List<K> keys, List<V> values, Comparator<String> comp) { // needed for examples I guess?
        this.keys = keys;
        this.values = values; 
    }

    ComparatorLookupTable() {} // empty constructor

    // returns true if the key was in the initial list of keys or has been added, false otherwise
    boolean contains(K key) {
        for (K k : this.keys) {
            if (k.equals(key)) { return true; }
        }
        return false;
    }

    // adds the given pair of key and value to the table. If key is already in the table, throw an IllegalArgumentException
    void add(K key, V value) {
        if (this.keys.contains(key)) {
            throw new IllegalArgumentException();
        }
        this.keys.add(key);
        this.values.add(value);
    }

    // returns the value corresponding to the given key, or throws a NoSuchElementException if it isn’t defined
    V find(K key) {
        if (!this.keys.contains(key)) {
            throw new NoSuchElementException(); // This line on stack
        }
        int ele = 0;
        for (K k : this.keys) {
            if (k.equals(key)) {
                break;
            }
            ele++;
        } 
        return this.values.get(ele);
    }

    // changes the value stored in key to value, or throws a NoSuchElementException if it isn’t defined.
    void update(K key, V value) {
        if (!this.keys.contains(key)) {
            throw new NoSuchElementException();
        }
        int ele = 0;
        for (K k : this.keys) {
            if (k.equals(key)) {
                this.values.set(ele, value);
                return; // because this should only happen once
            }
            ele++;
        }
    }

}

class StringComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
      return s1.compareTo(s2);
    }
  }
  class ComparatorLookupTableExamples {
    void testUpdate(Tester t) {
      List<String> strs = new ArrayList<>(Arrays.asList("a", "b", "c"));
      List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
      ComparatorLookupTable<String, Integer> ctl = new ComparatorLookupTable<>(strs, nums, new StringComparator());
      t.checkExpect(ctl.contains("a"), true);
      ctl.update("a", 9);
      t.checkExpect(ctl.find("a"), 9);
      ctl.add("z", 10);
      t.checkExpect(ctl.keys, Arrays.asList("a", "b", "c", "z"));
      t.checkExpect(ctl.values, Arrays.asList(9, 2, 3, 10));
  
      t.checkException(new IllegalArgumentException(), ctl, "add", "z", 5);
      t.checkException(new NoSuchElementException(), ctl, "find", "y");
    }

    void testMore(Tester t) {

        List<Integer> keys = new ArrayList<>(Arrays.asList(100, 101, 102, 103, 104, 105));
        List<String> values = new ArrayList<>(Arrays.asList("This", "is", "a", "very", "complex", "sentence"));
        ComparatorLookupTable<Integer, String> table = new ComparatorLookupTable<>(keys, values);
        t.checkExpect(table.contains(200), false);
        table.add(106, "structure");
        t.checkExpect(table.find(106), "structure");
        table.update(104, "simple");
        t.checkExpect(table.values, Arrays.asList("This", "is", "a", "very", "simple", "sentence", "structure"));

        t.checkException(new IllegalArgumentException(), table, "add", 101, "isn't");
        t.checkException(new NoSuchElementException(), table, "find", 107);
        t.checkException(new NoSuchElementException(), table, "update", 110, "oops");

        t.checkExpect(table.find(200), "what"); // This line on stack


        /*
          class                             method        this reference      other variables
          ComparatorLookupTableExamples     testMore      <ignore>            keys = :1; values = :2; table = :3;
          ComparatorLookupTable             find          :3                  key = 200;
        */

    }
  }