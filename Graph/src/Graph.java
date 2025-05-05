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

    // 1. Incidence method
    public boolean incidence(Set<String> setA, Set<String> setB) {
        return setB.containsAll(setA);
    }

    // 2. Degree method
    public int[] degree() {
        int[] degrees = new int[size];
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                count += adjMatrix[i][j];
            }
            degrees[i] = count;
        }
        return degrees;
    }

    // 3. Isomorphism method
    public boolean isIsomorphic(Graph other) {
        if (this.size != other.size) return false;
        int[] thisDeg = this.degree();
        int[] otherDeg = other.degree();
        Arrays.sort(thisDeg);
        Arrays.sort(otherDeg);
        return Arrays.equals(thisDeg, otherDeg);
    }

    // 4. Power Set method
    public List<Set<String>> powerSet(Set<String> originalSet) {
        List<Set<String>> subsets = new ArrayList<>();
        List<String> list = new ArrayList<>(originalSet);
        int n = list.size();
        for (int i = 0; i < (1 << n); i++) {
            Set<String> subset = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(list.get(j));
                }
            }
            subsets.add(subset);
        }
        return subsets;
    }
    public String[] getVertexData() {
        return vertexData;
    }
    
    // 5. Euler characteristic method
    public void euler() {
        int V = size;
        int E = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (adjMatrix[i][j] == 1) E++;
            }
        }
        int F = 1 + E - V; // Only for planar graphs
        System.out.println("Euler Characteristic χ = V - E + F = " + V + " - " + E + " + " + F + " = " + (V - E + F));
    }
}
