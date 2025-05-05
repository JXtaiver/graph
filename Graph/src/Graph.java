

class Graph {
    private int[][] adjMatrix; // Adjacency matrix to represent edges (for unweighted graph)
    private String[] vertexData; // Data associated with each vertex
    private int size;
    private boolean isWeighted; // Flag to check if the graph is weighted
    private int[][] weightMatrix; // Matrix to store edge weights (for weighted graph)

    // Constructor for unweighted graph
    public Graph(int size) {
        this.size = size;
        this.adjMatrix = new int[size][size];
        this.vertexData = new String[size];
        this.isWeighted = false;
    }

    // Constructor for weighted graph
    public Graph(int size, boolean isWeighted) {
        this(size); // Calling the basic constructor
        this.isWeighted = isWeighted;
        if (isWeighted) {
            this.weightMatrix = new int[size][size];
        }
    }

    // Add edge method (for unweighted and weighted graphs)
    public void addEdge(int u, int v, int weight) {
        if (isWeighted) {
            weightMatrix[u][v] = weight;
            weightMatrix[v][u] = weight;
        } else {
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1; // undirected
        }
    }

    // Add vertex data
    public void addVertexData(int vertex, String data) {
        if (vertex >= 0 && vertex < size) {
            vertexData[vertex] = data;
        }
    }

    // Print graph (adjacency matrix and vertex data)
    public void printGraph() {
        System.out.println("Adjacency Matrix:");
        if (isWeighted) {
            for (int[] row : weightMatrix) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } else {
            for (int[] row : adjMatrix) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }
        System.out.println("\nVertex Data:");
        for (int i = 0; i < size; i++) {
            System.out.println("Vertex " + i + ": " + vertexData[i]);
        }
    }

    // DFS method
    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(vertexData[v] + " ");
        for (int i = 0; i < size; i++) {
            if ((isWeighted ? weightMatrix[v][i] > 0 : adjMatrix[v][i] == 1) && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    // DFS traversal starting from a specific vertex
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

    // Degree method (returns degree of each vertex)
    public int[] degree() {
        int[] degrees = new int[size];
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (isWeighted ? weightMatrix[i][j] > 0 : adjMatrix[i][j] == 1) {
                    count++;
                }
            }
            degrees[i] = count;
        }
        return degrees;
    }

    // Euler characteristic for planar graphs
    public void euler() {
        int V = size;
        int E = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if ((isWeighted ? weightMatrix[i][j] > 0 : adjMatrix[i][j] == 1)) E++;
            }
        }
        int F = 1 + E - V; // Only for planar graphs
        System.out.println("Euler Characteristic χ = V - E + F = " + V + " - " + E + " + " + F + " = " + (V - E + F));
    }
}
