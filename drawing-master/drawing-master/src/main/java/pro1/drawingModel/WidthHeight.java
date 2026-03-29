package pro1.drawingModel;

public abstract class WidthHeight extends XY {
    protected int width;
    protected int height;

    public WidthHeight(int x, int y,
                       int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
}
