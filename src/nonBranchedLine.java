package javafx_test;

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
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;


public class nonBranchedLine{

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
    protected boolean isbreak;

    /*

    <Line>
    <P Name="ZOrder">4</P>
    <P Name="Src">4#out:1</P>
    <P Name="Dst">3#in:3</P>
    <P Name="Points">[-8, 0; 0, -65]</P>
    </Line>

     */
    public nonBranchedLine(Document doc, Pane pane, Block[] arr, int LineIndex) {
        extractInfo(doc, arr, LineIndex);
        drawnonBranchedLine(pane, arr);

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
        System.out.println("sid start: " + sidStart);

        // 2- DST 1
        String expression2 = "//Line/P[@Name='Dst']"; // Change it with each block
        NodeList dstList = null;
        try {
            dstList = (NodeList) xpath.evaluate(expression2, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element dstElement = (Element) dstList.item((noCreatedLines));
        String dstText = dstElement.getTextContent();
        parts = dstText.split("#");
        number = parts[0];
        sidEnd1 = Double.parseDouble(number);
        System.out.println("sid end: " + sidEnd1);
        branchNoIn = Double.parseDouble(dstText.substring(dstText.indexOf(":") + 1));
        System.out.println(branchNoIn);

        
        for (int i = 0; i < arr.length; i++) {
            
            if (sidStart == arr[i].getSid()) {
                startX = arr[i].getCenterX();
                startY = arr[i].getCenterY();
            }
            if (sidEnd1 == arr[i].getSid()) {
                endX1 = arr[i].getCenterX();
                endY1 = arr[i].getCenterY();
            }
        }

        isbreak = false;

        if(startY != endY1){

            isbreak = true;

            // 4- Points
        String expression4 = "//Line/P[@Name='Points']"; // Change it with each block
        NodeList pointsList = null;
        try {
            pointsList = (NodeList) xpath.evaluate(expression4, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        Element pointsElement = (Element) pointsList.item(LineIndex- 1);
        String pointsText = pointsElement.getTextContent();

        pointsText = pointsText.replaceAll("\\[|\\]", ""); // Remove the square brackets
        String[] pointsArray = pointsText.split(";\\s*"); // Split the string into two points

        // Extract each coordinate from the first point
        String[] point1Array = pointsArray[0].split(",\\s*");
        breakP1X = Double.parseDouble(point1Array[0]); // -8
        breakP1Y = Double.parseDouble(point1Array[1]); // 0

        // Extract each coordinate from the second point
        String[] point2Array = pointsArray[1].split(",\\s*");
        breakP2X = Double.parseDouble(point2Array[0]); // 0
        breakP2Y = Double.parseDouble(point2Array[1]); // -65
        System.out.println(breakP1X);
        System.out.println(breakP1Y);
        System.out.println(breakP2X);
        System.out.println(breakP2Y);

        }

        
        noCreatedLines++;

    }


    public void drawnonBranchedLine(Pane pane, Block[] arr) {

        if(isbreak){
            /*
            start x -> top left
            breakP1X ->

            startY ->
            breakP1Y ->

            startX ->
            breakP1X ->

            startY ->
            breakP1Y ->
            breakP2Y ->
             */
            Lines.drawLineWithArrow(pane, startX + breakP1X - 10+ breakP2X, startY + breakP1Y + breakP2Y + 18, endX1 - 8.5, endY1 + 30*0.9);
            
            Lines.drawNormal(pane, startX , startY + 15, startX + breakP1X - 10,
            startY + 15);
            
            Lines.drawNormal(pane, startX + breakP1X - 10, startY + 15, startX + breakP1X + breakP2X - 10,
            startY + breakP1Y + breakP2Y + 18);
        }else{
            
            Lines.drawLineWithArrow(pane, startX + 30, startY + 15, endX1 - 5, endY1 + 15);
        }

    }


    // public void drawLineUnitDelayToAdd(Pane pane, Block[] arr ){


    //     if (sidStart == arr[4].sid){
    //     startX = arr[4].CenterX - arr[4].width;
    //     startY = arr[4].CenterY + 0.5 * (arr[4].height);
    //     }
        

    //     Line line_2 = new Line(startX, startY, startX+breakP1X, breakP1Y);

    //     // set the stroke color and width of the line
    //     line_2.setStroke(Color.CORNFLOWERBLUE);
    //     line_2.setStrokeWidth(2);
    //     line_2.setStrokeType(StrokeType.OUTSIDE);

    //     // create a line
    //     Line line_1 = new Line(startX, startY, startX+breakP1X, breakP1Y);
        
    //     // set the stroke color and width of the line
    //     line_1.setStroke(Color.BLACK);
    //     line_1.setStrokeWidth(1);

    //     pane.getChildren().addAll(line_1, line_2);
        
    //     /*****************************************************/

    //     Line line_4 = new Line(startX+breakP1X, startY, startX+breakP1X, startY+breakP1Y);

    //     // set the stroke color and width of the line
    //     line_4.setStroke(Color.CORNFLOWERBLUE);
    //     line_4.setStrokeWidth(2);
    //     line_4.setStrokeType(StrokeType.OUTSIDE);

    //     // create a line
    //     Line line_3 = new Line(startX+breakP1X, startY, startX+breakP1X, startY+breakP1Y);
        
    //     // set the stroke color and width of the line
    //     line_3.setStroke(Color.BLACK);
    //     line_3.setStrokeWidth(1);

    //     pane.getChildren().addAll(line_4, line_3);

    // }
    
}