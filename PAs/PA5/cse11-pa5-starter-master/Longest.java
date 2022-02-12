
class Longest{
    public static void main(String[] args) {
        String longest = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > longest.length()) {
                longest = args[i];
            }
        }
        if (args.length > 0) {
            System.out.println(longest);
        }
    }
}