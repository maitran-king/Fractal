/**
 * The Triangle class represents a triangle in 2D space
 */
public class Triangle extends Polygon {
  
  /**
   * Creates a new triangle with 3 points
   * 
   * @param p1 the first Point of the triangle
   * @param p2 the second Point of the triangle
   * @param p3 the third Point of the triangle
   */
  public Triangle (Point p1, Point p2, Point p3) {
    super (new Point[] {p1,p2,p3});
  }
  
  /**
   * Returns the center of the triangle
   * @return the center of the triangle
   */
  public Point getCenter() {
    Point p1 = polyPoints[0]; //the first Point of the triangle
    Point p2 = polyPoints[1]; //the second Point of the triangle
    Point p3 = polyPoints[2]; //the third Point of the triangle
    
    double x1 = p1.getX();                  //the x coordinate of the first Point 
    double y1 = p1.getY();                  //the y coordinate of the first Point
    double x2 = (p2.getX() + p3.getX())/2;  //the x coordinate of the middle Point between p2 and p3
    double y2 = (p2.getY() + p3.getY())/2;  //the y coordinate of the middle Point between p2 and p3
    double x3 = p3.getX();                  //the x coordinate of the third Point
    double y3 = p3.getY();                  //the y coordinate of the third Point
    double x4 = (p1.getX() + p2.getX())/2;  //the x coordinate of the middle Point between p1 and p2 
    double y4 = (p1.getY() + p2.getY())/2;  //the x coordinate of the middle Point between p1 and p2
      
    double x = ((x1*y2 - y1*x2)*(x3-x4) - (x1-x2)*(x3*y4-y3*x4)) / ((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
    double y = ((x1*y2 - y1*x2)*(y3-y4) - (y1-y2)*(x3*y4-y3*x4)) / ((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
    
    return new Point (x,y);
  }
}
                                     