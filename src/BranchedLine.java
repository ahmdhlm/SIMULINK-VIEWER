import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javafx.scene.shape.Circle;

public class BranchedLine {
    protected double sidStart;
    protected double sidEnd1;
    protected double sidEnd2;
    protected double startX;
    protected double startY;
    protected double endX1;
    protected double endY1;
    protected double endX2;
    protected double endY2;
    protected double breakP1X;
    protected double breakP1Y;
    protected double breakP2X;
    protected double breakP2Y;
    protected double branchNoIn;
    static int noCreatedLines;

    public BranchedLine(Document doc, Pane pane, Block[] arr, int LineIndex) {
        extractInfo(doc, arr, LineIndex);
        drawBranchedLine(pane, arr);

    }

    public void extractInfo(Document doc, Block[] arr, int LineIndex) {
        XPath xpath = XPathFactory.newInstance().newXPath();

        // 1- SRC
        String expression = "//Line/P[@Name='Src']"; // Change it with each block
        NodeList srcList = null;
        try {
            srcList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element srcElement = (Element) srcList.item(LineIndex);
        String srcText = srcElement.getTextContent();
        String[] parts = srcText.split("#");
        String number = parts[0];
        sidStart = Double.parseDouble(number);

        // 2- DST 1
        String expression2 = "//Line/Branch/P[@Name='Dst']"; // Change it with each block
        NodeList dstList = null;
        try {
            dstList = (NodeList) xpath.evaluate(expression2, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element dstElement = (Element) dstList.item((2 * noCreatedLines));
        String dstText = dstElement.getTextContent();
        parts = dstText.split("#");
        number = parts[0];
        sidEnd1 = Double.parseDouble(number);
        System.out.println("sid: " + sidEnd1);
        branchNoIn = Double.parseDouble(dstText.substring(dstText.indexOf(":") + 1));
        System.out.println(branchNoIn);

        // 3- Dst 2
        String expression3 = "//Line/Branch/P[@Name='Dst']"; // Change it with each block
        NodeList dstList2 = null;
        try {
            dstList2 = (NodeList) xpath.evaluate(expression3, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element dstElement2 = (Element) dstList2.item(2 * noCreatedLines + 1);
        String dstText2 = dstElement2.getTextContent();
        sidEnd2 = Double.parseDouble(dstText2.substring(0, dstText2.indexOf("#")));
        // Extract the second double value after the ":" character

        // 4- Points
        String expression4 = "//Line/P[@Name='Points']"; // Change it with each block
        NodeList pointsList = null;
        try {
            pointsList = (NodeList) xpath.evaluate(expression4, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element pointElement = (Element) pointsList.item(LineIndex - 1);
        String pointsText = pointElement.getTextContent();
        String[] valueArr = pointsText.substring(1, pointsText.length() - 1).split(",");
        int[] value = new int[valueArr.length];
        for (int j = 0; j < valueArr.length; j++) {
            value[j] = Integer.parseInt(valueArr[j].trim());
        }
        breakP1X = value[0];
        breakP1Y = value[1];

        // 5- break in branch
        String expression5 = "//Line/Branch/P[@Name='Points']"; // Change it with each block
        NodeList pointsList2 = null;
        try {
            pointsList2 = (NodeList) xpath.evaluate(expression5, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element point2Element = (Element) pointsList2.item(noCreatedLines);
        String points2Text = point2Element.getTextContent();
        valueArr = points2Text.substring(1, pointsText.length() - 1).split(",");
        value = new int[valueArr.length];
        for (int j = 0; j < valueArr.length; j++) {
            value[j] = Integer.parseInt(valueArr[j].trim());
        }
        breakP2X = value[0];
        System.out.println("b2" + breakP2X);
        breakP2Y = value[1];
        System.out.println("b2" + breakP2Y);

        for (int i = 0; i < arr.length; i++) {
            if (sidStart == arr[i].getSid()) {
                startX = arr[i].getCenterX() + arr[i].getWidth();
                startY = arr[i].getCenterY() + 0.5 * (arr[i].getHeight());
            }
            if (branchNoIn != 2 && sidEnd1 == arr[i].getSid()) {
                endX1 = arr[i].getCenterX() + arr[i].getWidth();
                endY1 = arr[i].getCenterY() + 0.5 * (arr[i].height);
            } else if (sidEnd1 == arr[i].getSid()) {
                endX1 = arr[i].getCenterX() - 20;
                endY1 = arr[i].getCenterY() + 0.5 * (arr[i].height);
            }

            if (branchNoIn != 2 && sidEnd2 == arr[i].getSid()) {
                endX2 = arr[i].getCenterX() - 10;
                endY2 = arr[i].getCenterY() + 0.5 * arr[i].getHeight();
            } else if (sidEnd2 == arr[i].getSid()) {
                endX2 = arr[i].getCenterX() - 10;
                endY2 = arr[i].getCenterY() + 0.5 * (arr[i].getHeight()) - 10;
                System.out.println(endY2);
            }
        }
        noCreatedLines++;
    }

    public void drawBranchedLine(Pane pane, Block[] arr) {

        Lines.drawLineWithArrow(pane, startX, startY, endX2, endY2);

        Lines.drawLineWithArrow(pane, startX + breakP1X + breakP2X, startY + breakP1Y + breakP2Y, endX1 + 10, endY1);
        Lines.drawNormal(pane, startX + breakP1X, startY + breakP1Y, startX + breakP1X + breakP2X,
                startY + breakP1Y + breakP2Y);
        Circle c = new Circle(startX + breakP1X, startY + breakP1Y, 4);
        c.setFill(Color.BLACK);
        pane.getChildren().add(c);
    }
}