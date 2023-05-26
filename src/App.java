

import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.*;


public class App extends Application {
    

    @Override
    public void start(Stage primaryStage) {
        
        String logopath = getClass().getResource("project.jpg").toString();
        Image logo = new Image(logopath) ;
        primaryStage.getIcons().add(logo) ;
        
        
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setIconImage(new ImageIcon(logo.getUrl()).getImage());
        });
        
        Button parseButton = new Button("Browse");
        // Circle circle = new Circle(50);
        // Circle circle2 = new Circle(50);
        parseButton.setOnAction((ActionEvent e) -> {
            try {
                // Get the selected file using a FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select XML file");

                ExtensionFilter extFilter = new ExtensionFilter("MDL files (*.mdl)", "*.mdl");
                fileChooser.getExtensionFilters().add(extFilter);

                File selectedFile = fileChooser.showOpenDialog(primaryStage);

                if (selectedFile != null) {
                    // Parse the XML file using DocumentBuilderFactory
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    Document doc = factory.newDocumentBuilder().parse(selectedFile);

                    String start = "__MWOPC_PART_BEGIN__ /simulink/systems/system_root.xml";
                    String end = "__MWOPC_PART_BEGIN__ /simulink/windowsInfo.xml";

                    Stage stage = new Stage();
                    Pane pane = new Pane();
                    Scene scene = new Scene(pane, 1400, 720);
                    
                    // *************** Here Extract Blocks + Draw Them **************//
                    Block[] arr = { new ConstantBlock(), new ScopeBlock(), new AddBlock(), new SaturationBlock(),
                            new UnitDelayBlock() };
                    // 1- Constant Block
                    for (int i = 0; i < arr.length; i++) {
                        arr[i].extractInfo(doc);
                        arr[i].drawBlock(pane, arr[i].getBelowTextOffset());
                        System.out.println("element " + i + arr[i].belowTextOffset);
                    }

                    // *************************************************************//
                    NodeList lines = doc.getElementsByTagName("Line");
                    System.out.println(lines.getLength());
                    Lines[] arr2 = new Lines[lines.getLength()];

                    for (int i = 0; i < arr2.length; i++) {
                        Lines.extractInfo(doc, pane, arr);

                    }

                    stage.setScene(scene);
                    stage.getIcons().add(logo) ;
                    stage.setTitle("APPLICATION");
                    stage.show();
                } else {
                    // Handle the case where the Position attribute is empty
                    System.out.println("File is Empty");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        parseButton.setStyle(
                "-fx-font-size: 25px; -fx-min-width: 90px; -fx-min-height: 60px;-fx-background-color: DARKORANGE; -fx-border-color: white; -fx-border-width: 3;");

        // Load the image file
        // Image image = new Image(
                // "C:/Users/mh_sm/Dropbox/PC/Desktop/computer/Advanced/project/v627-aew-41-technologybackground.jpg");

        // Create an ImageView and set the loaded image to it
        // ImageView imageView = new ImageView(image);
        // imageView.setFitWidth(800);
        // imageView.setPreserveRatio(true);

        // Create a Text node and set its properties
        Text text = new Text("Simulink Tool\n ");
        text.setFill(Color.WHITESMOKE); // Set the text color to red
        text.setStrokeWidth(3.5);
        text.setStroke(Color.DARKORANGE); // GOLDENROD
        text.setFont(Font.font("Arial Bold", 40));

        // Create an AnchorPane and add the imageView, text, and parseButton to it
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(text, parseButton);

        // Set the position of the text relative to the AnchorPane
        AnchorPane.setLeftAnchor(text, 285.0);
        AnchorPane.setTopAnchor(text, 125.0);

        AnchorPane.setLeftAnchor(parseButton, 340.0);
        AnchorPane.setTopAnchor(parseButton, 300.0);

        Scene scene = new Scene(anchorPane, 800, 500);
        primaryStage.setResizable(false);
        primaryStage.setTitle("SIMULINK VIEWER");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
