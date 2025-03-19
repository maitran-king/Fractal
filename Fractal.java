import java.util.ArrayList;

/**
 * The abstract Fractal class represents the framework for generating fractals 
 * Subclasses must implement methods for generating fractal points and lines
 *
 * @param <T> the type of Polygon used as the base shape for the fractal
 */
public abstract class Fractal <T extends Polygon> {
  
  /** The base shape for fractal */
  protected T baseShape;
  
  /** The number of levels of recursion for the fractal */
  protected int numLevels;
  
  /** A list to store points of the fractal */
  protected ArrayList<Point> pointsOfFractal = new ArrayList<Point>();
  
  /**
   * Constructs a new Fractal with the specified base shape and recursion levels
   * 
   * @param baseShape the base Polygon used to generate the fractal
   * @param numLevels the number of recursive levels for the fractal. Must be non-negative
   * @throws IllegalArgumentException if numLevels is negative
   */
  public Fractal(T baseShape, int numLevels) {
    this.baseShape = baseShape;
    if (numLevels >= 0) 
      this.numLevels = numLevels;
    else 
      throw new IllegalArgumentException("Number of levels must be non-negative.");
  }
  
  /**
   * Retrieves the base shape of the fractal
   * 
   * @return the base Polygon shape
   */
  public T getBaseShape() {
    return baseShape;
  }
  
  /**
   * Retrieves the number of levels of recursion for the fractal
   * 
   * @return the number of recursive levels
   */
  public int getNumLevels() {
    return numLevels;
  }
  
  /**
   * Retrieves the center Point of the fractal
   * 
   * @return the center Point of the fractal
   */
  public Point getCenter() {
    return getBaseShape().getCenter();
  }
  
  /**
   * Sets a new center Point for the base shape and adjusts its points accordingly
   * 
   * @param newCenter the new center Point for the fractal
   */
  public void setCenter(Point newCenter) {
    Point currentCenter = getCenter();
    double dx = newCenter.getX() - currentCenter.getX();
    double dy = newCenter.getY() - currentCenter.getY();
    
    //a loop to adjust all Points of the base shape relative to the new center
    for (Point point : baseShape.getPoints()) {
      point.setX(point.getX() + dx);
      point.setY(point.getY() + dy);
    }
  }
  
  /**
   * Rotates the fractal about its center by a specified angle
   * 
   * @param angle the angle of rotation in radians
   */
  public void rotate(double angle) {
    Point center = baseShape.getCenter();
    
    // generate points if they haven't been initialized yet
    if (pointsOfFractal.isEmpty())
      getPoints(); 
    
    // a loop to rotate each Point of the fractal about the center
    for (Point point : pointsOfFractal) {
      point.rotateAbout(center, angle);
    }
  }
  
  /**
   * Abstract method to retrieve all points of the fractal
   * 
   * @return an array of Points representing the fractal's geometry
   */
  public abstract Point[] getPoints();
  
  /**
   * Abstract method to retrieve all lines of the fractal
   * 
   * @return an array of Lines that make up the fractal
   */
  public abstract Line[] getLines();
  
  /**
   * Helper method to find a point at a given ratio along a line segment
   * 
   * @param p1 the starting Point of the line segment
   * @param p2 the ending Point of the line segment
   * @param ratio the ratio along the line segment where the point is located
   * @return the Point at the specified ratio along the line segment
   */
  protected Point divide (Point p1, Point p2, double ratio) {
    return new Point (p1.getX() + (p2.getX() - p1.getX()) * ratio,
                      p1.getY() + (p2.getY() - p1.getY()) * ratio
                     );
  }
  
  /**
   * Adds a Line to a list if it does not already exist
   * 
   * @param line the Line to potentially add.
   * @param lines the list of Lines to add to if the line is unique.
   */
  protected void addLineIfNotExists(Line line, ArrayList<Line> lines) {
    //check if the line or its reverse already exists
    for (Line existingLine : lines) {
      if (existingLine.equals(line)) {
        return;
      }
    }
    lines.add(line);
  }
}