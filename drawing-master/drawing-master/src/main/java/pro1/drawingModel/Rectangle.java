package pro1.drawingModel;

import java.awt.*;

public class Rectangle extends WidthHeight {
    private String color;

    public Rectangle(int x, int y, int width, int height, String color) {
        super(x,y,width,height);
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode(this.color));
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}