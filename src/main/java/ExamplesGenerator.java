import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("all")
public class ExamplesGenerator {
    /**
     * WARNING Don't run this. It will delete all your work. This is cowboy stuff.
     */
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        System.out.println("~~~Don't run this. It will DELETE ALL your progress. DO NOT~~~");
        System.out.println("so, do you want to run this?");
        if(!scanner.hasNext("omgyessfr")){
            throw new RuntimeException("no");
        }

        Files.newDirectoryStream(Path.of("src/main/java/solutions"))
                .forEach(ExamplesGenerator::createExampleFile);

        Files.newDirectoryStream(Path.of("src/test/java/solutions"))
                .forEach(ExamplesGenerator::createTestFile);
    }

    private static void createTestFile(Path testFilePath) {
        try {
            var path = Path.of("src/test/java/examples", testFilePath.getFileName().toString());
            var content = new ArrayList<>(Arrays.asList("package examples;"));
            Files.readAllLines(testFilePath).stream()
                    .skip(1)
                    .forEach(content::add);
            Files.write(path, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void createExampleFile(Path solutionPath) {
        try {
            var path = Path.of("src/main/java/examples", solutionPath.getFileName().toString());
            var content = new ArrayList<>(Arrays.asList("package examples;"));
            Files.readAllLines(solutionPath).stream()
                    .skip(1)
                    .filter(line -> !line.startsWith("        "))
                            .forEach(line->{
                                if(line.equals("    }")){
                                    content.add("        throw new UnsupportedOperationException();");
                                }
                                content.add(line);
                            });

            Files.write(path, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
