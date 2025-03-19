import java.util.ArrayList;

/**
 * The TriangleFractal class generates a fractal pattern based on a triangle
 * The fractal is created by recursively subdividing the triangle into three smaller triangles
 * Each smaller triangle is formed by connecting the triangle's center with the three verticle of the outer triangle
 */
public class TriangleFractal extends Fractal<Triangle> {

  /**
   * Constructs a new TriangleFractal with the given base triangle and number of recursion levels
   *
   * @param triangle the base triangle to generate the fractal
   * @param numLevels the number of recursive levels for the fractal
   */
  public TriangleFractal (Triangle triangle, int numLevels) {
    super (triangle, numLevels);
  }
  
  /**
   * Generates and retrieves all Points of the triangle fractal
   *
   * @return an array of Points representing the fractal's vertices
   */
  public Point[] getPoints() {
    ArrayList<Point> points = new ArrayList<>();
    pointsOfFractal.clear(); //clear previously stored points
    generateTriangleFractalPoints(baseShape.getPoints(), numLevels, points);
    pointsOfFractal.addAll(points);
    return points.toArray(new Point[0]);
  }
  
  /**
   * Recursively generates the points of the triangle fractal for a given level
   *
   * @param points the Points of the current level's triangle
   * @param numLevels the number of recursive levels remaining
   * @param result the list to store the resulting points
   */
  private void generateTriangleFractalPoints (Point[] points, int numLevels, ArrayList<Point> result) {
    if (numLevels == 0) {
      //base case: Add all Points of the current triangle to the result
      for (Point point : points) {
        if (!result.contains(point))
          result.add(point);
      }
      return;
    }
    
    //vertices of the current triangle
    Point p1 = points[0];
    Point p2 = points[1];
    Point p3 = points[2];
    
    //calculate the center of the triangle
    Triangle triangle = new Triangle(p1,p2,p3);
    Point center = triangle.getCenter();
    
    //recursively generate Points for the three smaller triangles
    generateTriangleFractalPoints(new Point[]{center, p1, p2}, numLevels - 1, result);
    generateTriangleFractalPoints(new Point[]{center, p2, p3}, numLevels - 1, result);
    generateTriangleFractalPoints(new Point[]{center, p3, p1}, numLevels - 1, result);
  }
  
  /**
   * Generates and retrieves all Lines of the triangle fractal.
   *
   * @return an array of Lines representing all Lines of the smaller triangles
   */
  public Line[] getLines() {
    ArrayList<Line> lines = new ArrayList<>();
    generateTriangleFractalLines(baseShape.getPoints(), numLevels, lines);
    return lines.toArray(new Line[0]);
  }
  
  /**
   * Recursively generates the lines of the triangle fractal for a given level
   *
   * @param points the points of the current level's triangle
   * @param numLevels the number of recursive levels remaining
   * @param result the list to store the resulting lines
   */
  private void generateTriangleFractalLines (Point[] points, int numLevels, ArrayList<Line> result) {
    if (numLevels == 0) {
      //base case: Add all Lines of the smaller triangles
      addLineIfNotExists((new Line(points[0], points[1])), result);
      addLineIfNotExists((new Line(points[1], points[2])), result);
      addLineIfNotExists((new Line(points[2], points[0])), result); 
      return;
    }
    
    Triangle triangle = new Triangle(points[0], points[1], points[2]);
    
    //vertices of the current triangle
    Point p1 = points[0];
    Point p2 = points[1];
    Point p3 = points[2];
    
    //calculate the center of the triangle
    Point center = triangle.getCenter();
    
    //recursively generate Lines for the three smaller triangles
    generateTriangleFractalLines(new Point[]{center, p1, p2}, numLevels - 1, result);
    generateTriangleFractalLines(new Point[]{center, p2, p3}, numLevels - 1, result);
    generateTriangleFractalLines(new Point[]{center, p3, p1}, numLevels - 1, result);
  }
  
  public void printPoints() {
    System.out.println("Fractal Points:");
    for (Point p : getPoints()) {
      System.out.printf("(%.6f, %.6f)\n", p.getX(), p.getY());
    }
  }
}