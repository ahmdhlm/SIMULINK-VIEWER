import org.w3c.dom.*;
import javafx.scene.layout.Pane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AddBlock extends Block {

    public AddBlock() {
        belowTextOffset = 4;
    }

    @Override
    public void extractInfo(Document doc) {

        XPath xpath = XPathFactory.newInstance().newXPath();

        // 1 - Detect Constant Block
        String blockType = "//Block[@BlockType='Sum']";
        Node blockNode = null;
        try {
            blockNode = (Node) xpath.evaluate(blockType, doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        // 2- Get SID
        String sid = ((Element) blockNode).getAttribute("SID");
        this.sid = Double.parseDouble(sid);
        // System.out.println(sid); // Testing Line

        // 3- Get Name
        name = ((Element) blockNode).getAttribute("Name");
        // System.out.println(name); // Testing Line

        // 4 - Select the Position Tag inside Constant Block
        String expression = "//Block[@BlockType='Sum']/P[@Name='Position']"; // Change it with each block
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

    // Method To Draw The Shape and Text Below from Parent Class
    // and Inside Text which is related to this class only
    @Override
    public void drawBlock(Pane pane, double x_offset) {
        super.drawBlock(pane, x_offset);

        // Text Inside Rectangle
        Text constInText = new Text(CenterX + 4.5,
                CenterY + 10.5, "+");
        constInText.setFont(Font.font(12));
        Text constInText1 = new Text(CenterX + 4.5,
                CenterY + 20.5, "+");
        constInText.setFont(Font.font(12));
        Text constInText2 = new Text(CenterX + 4.5,
                CenterY + 30.5, "+");
        constInText.setFont(Font.font(12));
        System.out.println("X is : " + CenterX);
        constInText.setFont(Font.font(12));
        pane.getChildren().addAll(constInText, constInText1, constInText2);

    }
}
