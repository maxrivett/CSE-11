import tester.*;
class PairSelect {
    static int[] getAs(Pair[] parr) {
        int[] arr = new int[parr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = parr[i].a;
        }
        return arr;
    }
}

class Pair {
    int a,b;
    Pair(int a, int b) {
        this.a=a;
        this.b=b;
    }
}

class ExamplesPairs {
    Pair[] p1 = {new Pair(1,2), new Pair(2,2), new Pair(3,0), new Pair(4,4)};
    int[] a1 = {1, 2, 3, 4};
    Pair[] p2 = {new Pair(0,2), new Pair(-1,2), new Pair(3,0), new Pair(10,4)};
    int[] a2 = {0, -1, 3, 10};
    Pair[] p3 = {new Pair(22,-0), new Pair(25/5,10), new Pair(33,0), new Pair(45,4)};
    int[] a3 = {22, 5, 33, 45};
    Pair[] p4 = {new Pair(-1,2), new Pair(-2,-2), new Pair(-3,0), new Pair(-4,-4)};
    int[] a4 = {-1, -2, -3, -4};
    void testCase(Tester t) {
        t.checkExpect(PairSelect.getAs(p1), a1);
        t.checkExpect(PairSelect.getAs(p2), a2);
        t.checkExpect(PairSelect.getAs(p3), a3);
        t.checkExpect(PairSelect.getAs(p4), a4);
    }
}