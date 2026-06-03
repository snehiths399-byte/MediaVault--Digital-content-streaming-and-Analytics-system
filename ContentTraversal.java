import java.util.*;

public class ContentTraversal {

    static Map<String, List<String>> graph =
            new LinkedHashMap<>();

    static void BFS(String start) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {

            String node = queue.poll();

            System.out.print(node);

            if(!queue.isEmpty())
                System.out.print(" -> ");

            for (String neighbour :
                    graph.get(node)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
    }

    static void DFS(String node,
                    Set<String> visited) {

        visited.add(node);

        System.out.print(node);

        for(String neighbour :
                graph.get(node)) {

            if(!visited.contains(neighbour)) {

                System.out.print(" -> ");
                DFS(neighbour, visited);
            }
        }
    }

    public static void main(String[] args) {

        graph.put("Movie_A",
                Arrays.asList(
                "WebSeries_B",
                "Podcast_C",
                "Documentary_D"));

        graph.put("WebSeries_B",
                Collections.emptyList());

        graph.put("Podcast_C",
                Collections.emptyList());

        graph.put("Documentary_D",
                Arrays.asList(
                "ShortFilm_E",
                "MusicVideo_F"));

        graph.put("ShortFilm_E",
                Collections.emptyList());

        graph.put("MusicVideo_F",
                Collections.emptyList());

        System.out.println(
        "===== MediaVault Recommendation Network =====");

        System.out.println(
        "\nBFS Traversal:");

        BFS("Movie_A");

        System.out.println(
        "\n\nDFS Traversal:");

        DFS("Movie_A",
            new HashSet<>());

        System.out.println(
        "\n\nTraversal Completed");
    }
}