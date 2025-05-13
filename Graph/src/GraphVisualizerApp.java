import javax.swing.*;

public class GraphVisualizerApp {
    
    public static void visualizeGraph(Graph graph) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph Visualizer");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 800);
            frame.add(new GraphPanel(graph));
            frame.setVisible(true);
        });

    }

    public static void main(String[] args) {
        // Example usage with a sample graph
        Graph graph = new Graph(5, true); // A weighted graph with 5 vertices
        graph.addVertexData(0, "A");
        graph.addVertexData(1, "B");
        graph.addVertexData(2, "C");
        graph.addVertexData(3, "D");
        graph.addVertexData(4, "E");

        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 4);
        graph.addEdge(4, 0, 1);

        visualizeGraph(graph); // Visualize the sample graph
        
    }
}
