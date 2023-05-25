import org.w3c.dom.*;
import javafx.scene.layout.Pane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javafx.scene.shape.Line;

public class SaturationBlock extends Block {
    public SaturationBlock() {
        belowTextOffset = -12;
    }

    @Override
    public void extractInfo(Document doc) {

        XPath xpath = XPathFactory.newInstance().newXPath();

        // 1 - Detect Constant Block
        String blockType = "//Block[@BlockType='Saturate']";
        Node blockNode = null;
        try {
            blockNode = (Node) xpath.evaluate(blockType, doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        // 2- Get SID
        String sid = ((Element) blockNode).getAttribute("SID");
        this.sid = Double.parseDouble(sid);
        System.out.println(sid); // Testing Line

        // 3- Get Name
        name = ((Element) blockNode).getAttribute("Name");
        System.out.println(name); // Testing Line

        // 4 - Select the Position Tag inside Constant Block
        String expression = "//Block[@BlockType='Saturate']/P[@Name='Position']"; // Change it with each block
        NodeList positionList = null;
        try {
            positionList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        // 5 - Extract the position data from the selected P element
        Element positionElement = (Element) positionList.item(0);
        String positionText = positionElement.getTextContent();
        if (!positionText.isEmpty()) {
            positionText = positionText.replace("[", "").replace("]", "");
            String[] substrings = positionText.split(", ");
            // 1--> Center X
            CenterX = Double.parseDouble(substrings[0]);
            System.out.println(CenterX); // Testing Line
            // 2--> Width
            width = Double.parseDouble(substrings[2]) - CenterX;
            System.out.println(width); // Testing Line
            // 3--> Center Y
            CenterY = Double.parseDouble(substrings[1]);
            System.out.println(CenterY); // Testing Line
            // 4--> Height
            height = Double.parseDouble(substrings[3]) - CenterY;
            System.out.println(height); // Testing Line
        }

    }

    @Override
    public void drawBlock(Pane pane, double x_offset) {
        super.drawBlock(pane, x_offset);
        // Create vertical line inside the rectangle
        Line verticalLine = new Line(CenterX + 15, CenterY + 4, CenterX + 15, CenterY + 26);
        verticalLine.setOpacity(0.2); // Set opacity level
        pane.getChildren().add(verticalLine);

        // Create horizontal line inside the rectangle
        Line horizontalLine = new Line(CenterX + 4, CenterY + 15, CenterX + 26, CenterY + 15);
        horizontalLine.setOpacity(0.2); // Set opacity level
        pane.getChildren().add(horizontalLine);

        Line diagonalLine = new Line(CenterX + 22, CenterY + 8, CenterX + 8, CenterY + 22);
        diagonalLine.setOpacity(0.4); // Set opacity level
        pane.getChildren().add(diagonalLine);

        // Create horizontal line 1 inside the rectangle
        Line horizontalLine1 = new Line(CenterX + 22, CenterY + 8, CenterX + 27, CenterY + 8);
        horizontalLine1.setOpacity(0.4); // Set opacity level
        pane.getChildren().add(horizontalLine1);

        // Create horizontal line 2 inside the rectangle
        Line horizontalLine2 = new Line(CenterX + 8, CenterY + 22, CenterX + 3, CenterY + 22);
        horizontalLine2.setOpacity(0.4); // Set opacity level
        pane.getChildren().add(horizontalLine2);

    }
}