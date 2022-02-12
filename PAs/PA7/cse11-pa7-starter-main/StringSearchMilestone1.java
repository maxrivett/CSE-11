import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
class StringSearch {
    public static void main(String[] args) throws IOException{
        String file = Files.readString(Paths.get(args[0]));
        String[] fileLines = file.split("\n");
        for (String s : fileLines) {
            System.out.println(s);
        }
    }
}