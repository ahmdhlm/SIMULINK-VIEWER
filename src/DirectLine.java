// import org.w3c.dom.*;

// import javafx.scene.Group;
// import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;

// import javax.xml.xpath.XPath;
// import javax.xml.xpath.XPathConstants;
// import javax.xml.xpath.XPathExpressionException;
// import javax.xml.xpath.XPathFactory;
// import org.w3c.dom.Document;
// import org.w3c.dom.Element;
// import org.w3c.dom.NodeList;
// import javafx.scene.text.Font;
// import javafx.scene.text.Text;
// import javafx.scene.shape.Line;
// import javafx.scene.shape.Polygon;
// import javafx.scene.shape.StrokeType;
// import java.io.File;
// import javax.xml.parsers.DocumentBuilder;
// import javax.xml.parsers.DocumentBuilderFactory;
// import org.w3c.dom.Document;
// import org.w3c.dom.Element;
// import org.w3c.dom.Node;
// import org.w3c.dom.NodeList;

// public class DirectLine extends Lines {
//     protected double sidStart;
//     protected double sidEnd;

//     public void extractInfo(Document doc) {

//         XPath xpath = XPathFactory.newInstance().newXPath();

//         NodeList lines = doc.getElementsByTagName("Line");
//         Element line = (Element) lines.item(noCreatedLines++);

//         ///////////////////////////
//         String expression2 = "//Line/P[@Name='Src']";
//         NodeList positionList = null;
//         try {
//             positionList = (NodeList) xpath.evaluate(expression2, doc, XPathConstants.NODESET);
//         } catch (XPathExpressionException e) {
//             e.printStackTrace();
//         }
//         // 5 - Extract the position data from the selected P element
//         Element positionElement = (Element) positionList.item(0);
//         String srcValue = positionElement.getTextContent();
//         String[] parts = srcValue.split("#");
//         String number = parts[0];
//         sidStart = Double.parseDouble(number);

//         String expression = "//Line/P[@Name='Dst']";
//         positionList = null;
//         try {
//             positionList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
//         } catch (XPathExpressionException e) {
//             e.printStackTrace();
//         }

//         // 5 - Extract the position data from the selected P element
//         positionElement = (Element) positionList.item(0);
//         String dstValue = pointElement.getTextContent();
//         parts = srcValue.split("#");
//         String number = parts[0];
//         sidStart = Double.parseDouble(number);

//     }

//     public void drawLine(Pane pane, Block[] arr) {
//         for (int i = 0; i < arr.length; i++) {
//             if (sidStart == arr[i].sid) {
//                 startX = arr[i].CenterX + arr[i].width;
//                 startY = arr[i].CenterY + 0.5 * (arr[i].height);
//             }
//             if (sidEnd1 == arr[i].sid) {
//                 endX = arr[i].CenterX - 10;
//                 endY = arr[i].CenterY + 0.5 * (arr[i].height);
//             }
//         }

//     }
// }