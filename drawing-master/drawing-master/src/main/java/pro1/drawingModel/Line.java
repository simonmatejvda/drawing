package pro1.drawingModel;

import java.awt.*;

public class Line extends XY {
    private int endX;
    private int endY;
    private int thickness;
    private String color;

    public Line(int x, int y, int endX, int endY, int thickness, String color) {
        super(x,y);
        this.color = color;
        this.thickness = thickness;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(this.thickness));
        g.setColor(Color.decode(this.color));
        g.drawLine(this.x,this.y,this.endX,this.endY);
    }
}