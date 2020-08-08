package prog;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class CheckFile {
    public static void Run() throws IOException {
        checkExistence("c:/temp/temp.txt");
    }


    public static void checkExistence(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        if (Files.exists(path)) {
            System.out.println("Ye");
        }

        if (Files.notExists(path)) {
            System.out.println("No");
            List<String> lines = Arrays.asList("The first line", "The second line");
            Path file = Paths.get("new-chess-game.txt");
            Files.write(file, lines, StandardCharsets.UTF_8);
        }
    }
}
