/**
 * The EquilateralTriangle class represents the equilateral triangle in 2D space
 */
public class EquilateralTriangle extends Triangle implements RegularPolygon {
  /** The length of the side */
  private double sideLength;
  
  /**
   * Creates a equilateral triangle with a given center point and side length
   * 
   * @param center the center of the triangle
   * @param sideLength the side length of the triangle
   */
  public EquilateralTriangle (Point center, double sideLength) {
    super (null, null, null);
    this.sideLength = sideLength;
    this.center = center;
    
    double centralAngle = 2*Math.PI/3;                   
    double radius = sideLength/(2*Math.sin(Math.PI/3));
    
    polyPoints = new Point[3];
    
    polyPoints[0] = new Point (center.getX(), center.getY() + radius);
    
    //a loop that calculate the points of the polygon by rotate the last point
    for (int i = 1; i < 3; i = i + 1) {
      Point rotatedPoint = new Point(polyPoints[i - 1].getX(), polyPoints[i - 1].getY());
      rotatedPoint.rotateAbout(center, centralAngle);
      polyPoints[i] = rotatedPoint;
    }
  }
  
  /**
   * Returns the side length of the equilateral triangle
   * 
   * @return the side length of the equilateral triangle
   */
  public double getSideLength() {
    return sideLength;
  }
  
  /**
   * Returns the number of sides of the equilateral triangle
   * 
   * @return number of sides of the equilateral triangle
   */
  public int getNumSides() {
    return 3;
  }
  
  /**
   * Changes the side length of the equilateral triangle
   * 
   * @param sideLength side length of the equilateral triangle
   */
  public void setSideLength(double sideLength) {
    this.sideLength = sideLength;
    double centralAngle = 2*Math.PI/3;                   
    double radius = sideLength/(2*Math.sin(Math.PI/3));
    
    polyPoints[0] = new Point (center.getX(), center.getY() + radius);
    
    //a loop to regenerate a new equilateral triangle based on the old center and new side length
    for (int i = 1; i < 3; i = i + 1) {
      Point rotatedPoint = new Point(polyPoints[i - 1].getX(), polyPoints[i - 1].getY());
      rotatedPoint.rotateAbout(center, centralAngle);
      polyPoints[i] = rotatedPoint;
    }
  }
}