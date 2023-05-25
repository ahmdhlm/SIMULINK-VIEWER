import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import org.w3c.dom.Node;

public class Lines {
    static int noCreatedLines; // It's used to detect the next line

    static public void extractInfo(Document doc, Pane pane, Block[] arr) {

        NodeList lineList = doc.getElementsByTagName("Line");

        // Loop over each <Line> element and determine if it has a branch

        Node lineNode = lineList.item(noCreatedLines);
        boolean hasBranch = false;

        // Check if the <Line> element has any child elements with the name "Branch"
        NodeList childList = lineNode.getChildNodes();
        for (int j = 0; j < childList.getLength(); j++) {
            Node childNode = childList.item(j);
            if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("Branch")) {
                hasBranch = true;
                break;
            }
        }

        // Print out whether or not the current <Line> element has a branch
        if (hasBranch) {

            System.out.println("Line has a branch");
            new BranchedLine(doc, pane, arr, noCreatedLines);

        } else {
            System.out.println("Line does not have a branch");
            new NonBranchedLine(doc, pane, arr, noCreatedLines);

        }
        noCreatedLines++;
    }

    static public void drawLineWithArrow(Pane pane, double startX, double startY, double endX, double endY) {

        Line line_2 = new Line(startX, startY, endX, endY);

        // calculate the angle between the line and the x-axis
        double angle_2 = Math.atan2(endY - startY, endX - startX)
                * 180 / Math.PI;

        // create an arrowhead polygon
        Polygon arrowhead_2 = new Polygon();
        arrowhead_2.getPoints().addAll(new Double[] {
                endX - 5 * Math.cos(Math.toRadians(angle_2 + 15)),
                endY - 5 * Math.sin(Math.toRadians(angle_2 + 15)), // point 1
                endX, endY, // point 2
                endX - 5 * Math.cos(Math.toRadians(angle_2 - 15)),
                endY - 5 * Math.sin(Math.toRadians(angle_2 - 15)), // point 3
                endX - 5 * Math.cos(Math.toRadians(angle_2)), endY - 5 *
                        Math.sin(Math.toRadians(angle_2)), // point
                // 4
                endX - 5 * Math.cos(Math.toRadians(angle_2 + 15)),
                endY - 5 * Math.sin(Math.toRadians(angle_2 + 15)) // point 5
        });

        // set the stroke color and width of the line
        line_2.setStroke(Color.CORNFLOWERBLUE);
        line_2.setStrokeWidth(2);
        line_2.setStrokeType(StrokeType.OUTSIDE);
        // set the fill color of the arrowhead_1
        arrowhead_2.setStroke(Color.CORNFLOWERBLUE);
        arrowhead_2.setStrokeWidth(2);
        arrowhead_2.setStrokeType(StrokeType.OUTSIDE);
        // add the line and arrowhead_1 to a group_1
        Group group_2 = new Group();
        group_2.getChildren().addAll(line_2, arrowhead_2);

        // create a line
        Line line_1 = new Line(startX, startY, endX, endY);

        // calculate the angle between the line and the x-axis
        double angle_1 = Math.atan2(endY - startY, endX - startX)
                * 180 / Math.PI;

        // create an arrowhead polygon
        Polygon arrowhead_1 = new Polygon();
        arrowhead_1.getPoints().addAll(new Double[] {
                endX - 5 * Math.cos(Math.toRadians(angle_1 + 15)),
                endY - 5 * Math.sin(Math.toRadians(angle_1 + 15)), // point 1
                endX, endY, // point 2
                endX - 5 * Math.cos(Math.toRadians(angle_1 - 15)),
                endY - 5 * Math.sin(Math.toRadians(angle_1 - 15)), // point 3
                endX - 5 * Math.cos(Math.toRadians(angle_1)), endY - 5 *
                        Math.sin(Math.toRadians(angle_1)), // point
                // 4
                endX - 5 * Math.cos(Math.toRadians(angle_1 + 15)),
                endY - 5 * Math.sin(Math.toRadians(angle_1 + 15)) // point 5
        });

        // set the stroke color and width of the line
        line_1.setStroke(Color.BLACK);
        line_1.setStrokeWidth(1);

        // set the fill color of the arrowhead_1
        arrowhead_1.setFill(Color.BLACK);
        arrowhead_1.setStrokeWidth(1);

        // add the line and arrowhead_1 to a group_1
        Group group_1 = new Group();
        group_1.getChildren().addAll(line_1, arrowhead_1);

        pane.getChildren().addAll(group_2, group_1);
    }

    static public void drawNormal(Pane pane, double startX, double startY, double endX, double endY) {

        Line line_2 = new Line(startX, startY, endX, endY);

        // set the stroke color and width of the line
        line_2.setStroke(Color.CORNFLOWERBLUE);
        line_2.setStrokeWidth(2);
        line_2.setStrokeType(StrokeType.OUTSIDE);

        // add the line and arrowhead_1 to a group_1
        Group group_2 = new Group();
        group_2.getChildren().addAll(line_2);

        // create a line
        Line line_1 = new Line(startX, startY, endX, endY);
        // set the stroke color and width of the line
        line_1.setStroke(Color.BLACK);
        line_1.setStrokeWidth(1);

        // add the line and arrowhead_1 to a group_1
        Group group_1 = new Group();
        group_1.getChildren().addAll(line_1);

        pane.getChildren().addAll(group_2, group_1);
    }
}
