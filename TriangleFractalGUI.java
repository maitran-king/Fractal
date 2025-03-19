import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.canvas.GraphicsContext;

/**
 * TriangleFractalGUI is a JavaFX application for creating and visualizing triangle fractals
 * Users can input the coordinates of the triangle's vertices and the number of recursion levels
 * to generate the fractal on a canvas
 */
public class TriangleFractalGUI extends Application {
  
  /** Input fields for triangle vertices and recursion levels */
  private TextField x1Field = new TextField("0");
  private TextField y1Field = new TextField("500");
  private TextField x2Field = new TextField("250");
  private TextField y2Field = new TextField("0");
  private TextField x3Field = new TextField("500");
  private TextField y3Field = new TextField("500");
  private TextField numLevelsField = new TextField("1");
  
  /** Canvas for drawing the fractal */
  private Canvas canvas = new Canvas(500.0, 500.0);
  
  /**
   * Main entry point for the JavaFX application
   * Sets up the layout, input fields, and event handling for the GUI
   *
   * @param primaryStage the primary Stage for the application
   */
  public void start(Stage primaryStage) {
    //main layout container
    BorderPane pane = new BorderPane();
    
    //labels for input fields
    Label x1Label = new Label("X1:");
    Label y1Label = new Label("Y1:");
    Label x2Label = new Label("X2:");
    Label y2Label = new Label("Y2:");
    Label x3Label = new Label("X3:");
    Label y3Label = new Label("Y3:");
    Label numLevelsLabel = new Label("Number of Levels:");
    
    //button to trigger fractal drawing
    Button drawButton = new Button("Draw Fractal");
    
    //GridPane for organizing input fields
    GridPane inputPane = new GridPane();
    
    //set the gaps between the input
    inputPane.setHgap(10);
    inputPane.setVgap(10);
    
    //add labels and fields to the grid
    inputPane.addRow(0, x1Label, x1Field, y1Label, y1Field);
    inputPane.addRow(1, x2Label, x2Field, y2Label, y2Field);
    inputPane.addRow(2, x3Label, x3Field, y3Label, y3Field);
    inputPane.addRow(3, numLevelsLabel, numLevelsField);
    inputPane.add(drawButton, 0, 4, 4, 1);
    
    //add canvas and input panel to the layout
    pane.setCenter(canvas);
    pane.setLeft(inputPane);
    
    //set action for the draw button
    drawButton.setOnAction(e -> processFractal());
    
    //create and set up the scene
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Main method to launch the JavaFX application
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
  /**
   * Processes user inputs, generates the fractal, and draws it on the canvas
   * Handles invalid inputs and errors via dialogs
   */
  private void processFractal() {
    try {
      //get user inputs
      double x1 = Double.parseDouble(x1Field.getText());
      double y1 = Double.parseDouble(y1Field.getText());
      double x2 = Double.parseDouble(x2Field.getText());
      double y2 = Double.parseDouble(y2Field.getText());
      double x3 = Double.parseDouble(x3Field.getText());
      double y3 = Double.parseDouble(y3Field.getText());
      int numLevels = Integer.parseInt(numLevelsField.getText());
      
      Point p1 = new Point(x1, y1);
      Point p2 = new Point(x2, y2);
      Point p3 = new Point(x3, y3);
            
      Triangle triangle = new Triangle(p1,p2,p3);
      Triangle baseShape;
      
      //determine base shape, if all sides are equal, the base shape is EquilateralTriangle, else the base shape is Triangle
      if (isEquilateral(p1,p2,p3))
        baseShape = new EquilateralTriangle(triangle.getCenter(),calculateSideLength(p1, p2));
      else
        baseShape = triangle;
      
      //create the fractal
      TriangleFractal fractal = new TriangleFractal (baseShape, numLevels);
      
      //draw the fractal
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //clear canvas
      
      for (Line line : fractal.getLines()) {
        gc.strokeLine(
                      line.getFirstPoint().getX(),
                      line.getFirstPoint().getY(),
                      line.getSecondPoint().getX(),
                      line.getSecondPoint().getY()
                     );
      }
    } catch (NumberFormatException e) {
      showErrorDialog("Invalid input", "Please enter valid numbers for all inputs.");
    } catch (IllegalArgumentException e) {
      showErrorDialog("Invalid Input", e.getMessage());
    }
  }
  
  /**
   * Displays an error dialog with the given title and message
   *
   * @param title the title of the error dialog
   * @param content the content of the error message
   */
  private void showErrorDialog(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
  }
  
  /**
   * Calculates the side length between two Points
   *
   * @param p1 the first Point
   * @param p2 the second Point
   * @return the distance between the two Points
   */
  private static double calculateSideLength(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
  }
  
  /**
   * Determines whether a triangle is equilateral
   *
   * @param p1 the first vertex of the triangle
   * @param p2 the second vertex of the triangle
   * @param p3 the third vertex of the triangle
   * @return true if the triangle is equilateral, otherwise false
   */
  private static boolean isEquilateral(Point p1, Point p2, Point p3) {
    //calculate the side lengths of the triangle
    double side1 = calculateSideLength(p1, p2);
    double side2 = calculateSideLength(p2, p3);
    double side3 = calculateSideLength(p3, p1);
    
    //check if all sides are approximately equal
    return Math.abs(side1 - side2) < 1e-6 && Math.abs(side2 - side3) < 1e-6;
  }
}
