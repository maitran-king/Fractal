/**
 * The RegularPolygon interface represents a regular polygon,
 * which is a polygon with all sides of equal length and all angles of equal measure
 * Classes implementing this interface must define the number of sides and the length of each side
 */
public interface RegularPolygon {
  
  /**
   * Retrieves the number of sides of the regular polygon
   * 
   * @return the number of sides of the polygon
   */
  int getNumSides();
  
  /**
   * Retrieves the length of each side of the regular polygon
   * 
   * @return the length of the polygon's sides
   */
  double getSideLength();
}