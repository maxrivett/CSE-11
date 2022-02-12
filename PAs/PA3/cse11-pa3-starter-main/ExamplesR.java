class R {
    String str;
    R r1;
    R (String str, R r1) {
        this.str = str;
        this.r1 = r1;
    }
}

class ExamplesR {
    R rObject = new R("recursion", rObject);
    /*
        This will not work. 
        The only way to create an R object is to pass a string and an R object as arguments.
        (At least with the current constructor)
        This could be made possible if we were to add a second constructor with no parameters.
        This way, we can instantiate an R object without having an R object already made.
        Then this R object could be used with the current constructor we are using.
    */
}