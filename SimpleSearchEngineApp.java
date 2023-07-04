import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleSearchEngineApp {
    private List<String> documents;

    public SimpleSearchEngineApp() {
        documents = new ArrayList<>();
    }

    public void addDocument(String document) {
        documents.add(document.toLowerCase());
    }

    public List<String> search(String query) {
        List<String> results = new ArrayList<>();

        for (String document : documents) {
            if (document.contains(query.toLowerCase())) {
                results.add(document);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        SimpleSearchEngineApp searchEngine = new SimpleSearchEngineApp();

        // Sample documents
        searchEngine.addDocument("The quick brown fox jumps over the lazy dog.");
        searchEngine.addDocument("Java is a versatile programming language.");
        searchEngine.addDocument("Simple search engine app in Java.");

        Scanner scanner = new Scanner(System.in);
        String query;

        while (true) {
            System.out.print("Enter your search query (type 'exit' to quit): ");
            query = scanner.nextLine();

            if (query.equalsIgnoreCase("exit")) {
                break;
            }

            List<String> results = searchEngine.search(query);
            if (results.isEmpty()) {
                System.out.println("No results found.");
            } else {
                System.out.println("Search results:");
                for (String result : results) {
                    System.out.println("- " + result);
                }
            }
        }

        scanner.close();
    }
}
