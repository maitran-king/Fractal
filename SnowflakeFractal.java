import java.util.ArrayList;

/**
 * The SnowflakeFractal class generates a fractal pattern based on a regular polygon
 * The fractal is created by recursively subdividing each side of the base polygon into a "_/\_" pattern
 * 
 * @param <T> the type of Polygon used to generate the fractal. Must extend both Polygon and implement RegularPolygon
 */
public class SnowflakeFractal<T extends Polygon & RegularPolygon> extends Fractal<T> {
  
  /**
   * Constructs a new SnowflakeFractal with the given base shape and number of recursion levels
   * 
   * @param baseShape the base polygon (e.g., triangle, square, or regular polygon)
   * @param numLevels the number of recursive levels to generate the fractal
   */
  public SnowflakeFractal(T baseShape, int numLevels) {
    super(baseShape, numLevels);
  }
  
  /**
   * Generates and retrieves all points of the snowflake fractal
   * 
   * @return an array of Points representing the fractal
   */
  public Point[] getPoints() {
    ArrayList<Point> points = new ArrayList<>();
    pointsOfFractal.clear(); //clear previously stored points
    generateSnowflakeFractalPoints(baseShape.getPoints(), numLevels, points);
    pointsOfFractal.addAll(points);
    return points.toArray(new Point[0]);
  }
  
  /**
   * Helper method to recursively generates the points of the snowflake fractal for a given level
   * 
   * @param points the Points of the current level's polygon
   * @param numLevels the number of recursive levels remaining
   * @param result the list to store the resulting Points
   */
  private void generateSnowflakeFractalPoints (Point[] points, int numLevels, ArrayList<Point> result) {
    //base case: Add all Points of the current polygon to the result
    if (numLevels == 0) {
      for (Point point : points) {
        result.add(point);
      }
      return;
    }
    
    ArrayList<Point> generatedPoints = new ArrayList<>();
    for (int i = 0; i < points.length; i++) {
      Point start = points[i];
      Point end = points[(i+1) % points.length];
      
      //divide the line segment into three equal parts
      Point oneThird = divide(start, end, 1.0/3);
      Point twoThird = divide(start, end, 2.0/3);
      
      //calculate the peak of the "_/\_" segment
      Point peak = new Point(oneThird.getX(), oneThird.getY());
      peak.rotateAbout(twoThird, Math.PI/3);
      
      //add the subdivided points to the list
      generatedPoints.add(start);
      generatedPoints.add(oneThird);
      generatedPoints.add(peak);
      generatedPoints.add(twoThird);
    }
    //recursive call to process the next level of fractal Points
    generateSnowflakeFractalPoints(generatedPoints.toArray(new Point[0]), numLevels - 1, result);
  } 
  
  /**
   * Generates and retrieves all Lines of the snowflake fractal
   * 
   * @return an array of Lines representing the edges of the fractal
   */
  public Line[] getLines() {
    Point[] points = getPoints();
    Line[] lines = new Line[points.length];
    int i = 0;
    
    //a loop to create Lines connecting consecutive Points
    for (; i < points.length-1; i = i + 1) 
      lines[i] = new Line (points[i], points[i+1]);
    //connect the last Point to the first to close the polygon
    lines[i] = new Line(points[i],points[0]);
    return lines;
  }
  
  public void printPoints() {
    System.out.println("Fractal Points:");
    for (Point p : getPoints()) {
      System.out.printf("(%.6f, %.6f)\n", p.getX(), p.getY());
    }
  }
}