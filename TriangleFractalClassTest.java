import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * JUnit test suite for the TriangleFractal class
 * This class tests various functionalities of the TriangleFractal, 
 * including initialization, point generation, line generation, and rotation
 */
public class TriangleFractalClassTest {
  /** Test shapes */
  private EquilateralTriangle equilateral = new EquilateralTriangle(new Point(0, 0), 1.0);
  private Triangle triangle = new Triangle(
                                      new Point(-1, 0), 
                                      new Point(1, 0), 
                                      new Point(0, Math.sqrt(3))
                                     );
  /**
   * Test constructor initialization for TriangleFractal with different base shapes and levels
   */
  @Test
  public void testConstructorInitializatio() {
    TriangleFractal fractal1 = new TriangleFractal(equilateral, 2);
    
    // verify equilateral triangle fractal initialization
    assertEquals(equilateral, fractal1.getBaseShape());
    assertEquals(2, fractal1.getNumLevels());
    
    TriangleFractal fractal2 = new TriangleFractal(triangle, 2);
    
    // verify triangle fractal initialization
    assertEquals(triangle, fractal2.getBaseShape());
    assertEquals( 2, fractal2.getNumLevels());
  }
  
  /**
   * Test getPoints() for fractals with level 0 recursion
   * Points should match the base triangle
   */
  @Test
  public void testGetPointsLevelZero() {
    TriangleFractal fractal1 = new TriangleFractal(equilateral, 0);
    
    //verify points for equilateral triangle fractal level 0
    Point[] points1 = fractal1.getPoints();
    assertEquals(equilateral.getPoints().length, points1.length);
    assertArrayEquals(equilateral.getPoints(), points1);
    
    TriangleFractal fractal2 = new TriangleFractal(triangle, 0);
    
    //verify points for triangle fractal level 0
    Point[] points2 = fractal2.getPoints();
    assertEquals(triangle.getPoints().length, points2.length);
    assertArrayEquals(triangle.getPoints(), points2);
  }
  
  /**
   * Test getPoints() for fractals with level 1 recursion
   * Points should include subdivisions from the base triangle
   */
  @Test
  public void testGetPointsLevelOne() {
    TriangleFractal fractal1 = new TriangleFractal(equilateral, 1);
    
    //verify points for equilateral triangle fractal level 1
    Point[] points1 = fractal1.getPoints();
    assertNotNull(points1);
    assertTrue(points1.length > equilateral.getPoints().length);
    assertEquals(4, points1.length); //includes center and three vertices
    assertEquals(points1[0], equilateral.getCenter());
    
    TriangleFractal fractal2 = new TriangleFractal(triangle, 1);
    
    //verify points for triangle fractal level 1
    Point[] points2 = fractal2.getPoints();
    assertNotNull(points2);
    assertTrue(points2.length > triangle.getPoints().length);
    assertEquals(4, points1.length);
    assertEquals(points2[0], triangle.getCenter());
  }
  
  /**
   * Test getPoints() for fractals with higher recursion levels.
   */
  @Test
  public void testGetPointsHigherLevel() {
    TriangleFractal fractal = new TriangleFractal(equilateral, 2);
    
    Point[] points = fractal.getPoints();
    assertNotNull(points);
    assertEquals(7, points.length);
  }
  
  /**
   * Test getLines() for fractals with level 0 recursion.
   * Lines should match the base triangle's edges.
   */
  @Test
  public void testGetLinesLevelZero() {
    TriangleFractal fractal1 = new TriangleFractal(equilateral, 0);
    
    //verify lines for equilateral triangle fractal level 0
    Line[] lines1 = fractal1.getLines();
    assertNotNull(lines1);
    assertArrayEquals(equilateral.getLines(), lines1);
    
    TriangleFractal fractal2 = new TriangleFractal(triangle, 0);
    
    //verify lines for triangle fractal level 0
    Line[] lines2 = fractal2.getLines();
    assertNotNull(lines2);
    assertArrayEquals(triangle.getLines(), lines2);
  }
  
  /**
   * Test getLines() for fractals with level 1 recursion.
   */
  @Test
  public void testGetLinesLevelOne() {
    TriangleFractal fractal = new TriangleFractal(triangle, 1);
    
    Line[] lines = fractal.getLines();
    assertNotNull(lines);
    assertEquals(6, lines.length); //includes inner lines of the smaller triangles
  }
  
  /**
   * Test getLines() for fractals with higher recursion levels.
   */
  @Test
  public void testGetLinesHigherLevel() {
    TriangleFractal fractal = new TriangleFractal(triangle, 2);
    
    Line[] lines = fractal.getLines();
    assertNotNull(lines);
    assertEquals(15, lines.length);
  }
  
  /**
   * Test the rotate() method for fractals.
   * Verifies that all points are rotated correctly around the center.
   */
  @Test
  public void testRotate() {
    TriangleFractal fractal = new TriangleFractal(triangle, 1);
    
    Point[] originalPoints = fractal.getPoints();
    fractal.rotate(Math.toRadians(45));
    Point[] rotatedPoints = fractal.getPoints();

    Point rotatedPoint = originalPoints[0];
    rotatedPoint.rotateAbout(triangle.getCenter(), Math.toRadians(45));
    
    assertNotEquals(originalPoints, rotatedPoints);
    assertEquals(rotatedPoint, rotatedPoints[0]);
    assertNotEquals(originalPoints,rotatedPoints);
  }
}