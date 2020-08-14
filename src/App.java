import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {

        Path inputFilePath = Path.of("resources/Pride_and_Prejudice.txt");
        List<String[]> text = readTextFile(inputFilePath);
        HashMap<String, Integer> ocurrencyList = countWordsOcurrencies(text);

        writeOuputFile(ocurrencyList);
    }

    public static HashMap<String, Integer> countWordsOcurrencies(List<String[]> text) {

        int count = 1;
        HashMap<String, Integer> wordOcurrencyList = new HashMap<>();

        for (String[] line : text) {
            for (String word : line) {
                if (wordOcurrencyList.containsKey(word)) {
                    count = wordOcurrencyList.get(word) + 1;
                } else {
                    count = 1;
                }
                wordOcurrencyList.put(word, count);
            }
        }

        return wordOcurrencyList;
    }

    public static List<String[]> readTextFile(Path inputFilePath) throws IOException {

        List<String[]> text = Files.lines(Paths.get(inputFilePath.toUri()))
            .map(line -> line
                .toLowerCase()
                .split(" "))
            .collect(Collectors.toList());

        return text;
    }

    public static void writeOuputFile(HashMap<String, Integer> wordCount) throws IOException {
        FileWriter fileWriter = new FileWriter("resources/wordOcurrencyListOutputFile.txt");
        fileWriter.write(wordCount.toString());
        fileWriter.close();
    }
}
