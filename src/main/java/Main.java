import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        ConvexHullSolver solver = new GrahamConvexHullSolver();
        List<Point> points = generateNPoints(10);
        Stack<Point> result = solver.solve(points);
        return;
    }

    private static List<Point> generateNPoints(int numberOfPoints) {
        List<Point> points = new ArrayList<>();

        for(int i = 0 ; i< numberOfPoints ; i++ ) {
            Point point = new Point(random.nextInt(200) - 100, random.nextInt(200) - 100);
            points.add(point);
        }
        return points;
    }
}
