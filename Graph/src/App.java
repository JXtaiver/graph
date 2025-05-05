import java.util.*;

public class App {
    private static List<Graph> graphs = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Create a Graph");
            System.out.println("2. View All Graphs");
            System.out.println("3. Perform Operations on a Graph");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    createGraph();
                    break;
                case "2":
                    viewAllGraphs();
                    break;
                case "3":
                    performOperationsMenu();
                    break;
                case "4":
                    System.out.println("Exiting program.");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void createGraph() {
        System.out.print("Enter vertices (e.g., v1 v2 v3): ");
        String[] vertices = scan.nextLine().trim().split("\\s+");
        int n = vertices.length;
    
        Graph g = new Graph(n);
        Map<String, Integer> vertexIndexMap = new HashMap<>();
    
        for (int i = 0; i < n; i++) {
            g.addVertexData(i, vertices[i]);
            vertexIndexMap.put(vertices[i], i);
        }
    
        int edgeCount = 0;
        while (true) {
            System.out.print("Enter number of edges: ");
            String input = scan.nextLine().trim();
            try {
                edgeCount = Integer.parseInt(input);
                if (edgeCount < 0) {
                    System.out.println("Edge count must be non-negative.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer.");
            }
        }
    
        for (int i = 0; i < edgeCount; i++) {
            while (true) {
                System.out.print("Enter edge " + (i + 1) + " (e.g., v1 v2): ");
                String[] edge = scan.nextLine().trim().split("\\s+");
    
                if (edge.length != 2 || 
                    !vertexIndexMap.containsKey(edge[0]) || 
                    !vertexIndexMap.containsKey(edge[1])) {
                    System.out.println("Invalid edge. Make sure both vertices exist. Try again.");
                    continue;
                }
    
                int u = vertexIndexMap.get(edge[0]);
                int v = vertexIndexMap.get(edge[1]);
                g.addEdge(u, v);
                break;
            }
        }
    
        graphs.add(g);
        System.out.println("Graph created and saved as graph #" + (graphs.size() - 1));
    }
    
    

    private static void viewAllGraphs() {
        if (graphs.isEmpty()) {
            System.out.println("No graphs have been created yet.");
            return;
        }

        for (int i = 0; i < graphs.size(); i++) {
            System.out.println("\nGraph #" + i + ":");
            graphs.get(i).printGraph();
        }
    }

    private static void performOperationsMenu() {
        if (graphs.isEmpty()) {
            System.out.println("No graphs available to perform operations on.");
            return;
        }

        System.out.print("Enter graph number to operate on (0 to " + (graphs.size() - 1) + "): ");
        int index = Integer.parseInt(scan.nextLine());

        if (index < 0 || index >= graphs.size()) {
            System.out.println("Invalid graph index.");
            return;
        }

        Graph g = graphs.get(index);

        while (true) {
            System.out.println("\n--- Graph Operations Menu (Graph #" + index + ") ---");
            System.out.println("1. Print Graph");
            System.out.println("2. DFS Traversal");
            System.out.println("3. Degree Sequence");
            System.out.println("4. Euler Characteristic");
            System.out.println("5. Power Set with n-simplex");
            System.out.println("6. Incidence Check");
            System.out.println("7. Isomorphism with Another Graph");
            System.out.println("8. Back to Main Menu");
            System.out.print("Choose an operation: ");
            String op = scan.nextLine();

            switch (op) {
                case "1":
                    g.printGraph();
                    break;
                case "2":
                    System.out.print("Enter start vertex for DFS: ");
                    String start = scan.nextLine();
                    g.dfs(start);
                    break;
                case "3":
                    System.out.println("Degree Sequence: " + Arrays.toString(g.degree()));
                    break;
                case "4":
                    g.euler();
                    break;
                case "5":
                    Set<String> vertexSet = new HashSet<>(Arrays.asList(g.getVertexData()));
                    List<Set<String>> power = g.powerSet(vertexSet);
                    for (Set<String> s : power) {
                        System.out.println(s + " - n-simplex: " + (s.size() - 1));
                    }
                    break;
                case "6":
                    System.out.print("Enter Set A (e.g., v1 v2): ");
                    Set<String> setA = new HashSet<>(Arrays.asList(scan.nextLine().split(" ")));
                    System.out.print("Enter Set B (e.g., v1 v2 v3): ");
                    Set<String> setB = new HashSet<>(Arrays.asList(scan.nextLine().split(" ")));
                    System.out.println("Set A ⊆ Set B? " + g.incidence(setA, setB));
                    break;
                case "7":
                    System.out.print("Enter graph number to compare with (0 to " + (graphs.size() - 1) + "): ");
                    int other = Integer.parseInt(scan.nextLine());
                    if (other == index || other < 0 || other >= graphs.size()) {
                        System.out.println("Invalid comparison graph.");
                        break;
                    }
                    boolean isomorphic = g.isIsomorphic(graphs.get(other));
                    System.out.println("Isomorphic? " + isomorphic);
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
