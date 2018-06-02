import java.awt.*;
import java.util.List;
import java.util.Stack;

public interface ConvexHullSolver {

    public Stack<Point> solve(List<Point> points);
}
