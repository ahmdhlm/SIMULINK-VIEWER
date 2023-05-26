package javafx_test;

import org.w3c.dom.*;
import javax.xml.xpath.XPathExpressionException;
import javafx.scene.layout.Pane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UnitDelayBlock extends Block {

    public UnitDelayBlock() {
        belowTextOffset = -10;
    }

    @Override
    public void extractInfo(Document doc) {

        XPath xpath = XPathFactory.newInstance().newXPath();

        // 1 - Detect Constant Block
        String blockType = "//Block[@BlockType='UnitDelay']";
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
        String expression = "//Block[@BlockType='UnitDelay']/P[@Name='Position']"; // Change it with each block
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

        // 2- function inside Rectangle
        Text fraction = new Text(CenterX - 2, CenterY + 13, "1\n─\nz");
        // Text fraction = new Text("1\nz");
        fraction.setTextAlignment(TextAlignment.CENTER);
        fraction.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        fraction.setLineSpacing(-10.5);
        fraction.setWrappingWidth(40);
        // fraction.setFont(Font.font(11));
        pane.getChildren().add(fraction);
    }

}
