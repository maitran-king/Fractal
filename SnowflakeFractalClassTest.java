import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * JUnit test suite for the SnowflakeFractal class
 * This class tests various functionalities of the SnowflakeFractal, 
 * including initialization, point generation, line generation, and rotation
 */
public class SnowflakeFractalClassTest {
  
  /** Base test shapes */
  private EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 2.0);
  private Square square = new Square(new Point(0, 0), 4.0);
  private NGon ngon = new NGon (new Point(0, 0), 1.0, 5);
  
  /**
   * Test constructor initialization for different base shapes and recursion levels
   */
  @Test
  public void testConstructorInitialization() {
    SnowflakeFractal<EquilateralTriangle> fractal1 = new SnowflakeFractal<>(triangle, 3);
    
    //verify equilateral triangle initialization
    assertEquals(triangle, fractal1.getBaseShape());
    assertEquals(3, fractal1.getNumLevels());
    
    SnowflakeFractal<Square> fractal2 = new SnowflakeFractal<>(square, 0);
    
    //verify square initialization
    assertEquals(square, fractal2.getBaseShape());
    assertEquals(0, fractal2.getNumLevels());
    
    SnowflakeFractal<NGon> fractal3 = new SnowflakeFractal<>(ngon, 2);
    
    //verify pentagon initialization
    assertEquals(ngon, fractal3.getBaseShape());
    assertEquals(2, fractal3.getNumLevels());
  }
  
  /**
   * Test invalid number of recursion levels
   * Negative levels should throw an IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumLevels() {
    EquilateralTriangle baseShape = new EquilateralTriangle(new Point(0, 0), 1.0);
    new SnowflakeFractal<>(baseShape, -1); 
  }
  
  /**
   * Test changing the center of the fractal and verify updated Points
   */
  @Test
  public void testSetCenter() {
    SnowflakeFractal<EquilateralTriangle> fractal = new SnowflakeFractal<>(triangle, 1);
    
    Point[] originalPoints = fractal.getPoints();
    
    Point newCenter = new Point(5, 5);
    fractal.setCenter(newCenter);
    
    assertEquals(newCenter, fractal.getCenter());
    
    Point[] updatedPoints = fractal.getPoints();
    
    assertNotNull(updatedPoints);
    assertNotEquals(originalPoints, updatedPoints);
    
    for (Point point : updatedPoints) {
      assertTrue(point.getX() >= newCenter.getX() - triangle.getSideLength() &&
                 point.getY() >= newCenter.getY() - triangle.getSideLength());
    }
  }
  
  /**
   * Test getPoints() for fractals with level 0 recursion
   * Points should match the base shape
   */
  @Test
  public void testGetPointsLevelZero() {
    SnowflakeFractal<EquilateralTriangle> fractal1 = new SnowflakeFractal<>(triangle, 0);
    
    //verify points for equilateral triangle fractal level 0
    Point[] points1 = fractal1.getPoints();
    assertEquals(triangle.getPoints().length, points1.length);
    assertArrayEquals(triangle.getPoints(), points1);
    
    SnowflakeFractal<Square> fractal2 = new SnowflakeFractal<>(square, 0);
    
    //verify points for square fractal level 0
    Point[] points2 = fractal2.getPoints();
    assertEquals(square.getPoints().length, points2.length);
    assertArrayEquals(square.getPoints(), points2);
  }
  
  /**
   * Test getPoints() for fractals with level 1 recursion
   * Points should include subdivisions from the base shape
   */
  @Test
  public void testGetPointsLevelOne() {
    SnowflakeFractal<Square> fractal = new SnowflakeFractal<>(square, 1);
    Point[] expectedPoints = new Point[] {new Point (-2, -2), new Point (-2.0/3, -2), 
      new Point (0, -3.154701), new Point (2.0/3, -2), new Point (2, -2), 
      new Point (2, -2.0/3), new Point (3.154701, 0), new Point (2, 2.0/3),
      new Point (2, 2), new Point (2.0/3, 2), new Point (0, 3.154701), new Point (-2.0/3, 2), 
      new Point (-2, 2), new Point (-2, 2.0/3), new Point (-3.154701, 0), new Point (-2, -2.0/3)};
    
    //verify points for square fractal level 1
    Point[] points = fractal.getPoints();
    assertNotNull(points);
    assertTrue(points.length > square.getPoints().length);
    assertEquals(16, points.length);
    assertArrayEquals(points, expectedPoints);
  }
  
  /**
   * Test getPoints() for higher recursion levels
   */
  @Test
  public void testGetPointsHigherLevel() {
    SnowflakeFractal<Square> fractal = new SnowflakeFractal<>(square, 2);
    
    //verify points for square fractal level 2
    Point[] points = fractal.getPoints();
    assertEquals(64, points.length);
  }
  
  /**
   * Test getLines() for fractals with level 0 recursion
   * Lines should match the base shape's edges
   */
  @Test
  public void testGetLinesLevelZero() {
    SnowflakeFractal<EquilateralTriangle> fractal1 = new SnowflakeFractal<>(triangle, 0);
    
    //verify lines for equilateral triangle fractal level 0
    Line[] lines1 = fractal1.getLines();
    assertNotNull(lines1);
    assertEquals(triangle.getPoints().length, lines1.length);
    
    SnowflakeFractal<Square> fractal2 = new SnowflakeFractal<>(square, 0);
    
    //verify lines for square fractal level 0
    Line[] lines2 = fractal2.getLines();
    assertNotNull(lines2);
    assertEquals(square.getPoints().length, lines2.length);
  }
  
  /**
   * Test getLines() for fractals with level 1 recursion
   */
  @Test
  public void testGetLinesLevelOne() {
    SnowflakeFractal<Square> fractal1 = new SnowflakeFractal<>(square, 1);
    
    //verify lines for square fractal level 1
    Line [] lines1 = fractal1.getLines();
    assertEquals(16, lines1.length);
    
    SnowflakeFractal<NGon> fractal2 = new SnowflakeFractal<>(ngon, 1);
    
    //verify lines for pentagon fractal level 1
    Line [] lines2 = fractal2.getLines();
    assertEquals(20, lines2.length);
  }
  
  /**
   * Test rotating the fractal
   */
  @Test
  public void testRotate() {
    SnowflakeFractal<EquilateralTriangle> fractal = new SnowflakeFractal<>(triangle, 1);
    
    Point[] originalPoints = fractal.getPoints();
    fractal.rotate(Math.toRadians(90));
    Point[] rotatedPoints = fractal.getPoints();
    
    Point rotatedPoint = originalPoints[0];
    rotatedPoint.rotateAbout(triangle.getCenter(), Math.toRadians(90));
    
    //verify rotation for equilateral fractal
    assertNotEquals(originalPoints, rotatedPoints);
    assertEquals(rotatedPoint, rotatedPoints[0]);
  }
}