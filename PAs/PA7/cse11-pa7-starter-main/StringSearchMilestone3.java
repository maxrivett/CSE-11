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
            if (querySplit[ctr].equals("length")) {
                String[] arr = length(fileLines, Integer.parseInt(querySplit[ctr+1]));
                for (String line : arr) {
                    System.out.println(line);
                }
            } else if (querySplit[ctr].equals("greater")) {
                String[] arr = greater(fileLines, Integer.parseInt(querySplit[ctr+1]));
                for (String line : arr) {
                    System.out.println(line);
                }
            } else if (querySplit[ctr].equals("less")) {
                String[] arr = less(fileLines, Integer.parseInt(querySplit[ctr+1]));
                for (String line : arr) {
                    System.out.println(line);
                }
            } else if (querySplit[ctr].equals("contains")) {
                String[] arr = contains(fileLines, querySplit[ctr+1]);
                for (String line : arr) {
                    System.out.println(line);
                }
            } else if (querySplit[ctr].equals("starts")) {
                String[] arr = starts(fileLines, querySplit[ctr+1]);
                for (String line : arr) {
                    System.out.println(line);
                }
            } else if (querySplit[ctr].equals("ends")) {
                String[] arr = ends(fileLines, querySplit[ctr+1]);
                for (String line : arr) {
                    System.out.println(line);
                }
            } else if (querySplit[ctr].substring(0,3).equals("not")) {
                String[] arr = not(fileLines,query);
                for (String line : arr) {
                    System.out.println(line);
                }
            }
        }
        if (args.length > 2) {transform = args[2];}
    }

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
        int cnt = 0;
        for (int i = 0; i < flags.length; i++) {
            if (flags[i] == false) {
                leftover[cnt] = lines[i];
                cnt++;
            }
        }
        return leftover;
    }

}