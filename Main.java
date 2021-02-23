import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * My implementation of an adjacency list
 */
public class Main {
    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = {
                {0, 2, 3},
                {4, 0, 5},
                {0, 0, 0}
        };
        List<Vertex> graph = new ArrayList<>();
        List<String> letters = new ArrayList<>(Arrays.asList("A", "B", "C"));
        for (int i = 0; i < n; i++) {
            Vertex vertex = findByName(graph, letters.get(i));
            if (vertex == null) {
                vertex = new Vertex(100);
                graph.add(vertex);
            }
            vertex.setName(letters.get(i));
            List<Edge> edgesFrom = vertex.getEdgesFrom();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                Vertex destinationVertex = findByName(graph, letters.get(j));
                if (destinationVertex == null) {
                    destinationVertex = new Vertex(100, letters.get(j));
                    graph.add(destinationVertex);
                }
                edgesFrom.add(new Edge(matrix[i][j], destinationVertex));
            }
            if (findByName(graph, vertex.getName()) == null)
                graph.add(vertex);
        }
        System.out.println(graph);
        // don't do it
//        Gson gson = new Gson();
//        String json = gson.toJson(graph);
//        System.out.println(json);

    }

    public static Vertex findByName(List<Vertex> graph, String name) {
        for (Vertex vertex : graph) {
            if (vertex.getName().equals(name))
                return vertex;
        }
        return null;
    }
}

class Vertex {
   private List<Edge> edgesFrom = new ArrayList<>();
    private  int data;
    private  String name;

    public Vertex(List<Edge> edgesFrom, int data, String name) {
        this.edgesFrom = edgesFrom;
        this.data = data;
        this.name = name;
    }

    public Vertex(int data) {
        this.data = data;
    }


    public Vertex(int data, String name) {
        this.data = data;
        this.name = name;
    }

    public Vertex() {
    }

    public List<Edge> getEdgesFrom() {
        return edgesFrom;
    }

    public void setEdgesFrom(List<Edge> edgesFrom) {
        this.edgesFrom = edgesFrom;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Edge {
    private  int length;
    private Vertex destination;


    public Edge(int length, Vertex destination) {
        this.length = length;
        this.destination = destination;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }
}
