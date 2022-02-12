import tester.*;
interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toText();
    double toDouble();
}

class WholeNumber implements Number {
      int n;
      WholeNumber(int n){
          this.n = n;
      }
      public int numerator(){return n;}
      public int denominator(){return 1;}
      public Number add(Number other){
          Fraction f = new Fraction((((this.numerator()*other.denominator()) + other.numerator())), other.denominator());
          return f;
          //  return (((this.numerator()*other.denominator()) + other.numerator()) / other.denominator());
      }
      public Number multiply(Number other){
          Fraction f = new Fraction(((this.numerator() * other.numerator())), other.denominator());
          return f;
          //   return ((this.numerator() * other.numerator()) / other.denominator());
        }
      public String toText(){return n + "";}
      public double toDouble(){
          double d = this.n*1.0;
          return d;
      }
}

class Fraction implements Number {
    int n;
    int d;
    Fraction(int n, int d) {
        this.n = n;
        this.d = d;

        //simplify fractions
        int num = (this.n / 2)+1;
        for (int i = num; i > 0; i--) {
            if (this.n%num==0 && this.d%num==0) {
                this.n /= num;
                this.d /= num;
                break;
            }
        }
        
    }
    public int numerator(){return this.n;}
    public int denominator(){return this.d;}
    public Number add(Number other){
        Fraction f = new Fraction((this.denominator()*other.numerator() + this.numerator()*other.denominator()),(this.denominator()*other.denominator()));
        return f;
        // return (this.denominator()*other.numerator() + this.numerator()*other.denominator())/(this.denominator()*other.denominator());
    }
    public Number multiply(Number other){
        Fraction f = new Fraction((this.numerator() * other.numerator()),(this.denominator()*other.denominator()));
        return f;
        // return (this.numerator() * other.numerator()) / (this.denominator()*other.denominator());
    }
    public String toText(){return this.numerator()+"/"+this.denominator();}
    public double toDouble(){
        double d = this.numerator()*1.0/this.denominator()*1.0;
        return d;
    }
}

class ExamplesNumbers {
  Number n1 = new WholeNumber(5);
    Number n2 = new WholeNumber(7);
    Number n3 = new Fraction(7, 2);
    Number n4 = new Fraction(1, 2);

    void testAdd(Tester t) {
        t.checkExpect(this.n1.add(this.n2).toDouble(), 12.0);
        t.checkExpect(this.n1.add(this.n3).toDouble(), 5 + 7.0/2.0);
        t.checkExpect(this.n3.add(this.n3).toDouble(), 7.0);
    }

    void testMultiply(Tester t) {
        t.checkExpect(this.n1.multiply(this.n4).toDouble(), 2.5);
        t.checkExpect(this.n3.multiply(this.n4).toDouble(), 7.0/4.0);
    }

    void testNumDem(Tester t) {
        t.checkExpect(this.n3.numerator(), 7);
        t.checkExpect(this.n1.numerator(), 5);
        t.checkExpect(this.n4.denominator(), 2);
        t.checkExpect(this.n2.denominator(), 1);
    }

    void testToString(Tester t) {
        t.checkExpect(this.n4.toText(), "1/2");
        t.checkExpect(this.n3.toText(), "7/2");
        t.checkExpect(this.n2.toText(), "7");
    }
    //Exploration
    double a = 0.1 + 0.2 + 0.3; 
    double b = 0.1 + (0.2 + 0.3); 
    Fraction f1 = new Fraction(1,10);
    Fraction f2 = new Fraction(2,10);
    Fraction f3 = new Fraction(3,10);
    String c = this.f1.add(this.f2).add(this.f3).toText();
    String d = this.f1.add(this.f2.add(this.f3)).toText();
}