class C1 {
    C2 other;
    C1(C2 other) {
      this.other = other;
    }
  }
  
class C2 {
    int x;
    C2(int x) {
      this.x = x;
    }
}

// Class definition Task1 with requried fields
/* (A field named first of type C2 with its x field equal to 10
    A field named second of type C1. It’s value should be a reference to a C1 object with its other field set to any C2 object other than the one stored in first (you can create another C2 object for this).
    A field named third of type C1. Its value should be a reference to a C1 object. That C1 object should have its other field hold a reference to the same C2 object as the one stored in first.))
*/
class Task1 {
    C2 first = new C2(10);
    C2 secondArg = new C2(999);
    C1 second = new C1(secondArg);
    C1 third = new C1(first);
}