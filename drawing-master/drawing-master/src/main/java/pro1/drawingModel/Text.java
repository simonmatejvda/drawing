package pro1.drawingModel;

import java.awt.*;

public class Text extends XY {
    private String text;

    public Text(int x, int y, String text) {
        super(x, y);
        this.text = text;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawString(this.text,this.x,this.y);
    }
}