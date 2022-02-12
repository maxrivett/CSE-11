import tester.*;
class LongStrings {
    static String[] longStrings(String[] sarr, int n) {
        int longCount = 0;
        for (String s : sarr) {
            if (s.length() >= n) {
                longCount++;
            }
        }
        String[] longArr = new String[longCount];
        int ctr = 0;
        for (String s : sarr) {
            if (s.length() >= n) {
                longArr[ctr] = s;
                ctr++;
            }
        }
        return longArr;
    }    
}

class ExamplesLongStrings {
    String[] s1 = {"Hello", "my", "name", "is", "Max", "which", "isn't", "short", "for", "Maximilian"};
    String[] test1 = {"Hello", "which", "isn't", "short", "Maximilian"};
    String[] test2 = {"Hello", "my", "name", "is", "Max", "which", "isn't", "short", "for", "Maximilian"};
    String[] test3 = {"Hello", "name", "Max", "which", "isn't", "short", "for", "Maximilian"};
    String[] test4 = {};

    void testCase(Tester t) {
        t.checkExpect(LongStrings.longStrings(s1, 5), test1);
        t.checkExpect(LongStrings.longStrings(s1, 0), test2);
        t.checkExpect(LongStrings.longStrings(s1, 3), test3);
        t.checkExpect(LongStrings.longStrings(s1, 11), test4);
    }
}