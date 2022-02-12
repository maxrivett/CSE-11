import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
class StringSearch {
    /*

        Problems that need fixing:
            #1 IS SOLVED
            1. When more than one query conditions are inputted with at least one false for everything,
               it should not return any lines. However, at the moment it still does. 
               Example:
                $ java StringSearch "poem.txt" "contains='no'&starts='x'&not(contains='xeno')" "lower"
               Should return nothing becayse none of the lines contain 'no' or start with 'x'.
               However, the return is this:
                this is a short file
                it contains text and this is
                also a haiku

            2. Whenever a text file longer than 3 (not sure exactly what number) is run with queries and
               transformations, there is an ArrayIndexOutOfBoundsException.
               Example: 
               $ java StringSearch "words" "contains='no'&starts='x'&not(contains='xeno')" "lower"
                Should return:
                    xanthocyanopsia
                    xanthocyanopsy
                    xanthocyanopy
                    xanthomelanous
                    xoanon
                    xylenol
                    xyloquinone
                    xylorcinol
               However, it returns this:
                    Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 235886 out of bounds for length 235886
    */



    public static void main(String[] args) throws IOException{
        String file = Files.readString(Paths.get(args[0]));
        String[] fileLines = file.split("\n");

        String query;
        String transform;
        int queryArrayLen = 0;
        if (args.length == 1) {
            for (String line : fileLines) {
                System.out.println(line);
            }
        }
        if (args.length == 2) {
            query = args[1];
            String[] querySplit = query.split("&");
            String[] arr = queryResult(fileLines, querySplit[0]);
            for (String currentQuery : querySplit) {
                arr = queryResult(fileLines, currentQuery);
            }
            for (String line : arr) {
                System.out.println(line);
            }

        }
        if (args.length > 2) {
            //get query arr
            query = args[1];
            String[] querySplit = query.split("&");
            String[] queryArr = queryResult(fileLines, querySplit[0]);
            for (String currentQuery : querySplit) {
                queryArr = queryResult(queryArr, currentQuery);
            }

            //transform the query arr
            transform = args[2];
            String[] transformSplit = transform.split("&");
            String[] transformArr = transformResult(queryArr, transformSplit[0]); 
            for (String currentTransform : transformSplit) {
                transformArr = transformResult(transformArr, currentTransform);
            }
            for (String line : transformArr) {
                System.out.println(line);
            }

        }
    }

    static String[] queryResult(String[] fileLines, String query) {
        String[] querySplit = query.split("=");
        int ctr = 0;
        if (querySplit[ctr].equals("length")) {
            String[] arr = length(fileLines, Integer.parseInt(querySplit[ctr+1]));
            return arr;
        } else if (querySplit[ctr].equals("greater")) {
            String[] arr = greater(fileLines, Integer.parseInt(querySplit[ctr+1]));
            return arr;
        } else if (querySplit[ctr].equals("less")) {
            String[] arr = less(fileLines, Integer.parseInt(querySplit[ctr+1]));
            return arr;
        } else if (querySplit[ctr].equals("contains")) {
            String[] arr = contains(fileLines, querySplit[ctr+1]);
            return arr;
        } else if (querySplit[ctr].equals("starts")) {
            String[] arr = starts(fileLines, querySplit[ctr+1]);
            return arr;
        } else if (querySplit[ctr].equals("ends")) {
            String[] arr = ends(fileLines, querySplit[ctr+1]);
            return arr;
        } else if (querySplit[ctr].substring(0,3).equals("not")) {
            String[] arr = not(fileLines,query);
            return arr;
        }
        return null;
    }

    static String[] transformResult(String[] lines, String transform) {
        String[] transformSplit = transform.split("=");
        int ctr = 0;

        if (transformSplit[ctr].equals("upper")) {
            String[] arr = upper(lines);
            return arr;
        } else if (transformSplit[ctr].equals("lower")) {
            String[] arr = lower(lines);
            return arr;
        } else if (transformSplit[ctr].equals("first")) {
            String[] arr = first(lines, Integer.parseInt(transformSplit[ctr+1]));
            return arr;
        } else if (transformSplit[ctr].equals("last")) {
            String[] arr = last(lines, Integer.parseInt(transformSplit[ctr+1]));
            return arr;
        } else if (transformSplit[ctr].equals("replace")) {
            String[] arr = replace(lines, transformSplit[ctr+1]);
            return arr;
        }
        return null;
    }

    /* 
    -------------------------------------------------
    |              QUERY METHODS                    |
    -------------------------------------------------
    */ 
    static String[] length(String[] lines, int n) {
        int ctr = 0;
        for (String line : lines) {
            if (line.length() == n) {
                ctr++;
            }
        }
        String[] matchingLines = new String[ctr];
        int cnt = 0;
        for (String line : lines) {
            if (line.length() == n) {
                matchingLines[cnt] = line;
                cnt++;
            }
        }
        return matchingLines;
    }

    static String[] greater(String[] lines, int n) {
        int ctr = 0;
        for (String line : lines) {
            if (line.length() > n) {
                ctr++;
            }
        }
        String[] greaterLines = new String[ctr];
        int cnt = 0;
        for (String line : lines) {
            if (line.length() > n) {
                greaterLines[cnt] = line;
                cnt++;
            }
        }
        return greaterLines;
    }

    static String[] less(String[] lines, int n) {
        int ctr = 0;
        for (String line : lines) {
            if (line.length() < n) {
                ctr++;
            }
        }
        String[] lesserLines = new String[ctr];
        int cnt = 0;
        for (String line : lines) {
            if (line.length() < n) {
                lesserLines[cnt] = line;
                cnt++;
            }
        }
        return lesserLines;
    }

    static String[] contains(String[] lines, String s) {
        int ctr = 0;
        s=s.substring(1, s.length()-1);
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

    static String[] starts(String[] lines, String s) {
        int ctr = 0;
        s=s.substring(1, s.length()-1);
        int slen = s.length();
        for (String line : lines) {
            if (line.substring(0, slen).equals(s)) {
                ctr++;
            }
        }
        String[] startsLines = new String[ctr];
        int cnt = 0;
        for (String line : lines) {
            if (line.substring(0, slen).equals(s)) {
                startsLines[cnt] = line;
                cnt++;
            }
        }
        return startsLines;
    }

    static String[] ends(String[] lines, String s) {
        int ctr = 0;
        s=s.substring(1, s.length()-1);
        int slen = s.length();
        for (String line : lines) {
            if (line.substring(line.length() - slen, line.length()).equals(s)) {
                ctr++;
            }
        }
        String[] endsLines = new String[ctr];
        int cnt = 0;
        for (String line : lines) {
            if (line.substring(line.length() - slen, line.length()).equals(s)) {
                endsLines[cnt] = line;
                cnt++;
            }
        }
        return endsLines;
    }

    static String[] not(String[] lines, String query) {
        query = query.substring(4,query.length()-1); // to remove the "not()"
        String[] querySplit = query.split("=");
        String[] arr;
        int arrlen = 0;
        
        // Essentially want to subtract the lines that meet the condition
        // inside the not(______) from the total lines       
        if (querySplit[0].equals("length")) {
            String s = querySplit[1];
            return notHelper(lines, length(lines, Integer.parseInt(s)), s);
        } else if (querySplit[0].equals("greater")) {
            String s = querySplit[1];
            return notHelper(lines, greater(lines, Integer.parseInt(s)), s);
        } else if (querySplit[0].equals("less")) {
            String s = querySplit[1];
            return notHelper(lines, less(lines, Integer.parseInt(s)), s);
        } else if (querySplit[0].equals("contains")) {
            String s = querySplit[1];
            return notHelper(lines, contains(lines, s), s);
        } else if (querySplit[0].equals("starts")) {
            String s = querySplit[1];
            return notHelper(lines, starts(lines, s), s);
        } else if (querySplit[0].equals("ends")) {
            String s = querySplit[1];
            return notHelper(lines, ends(lines, s), s);
        } 

        //base case 
        return null;
    }

    static String[] notHelper(String[] lines, String[] arr, String s) {
        int arrlen = lines.length - arr.length;
        int ctr = 0;
        String[] leftover = new String[arrlen];
        boolean[] flags = new boolean[lines.length];


        /*

            Essentially want to check whether or not a line in the lines array is equal to a line in the other array
            If so, set the element of a boolean array at the index of the current line to true to signify this
            Else, set the element to false, which means this line DOES NOT match the line in the other array
            In this case, we want to put this line into a new array
            Which we do so by looping through the boolean array and copying the lines at that element in the lines array into a new array

        */


        // for each way
        for (int i = 0; i < lines.length; i++) {
            // for (String arrLine : arr) {
            //     if (textLine.equals(arrLine)) {
            //         flags[ctr] = true; 
            //     } else {
            //         flags[ctr] = false;
            //     }
            // }
            for (int j = 0; j < arr.length; j++) {
                // System.out.println(ctr + " --- " + lines[i] + " = " + arr[j] + " - Answer: " + lines[i].equals(arr[j]));
                if (lines[i].equals(arr[j])) {
                    flags[ctr] = true; 
                    break; // this singular line caused 4+ hours of debugging pain
                } else {
                    flags[ctr] = false;
                }
            }
            ctr++;
        }
        // System.out.println("TEXT LINES");
        // for (String textLine : lines) {
        //     System.out.println(textLine + " " + textLine.length());
        // }

        // System.out.println("ARR LINES");
        // for (String arrLine : arr) {
        //     System.out.println(arrLine + " " + arrLine.length());
        // }

        // System.out.println("FLAG LINES");
        // for (boolean flag : flags) {
        //     System.out.println(flag);
        // }
        // counted for loop way
        // for (int i = 0; i < lines.length; i++) {
        //     for (int j =0; j < arr.length; j++) {
        //         if (lines[i].equals(arr[j])) {
        //             flags[i] = true;
        //         } else {
        //             flags[i] = false;
        //         }
        //     }
        // }

        int cnt = 0;
        for (int i = 0; i < flags.length; i++) {
            // System.out.println(cnt);
            // System.out.println(flags[i]);
            if (flags[i] == false) {
                // System.out.println("in if condition");
                // System.out.println(leftover[cnt]);
                leftover[cnt] = lines[i];
                // System.out.println(leftover[cnt]);
                cnt++;
            }
        }
        return leftover;
    }

    /* 
    -------------------------------------------------
    |             TRANSFORM METHODS                 |
    -------------------------------------------------
    */ 

    static String[] upper(String lines[]) {
        String[] arr = new String[lines.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = lines[i].toUpperCase();
        }
        return arr;
    }

    static String[] lower(String lines[]) {
        String[] arr = new String[lines.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = lines[i].toLowerCase();
        }
        return arr;
    }

    static String[] first(String lines[], int n) {
        String[] arr = new String[lines.length];
        for (int i = 0; i < arr.length; i++) {
            if (lines[i].length() <= n) { arr[i] = lines[i];} else {
                arr[i] = lines[i].substring(0, n);
            }
        }
        return arr;
    }

    static String[] last(String lines[], int n) {
        String[] arr = new String[lines.length];
        for (int i = 0; i < arr.length; i++) {
            if (lines[i].length() <= n) { arr[i] = lines[i];} else {
                arr[i] = lines[i].substring(lines[i].length() - n, lines[i].length());
            }
        }
        return arr;
    }

    static String[] replace(String lines[], String s) {
        String[] sSplit = s.split(";");
        String s1 = sSplit[0].substring(1, sSplit[0].length()-1); // remove ''
        String s2 = sSplit[1].substring(1, sSplit[1].length()-1); // remove ''
        String[] arr = new String[lines.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = lines[i].replace(s1,s2);
        }
        return arr;
    }
}