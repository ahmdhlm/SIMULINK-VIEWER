import org.w3c.dom.Document;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

abstract public class Block {
    protected double CenterX;
    protected double CenterY;
    protected double width;
    protected double height;
    protected double sid;
    protected String name;
    protected double belowTextOffset;

    abstract public void extractInfo(Document doc);

    public void drawBlock(Pane pane, double x_offset) {
        // 1- Rectangle
        Rectangle Scope_Rect_2 = new Rectangle(CenterX, CenterY, width, height);
        Scope_Rect_2.setFill(null);
        Scope_Rect_2.setStroke(Color.CORNFLOWERBLUE);
        Scope_Rect_2.setStrokeType(StrokeType.OUTSIDE);
        Scope_Rect_2.setStrokeWidth(3);
        pane.getChildren().add(Scope_Rect_2);

        Rectangle blockRect = new Rectangle(CenterX, CenterY, width, height);
        blockRect.setFill(null);
        blockRect.setStroke(Color.BLUE);
        blockRect.setStrokeWidth(2);
        pane.getChildren().add(blockRect);

        // 2- Text Below The Rectangle
        System.out.println("Offset is:" + x_offset);
        Text belowText = new Text(CenterX + x_offset, (CenterY + height + height / 2), name);
        belowText.setFont(Font.font(12));
        belowText.setStroke(Color.BLUE);
        pane.getChildren().add(belowText);

    }

    public double getBelowTextOffset() {
        return belowTextOffset;
    }

    public double getSid() {
        return sid;
    }

    public double getCenterX() {
        return CenterX;
    }

    public double getCenterY() {
        return CenterY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
