import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
class StringSearch {
    public static void main(String[] args) throws IOException{
        String file = Files.readString(Paths.get(args[0]));
        String[] fileLines = file.split("\n");
        
        /* For initial testing
        for (String s : fileLines) {
            System.out.println(s);
        }
        */

        String query;
        String transform;
        if (args.length > 1) {
            query = args[1];

            String[] querySplit = query.split("=");
            int ctr = 0;
            if (querySplit[ctr].equals("contains")) {
                String[] arr = contains(fileLines, querySplit[ctr+1].substring(1, querySplit[ctr+1].length()-1));
                for (String line : arr) {
                    System.out.println(line);
                }
            }
            
            

        }
        if (args.length > 2) {transform = args[2];}


    }

    static String[] contains(String[] lines, String s) {
        int ctr = 0;
        for (String line : lines) {
            if (line.contains(s)) {
                ctr++;
            }
        }
        String[] containsLines = new String[ctr];
        int cnt = 0;
        for (String line : lines) {
            if (line.contains(s)) {
                containsLines[cnt] = line;
                cnt++;
            }
        }
        return containsLines;
    }

}