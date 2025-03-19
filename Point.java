
/**
 * The point class represent a point in 2D space with x and y coordinates
 */
public class Point {
  /** The x coordinate of a Point */
  private double x;
  
  /** The x coordinate of a Point */
  private double y;
  
  /**
   * Creates a Point with given x and y coordinates
   * 
   * @param x the x coordinate of a Point
   * @param y the y coordinate of a Point
   */
  public Point (double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Returns the x coordinate of a Point
   * 
   * @return the x coordinate of a Point
   */
  public double getX() {
    return x;
  }
  
  /**
   * Changes the x coordinate of a Point
   * 
   * @param x the x coordinate of a Point
   */
  public void setX (double x) {
    this.x = x;
  }
  
   /**
   * Returns the y coordinate of a Point
   * 
   * @return the y coordinate of a Point
   */
  public double getY() {
    return y;
  }
  
  /**
   * Changes the y coordinate of a Point
   * 
   * @param y the y coordinate of a Point
   */
  public void setY (double y) {
    this.y = y;
  }
  
  /**
   * Rotates this Point around another specified Point by a given angle in radians
   * The rotation treats the specified Point as the origin for the rotation
   * 
   * @param center the Point which this Point is rotate around
   * @param angle the angle of rotation, in radians
   */
  public void rotateAbout (Point center, double angle) {
    double translateX = this.getX() - center.getX(); //the x coordinate of this point after move it relatively to the center point 
    double translateY = this.getY() - center.getY(); //the y coordinate of this point after move it relatively to the center point
    double rotateX = translateX * Math.cos(angle) - translateY * Math.sin(angle); //the x coordinate of this point after the rotation
    double rotateY = translateX * Math.sin(angle) + translateY * Math.cos(angle); //the y coordinate of this point after the rotation 
    this.setX (rotateX + center.getX());
    this.setY (rotateY + center.getY());
  }
  
  /**
   * Changes the behavior of toString() to return in the format (x,y)
   * 
   * @return String respresentation of the Point
   */
  public String toString() {
    return "(" + this.getX() + ", " + this.getY() + ")";
  }
  
  /**
   * Changes the behavior of equals
   * Two Points are equal if their X and Y coordinate are the same within the tolerance range
   * 
   * @return true if two Points are equal; false if not
   */
  public boolean equals(Object o) {
    double tolerance = 0.000001;
    if (o instanceof Point) {
      Point point = (Point) o;
      return ((Math.abs(this.x - point.x)) < tolerance && (Math.abs(this.y - point.y) < tolerance));
    }
    return false;
  }
}
                            