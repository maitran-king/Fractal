import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * JUnit test suite for the RectangleFractal class
 * This class tests the functionality of fractal generation using both rectangles and squares
 */
public class RectangleFractalClassTest {
  /** Base test shapes */
  private Rectangle rectangle = new Rectangle(new Point(0, 0), 4.0, 2.0);
  private Square square = new Square(new Point(0, 0), 2.0);
  
  /**
   * Test the constructor of RectangleFractal
   * Ensures the base shape and recursion levels are initialized correctly
   */
  @Test
  public void testConstructorInitialization() {
    RectangleFractal fractal1 = new RectangleFractal(rectangle, 2);
    
    // verify rectangle fractal initialization
    assertEquals(rectangle, fractal1.getBaseShape());
    assertEquals(2, fractal1.getNumLevels());
    
    RectangleFractal fractal2 = new RectangleFractal(square, 2);
    
    //verify square fractal initialization
    assertEquals(square, fractal2.getBaseShape());
    assertEquals(2, fractal2.getNumLevels());
  }
  
  /**
   * Test getPoints() for a level 0 fractal using both Rectangle and Square
   * Ensures the fractal points match the base shape Points
   */
  @Test
  public void testGetPointsLevelZero() {
    RectangleFractal fractal1 = new RectangleFractal(rectangle, 0);
    
    //verify points for rectangle fractal level 0
    Point[] points1 = fractal1.getPoints();
    assertArrayEquals(rectangle.getPoints(), points1);
    
    RectangleFractal fractal2 = new RectangleFractal(square, 0);
    
    //verify points for square fractal level 0
    Point[] points2 = fractal2.getPoints();
    assertArrayEquals(square.getPoints(), points2);
  }
  
  /**
   * Test getPoints() for a level 1 fractal using both Rectangle and Square
   * Ensures the fractal generates additional points
   */
  @Test
  public void testGetPointsLevelOne() {
    RectangleFractal fractal1 = new RectangleFractal(square, 1);
    
    //verify points for square fractal level 1
    Point[] points1 = fractal1.getPoints();
    assertNotNull(points1);
    assertEquals(16, points1.length);
    
    RectangleFractal fractal2 = new RectangleFractal(rectangle, 1);
    
    //verify points for rectangle fractal level 1
    Point[] points2 = fractal2.getPoints();
    assertNotNull(points2);
    assertEquals(16, points2.length);
  }
  
  /**
   * Test getLines() for a level 0 fractal using both Rectangle and Square
   * Ensures the fractal lines match the base shape lines
   */
  @Test
  public void testGetLinesLevelZero() {
    RectangleFractal fractal1 = new RectangleFractal(square, 0);
    
    //verify lines for square fractal level 0
    Line[] lines1 = fractal1.getLines();
    assertNotNull(lines1);
    assertArrayEquals(square.getLines(), lines1);
    
    RectangleFractal fractal2 = new RectangleFractal(rectangle, 0);
    
    //verify lines for rectangle fractal level 0
    Line[] lines2 = fractal2.getLines();
    assertNotNull(lines2);
    assertArrayEquals(rectangle.getLines(), lines2);
  }
  
  /**
   * Test getLines() for a level 1 fractal using both Rectangle and Square
   * Ensures the fractal generates additional lines
   */
  @Test
  public void testGetLinesLevelOne() {
    RectangleFractal fractal1 = new RectangleFractal(square, 1);
    
    //verify lines for square fractal level 1
    Line[] lines1 = fractal1.getLines();
    assertNotNull(lines1);
    assertEquals(24, lines1.length);
    
    RectangleFractal fractal2 = new RectangleFractal(rectangle, 1);
    
    //verify lines for rectangle fractal level 1
    Line[] lines2 = fractal2.getLines();
    assertNotNull(lines2);
    assertEquals(24, lines2.length);
  }
  
  /**
   * Test getLines() for a level 2 fractal using both Rectangle and Square
   * Ensures the fractal generates significantly more lines
   */
  @Test
  public void testGetLinesHigherLevel() {
    RectangleFractal fractal1 = new RectangleFractal(square, 2);
    
    //verify lines for square fractal level 2
    Line[] lines1 = fractal1.getLines();
    assertNotNull(lines1);
    assertEquals(168, lines1.length);
    
    RectangleFractal fractal2 = new RectangleFractal(rectangle, 2);
    
    //verify lines for rectangle fractal level 2
    Line[] lines2 = fractal2.getLines();
    assertNotNull(lines2);
    assertEquals(168, lines2.length);
  }
  
  /**
   * Test rotate() for both Rectangle and Square fractals
   * Ensures the fractal rotates correctly about its center
   */
  @Test
  public void testRotate() {
    RectangleFractal fractal1 = new RectangleFractal(square, 1);
    
    //verify rotation for square fractal
    Point[] originalPoints1 = fractal1.getPoints();
    fractal1.rotate(Math.toRadians(90)); 
    Point[] rotatedPoints1 = fractal1.getPoints();
    
    Point rotatedPoint1 = originalPoints1[0];
    rotatedPoint1.rotateAbout(square.getCenter(), Math.toRadians(-90));
    
    assertNotEquals(originalPoints1, rotatedPoints1);
    assertEquals(rotatedPoint1, rotatedPoints1[0]);
    assertNotEquals(originalPoints1,rotatedPoints1);
    
    RectangleFractal fractal2 = new RectangleFractal(rectangle, 1);
    
    Point[] originalPoints2 = fractal2.getPoints();
    Point[] rotatedPoints2 = fractal2.getPoints();
   
    //verify rotation for rectangle fractal
    assertArrayEquals(originalPoints2, rotatedPoints2);
  }
}