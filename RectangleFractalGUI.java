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
 * RectangleFractalGUI is a JavaFX application that provides a GUI
 * for creating and visualizing rectangle fractals. Users can input 
 * the width, height, number of levels, and rotation angle for the fractal
 */
public class RectangleFractalGUI extends Application {
  
  /** Text fields for user input */
  private TextField widthField = new TextField("100");   //default width
  private TextField heightField = new TextField("100");  //default height
  private TextField numLevelsField = new TextField("1"); //default numLevels
  private TextField rotationField = new TextField("0");  //default rotation angle
  
  /** Canvas to draw the fractal */
  private Canvas canvas = new Canvas(500.0, 500.0);
  
  /**
   * The main entry point for the JavaFX application
   * Configures the layout, input fields, and button actions
   *
   * @param primaryStage the primary Stage for this application
   */
  public void start(Stage primaryStage) {
    //main layout container
    BorderPane pane = new BorderPane();
    
    //labels for input fields
    Label widthLabel = new Label("Width:");
    Label heightLabel = new Label("Height:");
    Label numLevelsLabel = new Label("Number of Levels:");
    Label rotationLabel = new Label("Rotation (degree):");
    
    //button to trigger fractal drawing
    Button drawButton = new Button("Draw Fractal");
    
    //GridPane for organizing input fields
    GridPane inputPane = new GridPane();
    
    //set the gaps between
    inputPane.setHgap(10);
    inputPane.setVgap(10);
    
    //adding input labels and fields to the GridPane
    inputPane.add(widthLabel, 0, 0);
    inputPane.add(widthField, 1, 0);
    
    inputPane.add(heightLabel, 0, 1);
    inputPane.add(heightField, 1, 1);
    
    inputPane.add(numLevelsLabel, 0, 2);
    inputPane.add(numLevelsField, 1, 2);
    
    inputPane.add(rotationLabel, 0, 3);
    inputPane.add(rotationField, 1, 3);
    
    inputPane.add(drawButton, 0, 4, 2, 1);
    
    //place the canvas and input pane in the layout
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
   * Handles errors and invalid inputs through dialogs
   */
  private void processFractal() {
    try {
      //get user inputs
      double width = Double.parseDouble(widthField.getText());
      double height = Double.parseDouble(heightField.getText());
      int numLevels = Integer.parseInt(numLevelsField.getText());
      double rotation = Double.parseDouble(rotationField.getText());
      
      //define the center point of the canvas
      Point center = new Point (250,250);
      
      //determine the base shape: Square if width == height, otherwise Rectangle
      Rectangle baseShape;
      if (width == height)
        baseShape = new Square(center,width);
      else
        baseShape = new Rectangle(center, width, height);
      
      //create the fractal and apply rotation
      RectangleFractal fractal = new RectangleFractal (baseShape, numLevels);
      fractal.rotate(Math.toRadians(rotation));
      
      //clear the canvas before drawing
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      
      //draw all Lines of the fractal
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
