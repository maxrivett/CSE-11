import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class WordSearch {
    public static void main(String[] args) throws IOException {

        String queryFile = Files.readString(Paths.get(args[0]));
        String[] queryLines = queryFile.split("\n");
        List<List<String>> queryMatches = new ArrayList<>(); // index matches with queryLines

        List<String> files = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            files.add(args[i]);
        }

        List<String[]> fileLines = new ArrayList<>();
        for (String file : files) {
            String readFile = Files.readString(Paths.get(file));
            fileLines.add(readFile.split("\n"));
        }
        List<List<String>> fileMatches = new ArrayList<>(); // index matches with queryLines


        // index of file name in "files" will be same as index of file contents in "fileLines"

        boolean oneMatch = false;

        //make query matches
        for (String query : queryLines) {
            List<String> filesMatchedWithQuery = new ArrayList<>();
            for (int i = 0; i < fileLines.size(); i++) {
                for (String line : fileLines.get(i)) {
                    if (line.contains(query)) {
                        filesMatchedWithQuery.add(files.get(i));
                        oneMatch = true;
                        break;
                    }
                }
            }
            queryMatches.add(filesMatchedWithQuery);
        }

        // make file matches
        for (int i = 0; i < files.size(); i++) {
            List<String> queriesMatchedWithFile = new ArrayList<>();
            for (int j = 0; j < queryMatches.size(); j++) {
                boolean flag = false;
                flag = contains(queryMatches.get(j), files.get(i));
                if (flag == true) {
                    queriesMatchedWithFile.add(queryLines[j]);
                }
            }
            fileMatches.add(queriesMatchedWithFile);
        }

        // find most relevant search term
        int termCount = 0;
        String mostRelevantTerm = "";
        int count = 0;

        for (List<String> list1 : queryMatches) {
            if (list1.size() > termCount) {
                termCount = list1.size();
                mostRelevantTerm = queryLines[count];
            }
            count++;
        }

        // find most relevant document
        int documentCount = 0;
        String mostRelevantDocument = "";
        int counter = 0;

        for (List<String> list1 : fileMatches) {
            if (list1.size() > documentCount) {
                documentCount = list1.size();
                mostRelevantDocument = files.get(counter);
            }
            counter++;
        }

       //------------------------------PRINTS-------------------------------

        if (oneMatch) {
            System.out.println("Most relevant search term: " + mostRelevantTerm);
            System.out.println("Most relevant document: " + mostRelevantDocument + "\n");

            int ctr = 0;
            for (List<String> list1 : queryMatches) {
                System.out.println(queryLines[ctr] + ": " + list1.size() + " " + list1);
                ctr++;
            }

            System.out.println("");
            int cnt = 0;
            for (List<String> list2 : fileMatches) {
                System.out.println(files.get(cnt) + ": " + list2.size() + " " + list2);
                cnt++;
            }
        } else { 
            System.out.println("No matches found.");
        }
    }
    

    //contains method to help out
    static boolean contains(List<String> list, String filename) {
        for (String l : list) {
            if (l.equals(filename)) {
                return true;
            }
        }
        return false;
    }
}

