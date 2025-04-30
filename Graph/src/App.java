import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter verticies (ex. v1 v2 v3)");
        String[] verticies = scan.nextLine().split(" ");
        int n = verticies.length;

        Graph g = new Graph(n);
            Map<String, Integer> vertexIndexMap = new HashMap<>();
            for (int i = 0; i < n; i++){
                g.addVertexData(i, verticies[i]);
                vertexIndexMap.put(verticies[i], i );
            }

            System.out.println("Enter number of edges:");
            int edgeCount = Integer.parseInt(scan.nextLine());
            
            for (int i = 0; i<edgeCount; i++) {
                System.out.println("Enter edge "+(i+1));
                String[] edge = scan.nextLine().split(" ");
                int u = vertexIndexMap.get(edge[0]);
                int v = vertexIndexMap.get(edge[1]);
                g.addEdge(u,v);

            }

            g.printGraph();

            System.out.println("Enter start vertex for DFS");
            String start = scan.nextLine();
            g.dfs(start);
            scan.close();
    }
}
