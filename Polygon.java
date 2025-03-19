/**
 * The Polygon class represents a polygon in 2D space
 */
public class Polygon {
  
  /** Array of Points represents the verticles of the Polygon*/
  protected Point[] polyPoints;
  
  /** The center of the Polygon */
  protected Point center;
  
  /**
   * Creates a new Polygon with given array of points
   * 
   * @param polyPoints represents the verticles of the polygon. The array has to contain at least 3 points
   */
  public Polygon (Point[] polyPoints) {
    if (polyPoints.length >= 3)
      this.polyPoints = polyPoints;
    else
      throw new IllegalArgumentException();
  }
  
  /**
   * Returns the center of the Polygon.
   * The center will be defined as the center of the bounding rectangle of the Polygon
   * 
   * @return the center of the Polygon
   */
  public Point getCenter() {
    double minX = Double.MAX_VALUE; 
    double maxX = Double.MIN_VALUE;
    double minY = Double.MAX_VALUE;
    double maxY = Double.MIN_VALUE;
    
    //a loop goes through all Points of the Polygon to find the bounding rectangle Points
    for (int i = 0; i < polyPoints.length; i = i + 1) {
      if (polyPoints[i].getX() < minX)
        minX = polyPoints[i].getX();
      if (polyPoints[i].getX() > maxX)
        maxX = polyPoints[i].getX();
      if (polyPoints[i].getY() < minY)
        minY = polyPoints[i].getY();
      if (polyPoints[i].getY() > maxY)
        maxY = polyPoints[i].getY();
    }
    
    this.center = new Point ((minX + maxX)/2, (minY + maxY)/2);
    return center;
  }
  
  /**
   * Changes the center of the Polygon by moving each the whole Polygon
   * 
   * @param newCenter a Point to be new the center
   */
  public void setCenter(Point newCenter) {
    //a Point to remember the current center
    Point currentCenter = this.getCenter();
    
    //a loop goes through and changes all the Points in the Polygon in relative to the new center point
    for (int i = 0; i < polyPoints.length; i = i + 1) {
      polyPoints[i].setX (polyPoints[i].getX() + (newCenter.getX() - currentCenter.getX()));
      polyPoints[i].setY (polyPoints[i].getY() + (newCenter.getY() - currentCenter.getY()));
    }
    this.center = this.getCenter();
  }
  
  /**
   * Rotates the Polygon about its center by the input angle
   * 
   * @param angle the angle of the rotation
   */
  public void rotate (double angle) {
    Point center = this.getCenter();
    
    //a loop goes through and rotate all the Points in the Polygon by the given angle 
    for (int i = 0; i < polyPoints.length; i = i + 1) {
      polyPoints[i].rotateAbout(center,angle);
    }
    this.center = this.getCenter();
  }
  
  /**
   * Return the Points in the Polygon
   * 
   * @return the Points in the Polygon
   */
  public Point[] getPoints() {
    return polyPoints;
  }
  
  /**
   * Return the Lines in the Polygon
   * 
   * @return the Lines in the Polygon
   */
  public Line[] getLines() {
    //a array of lines represents all the lines that the polygon has
    Line[] polyLines = new Line[polyPoints.length];
    int i = 0;
    //a loop goes through all the points to initiate lines in the line array
    for (; i < polyPoints.length-1; i = i + 1) 
      polyLines[i] = new Line (polyPoints[i], polyPoints[i+1]);
    polyLines[i] = new Line(polyPoints[i],polyPoints[0]);
    return polyLines;
    }
  }
                                

    
    
            
      
      