/**
 * The Square class represents the square in 2D space
 */
public class Square extends Rectangle implements RegularPolygon {
  
  /**
   * Creates a square with a given ceter and side length
   * 
   * @param center the center of the square
   * @param side the side length of the square
   */
  public Square (Point center, double side) {
    super (center, side, side); 
  }
  
  /**
   * Changes the width and the height of the square
   * 
   * @param width the side length of the square
   */
  public void setWidth(double width) {
      super.setWidth(width);
      super.setHeight(width);
  }
  
  /**
   * Changes the width and the height of the square
   * 
   * @param height the side length of the square
   */
  public void setHeight(double height) {
    this.setWidth(height);
  }
  
  /**
   * Retrives the side length of the square
   * 
   * @return the side length of the square
   */
  public double getSideLength() {
    return getWidth();
  }
  
  /**
   * Retrives the number of sides of the square
   * 
   * @return the number of sides of the square
   */
  public int getNumSides() {
    return 4;
  }
}
   
                                       