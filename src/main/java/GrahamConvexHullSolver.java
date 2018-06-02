import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class GrahamConvexHullSolver implements ConvexHullSolver {

    @Override
    public Stack<Point> solve(List<Point> points) {
        points.sort(Comparator.<Point>comparingInt(point -> point.y).<Point>thenComparingInt(point -> point.x));
        points = sortByAngleToXAxis(points);
        Stack<Point> result = new Stack<>();

        result.push(points.get(0));
        result.push(points.get(1));
        result.push(points.get(2));

        for (int i = 3; i < points.size(); i++) {
            while (isTurningRight(result, points.get(i))) {
                result.pop();
            }
            result.push(points.get(i));
        }
        return result;
    }

    private List<Point> sortByAngleToXAxis(List<Point> points) {
        List<Point> sorted = points.subList(1, points.size());
        sorted.sort(Comparator.comparingDouble(this::calculateAlpha).thenComparingInt(point -> point.x));
        List<Point> result = new ArrayList<>();
        result.add(points.get(0));
        result.addAll(sorted);
        return result;
    }

    private double calculateAlpha(Point point) {

        double d = Math.abs(point.x) + Math.abs(point.y);
        if (point.x >= 0 && point.y >= 0) {
            return point.y / d;
        }

        if (point.x < 0 && point.y >= 0) {
            return 2 - point.y / d;
        }

        if (point.x < 0) {
            return 2 + Math.abs(point.y) / d;
        }

        return 4 - Math.abs(point.y) / d;
    }

    private double calculateDeterminant(Point first, Point second, Point third) {
        return first.x * second.y + second.x * third.y + third.x * first.y - third.x * second.y - first.x * third.y - second.x * first.y;
    }

    private boolean isTurningRight(Stack<Point> currentSolution, Point nextPoint) {
        Point p2 = currentSolution.pop();
        Point p1 = currentSolution.pop();
        currentSolution.push(p1);
        currentSolution.push(p2);
        double determinant = calculateDeterminant(p1, p2, nextPoint);
        return determinant <= 0;
    }
}