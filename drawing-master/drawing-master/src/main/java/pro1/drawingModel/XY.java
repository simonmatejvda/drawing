package pro1.drawingModel;

public abstract class XY implements Drawable {
    protected int x;
    protected int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
