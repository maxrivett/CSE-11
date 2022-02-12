class Point {
    int x, y;
    Point(int x, int y) {
        this.x=x;
        this.y=y;
    }   
    boolean belowLeftOf(Point p) { return this.x < p.x && this.y < p.y; } 
    boolean aboveRightOf(Point p) { return this.x > p.x && this.y > p.y; } 
}

class ClosestPoints {
    public static void main(String[] args) {
        Point a = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Point b = new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        Point c = new Point(Integer.parseInt(args[4]), Integer.parseInt(args[5]));
        double ab = pythagorean(a,b);
        double bc = pythagorean(b, c);
        double ca = pythagorean(c, a);
        double closest = ab;
        Point point1 = a;
        Point point2 = b;
        if (bc < closest) {
            closest = bc;
            point1 = b;
            point2 = c;
        }
        if (ca < closest) {
            closest = ca;
            point1 = c;
            point2 = a;
        }
        System.out.println("The closest points are ("+point1.x+","+point1.y+") and ("+point2.x+","+point2.y+") at distance " + closest);
    }

    static double pythagorean(Point a, Point b) {
        double side1Squared = Math.pow(Math.abs(a.x-b.x), 2);
        double side2Squared = Math.pow(Math.abs(a.y-b.y), 2);
        double hypotenuse = Math.sqrt(side1Squared + side2Squared);
        return hypotenuse;
    }
}