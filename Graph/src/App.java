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
        System.out.print("Do you want to create a weighted graph? (yes/no): ");
        boolean isWeighted = scan.nextLine().trim().equalsIgnoreCase("yes");
    
        System.out.print("Enter vertices (e.g., A B C): ");
        String[] vertices = scan.nextLine().trim().split("\\s+");
        int n = vertices.length;
    
        Graph g = new Graph(n, isWeighted);
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
    
        //  This is the part where you insert the updated edge input logic
        for (int i = 0; i < edgeCount; i++) {
            while (true) {
                if (isWeighted) {
                    System.out.print("Enter edge " + (i + 1) + " in the format: vertex1 vertex2 weight (e.g., A B 5): ");
                } else {
                    System.out.print("Enter edge " + (i + 1) + " in the format: vertex1 vertex2 (e.g., A B): ");
                }
    
                String[] edge = scan.nextLine().trim().split("\\s+");
    
                if ((isWeighted && edge.length != 3) || (!isWeighted && edge.length != 2)) {
                    System.out.println("Invalid format. " + (isWeighted ? "Expected: vertex1 vertex2 weight" : "Expected: vertex1 vertex2"));
                    continue;
                }
    
                String uStr = edge[0];
                String vStr = edge[1];
    
                if (!vertexIndexMap.containsKey(uStr) || !vertexIndexMap.containsKey(vStr)) {
                    System.out.println("One or both vertices not found. Please make sure both vertices exist.");
                    continue;
                }
    
                int u = vertexIndexMap.get(uStr);
                int v = vertexIndexMap.get(vStr);
                int weight = 1;
    
                if (isWeighted) {
                    try {
                        weight = Integer.parseInt(edge[2]);
                        if (weight <= 0) {
                            System.out.println("Weight must be a positive integer.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Weight must be a valid integer.");
                        continue;
                    }
                }
    
                g.addEdge(u, v, weight);
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
            System.out.println("6. Back to Main Menu");
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
                    System.out.println("No operations for Power Set in this version.");
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
