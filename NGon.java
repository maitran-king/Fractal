/**
 * The NGon class represents a regular polygon in 2D space
 */
public class NGon extends Polygon implements RegularPolygon {

  /** The number of sides of the regular polygon*/
  private int numSides;
  
  /** The side length of the regular polygon*/
  private double sideLength;
  
  /**
   * Creates a regular polygon with a given center, side length, and number of sides
   * 
   * @param center the center of the polygon
   * @param sideLength the side length of the polygon
   * @param numSides the number of sides of the polygon
   */
  public NGon (Point center, double sideLength, int numSides) {
    super (new Point[numSides]);
    this.center = center;
    if (sideLength >= 0)
      this.sideLength = sideLength;
    else
      throw new IllegalArgumentException();
    if (numSides >= 3)
      this.numSides = numSides;
    else
      throw new IllegalArgumentException();
    
    double centralAngle = 2*Math.PI/numSides;                   //the central angle of the regular polygon
    double radius = sideLength/(2*Math.sin(Math.PI/numSides));  //the length between the center and the polygon's points
    
    polyPoints[0] = new Point (center.getX(), center.getY() + radius);
    
    //a loop that calculate the points of the polygon by rotate the last Point
    for (int i = 1; i < numSides; i = i + 1) {
      Point rotatedPoint = new Point(polyPoints[i - 1].getX(), polyPoints[i - 1].getY());
      rotatedPoint.rotateAbout(center, centralAngle);
      polyPoints[i] = rotatedPoint;
    }
  }
  
  /**
   * Returns the number of sides of the polygon
   * 
   * @return the number of sides of the polygon
   */
  public int getNumSides() {
    return numSides;
  }
  
  /**
   * Returns the side length of the polygon
   * 
   * @return the side length of the polygon
   */
  public double getSideLength() {
    return sideLength;
  }
  
  /**
   * Changes the polygon according to the new side length
   * 
   * @param sideLength the new side length of the polygon
   */
  public void setSideLength(double sideLength) {
    this.sideLength = sideLength;
    
    double centralAngle = 2*Math.PI/this.getNumSides();
    double radius = this.getSideLength()/(2*Math.sin(Math.PI/this.getNumSides()));
    
    polyPoints[0] = new Point (center.getX(), center.getY() + radius);
    
    //a loop to regenerate the NGon with the old center and the new side length
    for (int i = 1; i < numSides; i = i + 1) {
      Point rotatedPoint = new Point(polyPoints[i - 1].getX(), polyPoints[i - 1].getY());
      rotatedPoint.rotateAbout(center, centralAngle);
      polyPoints[i] = rotatedPoint;
    }
  }
}
    
      
      
      
      