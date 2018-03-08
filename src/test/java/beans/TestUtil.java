package beans;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestUtil {

    public static String readTestCase(Class clazz, String fileName) throws Exception{

        Path path = Paths.get(clazz.getClassLoader()
                .getResource(fileName).toURI());

        StringBuilder data = new StringBuilder();
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> data.append(line).append("\n"));
        lines.close();
        return data.toString();
    }
}
