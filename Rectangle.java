/**
 * The Rectangle class represents a rectangle in 2D space with a width and a height
 */
public class Rectangle extends Polygon {
  
  /** The width of the rectangle */
  private double width;
  
  /** The height of the rectangle */
  private double height;
  
  /**
   * Creates a rectangle with a given center Point, a width and a height
   * 
   * @param center the center of the rectangle
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   */
  public Rectangle (Point center, double width, double height) {
    super (new Point[4]);
    if (width >= 0)
      this.width = width;
    else
      throw new IllegalArgumentException();
    if (height >= 0)
    this.height = height;
    else
      throw new IllegalArgumentException();
    this.center = center;
    
    polyPoints[0] = new Point (center.getX() - width/2, center.getY() - height/2);
    polyPoints[1] = new Point (center.getX() + width/2, center.getY() - height/2);
    polyPoints[2] = new Point (center.getX() + width/2, center.getY() + height/2);
    polyPoints[3] = new Point (center.getX() - width/2, center.getY() + height/2);
  }
  
  /**
   * Returns the width of the rectangle
   * 
   * @return the width of the rectangle
   */
  public double getWidth() {
    return this.width;
  }
  
  /**
   * Changes the width of the rectangle
   * 
   * @param width the new width of the rectangle
   */
  public void setWidth (double width) {
    this.width = width;
    this.updatePoints();
  }
  
  /**
   * Returns the height of the rectangle
   * 
   * @return the height of the rectangle
   */
  public double getHeight() {
    return this.height;
  }
  
  /**
   * Changes the height of the rectangle
   * 
   * @param height the new height of the rectangle
   */
  public void setHeight (double height) {
    this.height = height;
    this.updatePoints();
  }
  
  /**
   * Helper method to generate Points based on the center, width , and height
   */
  private void updatePoints() {
    Point center = this.getCenter();
    double width = this.getWidth();
    double height = this.getHeight();
    
    polyPoints[0] = new Point (center.getX() - width/2, center.getY() - height/2);
    polyPoints[1] = new Point (center.getX() + width/2, center.getY() - height/2);
    polyPoints[2] = new Point (center.getX() - width/2, center.getY() + height/2);
    polyPoints[3] = new Point (center.getX() + width/2, center.getY() + height/2);
  }
}