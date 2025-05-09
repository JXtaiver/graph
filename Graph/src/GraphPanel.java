import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class GraphPanel extends JPanel {
    private Graph graph;

    public GraphPanel(Graph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int size = graph.getSize();
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 3;

        // Calculate positions of vertices in a circular layout
        Point[] vertexPositions = new Point[size];
        double angleStep = 2 * Math.PI / size;
        for (int i = 0; i < size; i++) {
            int x = (int) (centerX + radius * Math.cos(i * angleStep));
            int y = (int) (centerY + radius * Math.sin(i * angleStep));
            vertexPositions[i] = new Point(x, y);
        }

        // Draw edges
        g.setColor(Color.BLACK);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (graph.isEdge(i, j)) {
                    Point p1 = vertexPositions[i];
                    Point p2 = vertexPositions[j];
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);

                    // Draw weight if the graph is weighted
                    if (graph.isWeighted()) {
                        int weight = graph.getEdgeWeight(i, j);
                        int midX = (p1.x + p2.x) / 2;
                        int midY = (p1.y + p2.y) / 2;
                        g.drawString(String.valueOf(weight), midX, midY);
                    }
                }
            }
        }

        // Draw vertices
        g.setColor(Color.BLUE);
        for (int i = 0; i < size; i++) {
            Point p = vertexPositions[i];
            g.fillOval(p.x - 15, p.y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(graph.getVertexData(i), p.x - 5, p.y + 5);
            g.setColor(Color.BLUE);
        }
    }
}