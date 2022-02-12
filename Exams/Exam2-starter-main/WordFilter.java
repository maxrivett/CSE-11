// EXAM INSTRUCTIONS:
// All of your code for Task 3 goes in this file.
class WordFilter{
    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("Provide at least one command-line argument");
            return;
        }
        String pattern = args[0];
        boolean flag = false;
        for (int i = 1; i < args.length; i++) {
            if (args[i].contains(pattern)) {
                System.out.println(args[i]);
                flag = true;
            }
        }
        if (!flag) {System.out.println("0 words contained \"" + pattern +"\"");}
    }
}