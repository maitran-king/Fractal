/**
 * The Line class represents a line in 2D space with 2 points
 */
public class Line {
  /** The first Point of the Line */
  private Point firstPoint;
  
  /** The second Point of the Line */
  private Point secondPoint;
  
  /**
   * Creates a Line with 2 given points
   * 
   * @param p1 the first Point of a Line
   * @param p2 the second Point of a Line
   */
  public Line (Point p1, Point p2) {
    this.firstPoint = p1;
    this.secondPoint = p2;
  }
  
  /**
   * Creates a line with 4 given points
   * 
   * @param x1 the x coordinate of the first Point
   * @param y1 the y coordinate of the first Point
   * @param x2 the x coordinate of the second Point
   * @param y2 the y coordinate of the second Point
   */
  public Line (double x1, double y1, double x2, double y2) {
    this.firstPoint = new Point (x1,y1);
    this.secondPoint = new Point (x2,y2);
  }
  
  /**
   * Returns the first Point of a Line
   * 
   * @return the first Point of a Line
   */
  public Point getFirstPoint() {
    return this.firstPoint;
  }
  
  /**
   * Changes the first Point of a Line
   * 
   * @param firstPoint the new first Point of a Line
   */
  public void setFirstPoint (Point firstPoint) {
    this.firstPoint = firstPoint;
  }
  
  /**
   * Returns the second Point of a Line
   * 
   * @return the second Point of a Line
   */
  public Point getSecondPoint() {
    return this.secondPoint;
  }
  
  /**
   * Changes the second Point of a Line
   * 
   * @param secondPoint the new second Point of a Line
   */
  public void setSecondPoint (Point secondPoint) {
    this.secondPoint = secondPoint;
  }
  
  /**
   * Returns this Line
   * 
   * @return this Line
   */
  public Line[] getLines () {
    return new Line[] {this};
  }
  
  /**
   * Changes the behavior of toString() to return the format ((x1,y2), (x2,y2))
   * 
   * @return the String result of toString()
   */
  public String toString() {
    return "(" + firstPoint + ", " + secondPoint + ")";
  }
  
  /**
   * Changes the behavior of the equals method
   * Two Lines are equal if they have the same first Point and second Point
   * 
   * @return true if two Lines are equal; false if not
   */
  public boolean equals(Object o) {
    if (o instanceof Line) {
      Line line = (Line) o;
      return (this.getFirstPoint().equals(line.getFirstPoint()) && this.getSecondPoint().equals(line.getSecondPoint()) ||
              this.getFirstPoint().equals(line.getSecondPoint()) && this.getSecondPoint().equals(line.getFirstPoint()));
    }
    return false;
  }
}