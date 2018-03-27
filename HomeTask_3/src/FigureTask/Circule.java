package FigureTask;

public class Circule implements Figure {
    private double r;
    private double x1, x2;
    private double y1, y2;
    private final String name = "Triangle";

    public Circule(double r) {
        this.r = Math.abs(r);
    }

    public Circule(Point firstPoint, Point secondPoint) {
        this.x1 = firstPoint.getY();
        this.x2 = secondPoint.getX();
        this.y1 = firstPoint.getY();
        this.y2 = secondPoint.getY();

        this.r = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public Circule(Point [] points)  {
        this(points[0], points[1]);
    }

    @Override
    public double calcSquare() {
        return (double) (Math.PI * r * r);
    }

    @Override
    public double calcPerimeter() {
        return (double) (2 * Math.PI * r);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(name)
                .append(" perimeter:" )
                .append(calcPerimeter())
                .append(" square:")
                .append(calcSquare());
        return sb.toString();
    }
}
