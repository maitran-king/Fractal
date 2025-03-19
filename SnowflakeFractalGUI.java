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
 * SnowflakeFractalGUI is a JavaFX application for creating and visualizing snowflake fractals.
 * Users can input the number of sides, side length, number of recursion levels, and rotation angle 
 * to generate the fractal on a canvas.
 */
public class SnowflakeFractalGUI extends Application {
  
  /** Input fields for fractal parameters */
  private TextField numSidesField = new TextField("3");
  private TextField sideLengthField = new TextField("100");
  private TextField numLevelsField = new TextField("1");
  private TextField rotationField = new TextField("0");
  
  /** Canvas for drawing the fractal */
  private Canvas canvas = new Canvas(500.0, 500.0);
  
  /**
   * The main entry point for the JavaFX application
   * Sets up the layout, input fields, and event handling for the GUI
   *
   * @param primaryStage the primary Stage for the application
   */
  public void start(Stage primaryStage) {
    //main layout container
    BorderPane pane = new BorderPane();
    
    //labels for input fields
    Label numSidesLabel = new Label("Number of Sides:");
    Label sideLengthLabel = new Label("Side Length:");
    Label numLevelsLabel = new Label("Number of Levels:");
    Label rotationLabel = new Label("Rotation (degree):");
    
    //button to trigger fractal drawing
    Button drawButton = new Button("Draw Fractal");
    
    //GridPane to organize input fields and labels
    GridPane inputPane = new GridPane();
    
    //set the gaps between the input
    inputPane.setHgap(10);
    inputPane.setVgap(10);
    
    inputPane.add(numSidesLabel, 0, 0);
    inputPane.add(numSidesField, 1, 0);
    
    inputPane.add(sideLengthLabel, 0, 1);
    inputPane.add(sideLengthField, 1, 1);
    
    inputPane.add(numLevelsLabel, 0, 2);
    inputPane.add(numLevelsField, 1, 2);
    
    inputPane.add(rotationLabel, 0, 3);
    inputPane.add(rotationField, 1, 3);
    
    inputPane.add(drawButton, 0, 4, 2, 1);
    
    //place the canvas and input panel in the layout
    pane.setCenter(canvas);
    pane.setLeft(inputPane);
    
    //set the action for the draw button
    drawButton.setOnAction(e -> processFractal());
    
    //create and set up the scene
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * The main method to launch the application
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
  /**
   * Processes user inputs, generates the fractal, and draws it on the canvas
   * Handles invalid inputs and errors through dialogs
   */
  private void processFractal() {
    try {
      //get user inputs
      int numSides = Integer.parseInt(numSidesField.getText());
      double sideLength = Double.parseDouble(sideLengthField.getText());
      int numLevels = Integer.parseInt(numLevelsField.getText());
      double rotation = Double.parseDouble(rotationField.getText());
      
      //center of the canvas
      Point center = new Point (250,250);
      
      //choose which shape will be the base shape using the numSides
      if (numSides < 3)
        throw new IllegalArgumentException("The number of sides must be at least 3.");
      else if (numSides == 3) {
        //create a fractal with an equilateral triangle base
        SnowflakeFractal<EquilateralTriangle> triangle =
          new SnowflakeFractal<EquilateralTriangle> ((new EquilateralTriangle(center, sideLength)),numLevels);
        triangle.rotate(Math.toRadians(rotation));
        drawFractal(triangle);
      }
      else if (numSides == 4) {
        //create a fractal with a square base
        SnowflakeFractal<Square> square = 
          new SnowflakeFractal<Square> ((new Square (center, sideLength)),numLevels);
        square.rotate(Math.toRadians(rotation));
        drawFractal(square);
      }
      else {
         //create a fractal with a regular polygon base
        SnowflakeFractal<NGon> ngon = 
          new SnowflakeFractal<NGon> ((new NGon (center, sideLength, numSides)),numLevels);
        ngon.rotate(Math.toRadians(rotation));
        drawFractal(ngon);
      }
    } 
    catch (NumberFormatException e) {
      showErrorDialog("Invalid input", "Please enter valid numbers for all inputs.");
    } catch (IllegalArgumentException e) {
      showErrorDialog("Invalid Input", e.getMessage());
    }
  }
  
  /**
   * Draws the fractal on the canvas
   *
   * @param fractal the fractal to be drawn
   * @param <T> the type of Polygon used to generate the fractal
   */
  private <T extends Polygon & RegularPolygon> void drawFractal(SnowflakeFractal<T> fractal) {
    //clear previous drawings
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    
    //draw all lines of the fractal
    for (Line line : fractal.getLines()) {
      gc.strokeLine(
                    line.getFirstPoint().getX(),
                    line.getFirstPoint().getY(),
                    line.getSecondPoint().getX(),
                    line.getSecondPoint().getY()
                   );
    }
  }
  
  /**
   * Displays an error dialog with a given title and message
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
}
