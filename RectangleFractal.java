import java.util.ArrayList;

/**
 * The RectangleFractal class represents a recursive fractal generated from a base Rectangle
 * It recursively subdivides the base rectangle into smaller rectangles, forming a fractal pattern
 */
public class RectangleFractal extends Fractal<Rectangle> {
  
  /** List to store the current generated Rectangles in the fractal */
  private ArrayList<Rectangle> currentRectangles = new ArrayList<>();
  
  /**
   * Constructs a RectangleFractal with the specified base Rectangle or Square and number of levels
   *
   * @param rectangle the base Rectangle or Square used to generate the fractal.
   * @param numLevels the number of recursive levels for the fractal.
   */
  public RectangleFractal(Rectangle rectangle, int numLevels) {
    super(rectangle, numLevels);
  }
  
  /**
   * Retrieves all the Points that make up the fractal
   * 
   * @return an array of Points representing the fractal's geometry
   */
  public Point[] getPoints() {
    ArrayList<Point> points = new ArrayList<>();
    currentRectangles.clear(); // clear any previously generated Rectangles
    generateRectangleFractalPoints(baseShape, numLevels, points); 
    return points.toArray(new Point[0]);
  }
  
  /**
   * Helper method to recursively generate the Points of the rectangle fractal.
   * 
   * @param rectangle the current Rectangle to subdivide
   * @param numLevels the remaining levels of recursion
   * @param result the list to store the generated Points
   */
  private void generateRectangleFractalPoints (Rectangle rectangle, int numLevels, ArrayList<Point> result) {
    Point[] points = rectangle.getPoints();
    
    //base case: If no levels remain, add the Points of the rectangle
    if (numLevels == 0) {
      for (Point point : points) {
        if (!result.contains(point))
          result.add(point);
      }
      currentRectangles.add(rectangle);
      return;
    }
    
    double subWidth = rectangle.getWidth()/3.0;
    double subHeight = rectangle.getHeight()/3.0;
    
    //determine the center of the top left corner of the rectangle
    Point centerTopLeft = divide(points[3], points[1], 1.0/6);
    
    //a loop to recursively create smaller Rectangles, skipping the center one
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) { 
        if (i == 1 && j == 1)
          ;
        else {
          //calculate the center of the smaller Rectangle
          Point subCenter = new Point(centerTopLeft.getX() + i * subWidth, centerTopLeft.getY() - j * subHeight);
          //create a new smaller Rectangle
          Rectangle smallerRectangle = new Rectangle (subCenter, subWidth, subHeight);
          generateRectangleFractalPoints(smallerRectangle, numLevels - 1, result);
        }
      }
    }
  }
  
  /**
   * Retrieves all the Lines that make up the fractal
   * 
   * @return an array of Lines that make up the fractal
   */
  public Line[] getLines() {
    ArrayList<Line> generatedLines = new ArrayList<>();
    
    if (currentRectangles.isEmpty()) {
      getPoints();
    }
    
    //a loop to add Lines from each rectangle in the fractal
    for (Rectangle rectangle : currentRectangles) {
      Line[] lines = rectangle.getLines();
      for (int i = 0; i < lines.length; i++) {
        addLineIfNotExists(lines[i], generatedLines);
      }
    }
    return generatedLines.toArray(new Line[0]);
  }
  
  /**
   * Rotates the fractal about the center of the base shape by a specified angle
   * 
   * @param angle the angle of rotation in radians
   */
  public void rotate(double angle) {
    Point center = baseShape.getCenter();
    
    if (currentRectangles.isEmpty()) {
      getPoints();
    }
    
    // a loop to rotate each Point of every rectangle in the fractal
    for (Rectangle rectangle : currentRectangles) {
      for (Point point : rectangle.getPoints()) {
        point.rotateAbout(center, angle);
      }
    }
  }
}