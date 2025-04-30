import java.util.Arrays;
import java.util.*;

class Graph {
    private int[][] adjMatrix;
    private String[] vertexData;
    private int size;

    public Graph(int size) {
        this.size = size;
        this.adjMatrix = new int[size][size];
        this.vertexData = new String[size];
    }

    public void addEdge(int u, int v) {
        if (u >= 0 && u < size && v >= 0 && v < size) {
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1; // undirected
        }
    }

    public void addVertexData(int vertex, String data) {
        if (vertex >= 0 && vertex < size) {
            vertexData[vertex] = data;
        }
    }

    public void printGraph() {
        System.out.println("Adjacency Matrix:");
        for (int[] row : adjMatrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("\nVertex Data:");
        for (int i = 0; i < size; i++) {
            System.out.println("Vertex " + i + ": " + vertexData[i]);
        }
    }

    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(vertexData[v] + " ");
        for (int i = 0; i < size; i++) {
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    public void dfs(String startVertexData) {
        boolean[] visited = new boolean[size];
        int startVertex = -1;
        for (int i = 0; i < size; i++) {
            if (vertexData[i].equals(startVertexData)) {
                startVertex = i;
                break;
            }
        }
        if (startVertex == -1) {
            System.out.println("Start vertex not found!");
            return;
        }
        System.out.print("DFS Traversal: ");
        dfsUtil(startVertex, visited);
        System.out.println();
    }
}

