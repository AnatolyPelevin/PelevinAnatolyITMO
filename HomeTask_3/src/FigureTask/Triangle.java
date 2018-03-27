package FigureTask;

public class Triangle implements Figure {
    private double a, b, c;
    private double x1, x2, x3;
    private double y1, y2, y3;
    private final String name = "Triangle";

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(Point firstPoint, Point secondPoint, Point thirdPoint) {
         this.x1 = firstPoint.getY();
         this.x2 = secondPoint.getX();
         this.x3 = thirdPoint.getX();
         this.y1 = firstPoint.getY();
         this.y2 = secondPoint.getY();
         this.y3 = thirdPoint.getY();

        this.a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        this.b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
        this.c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
    }

    public Triangle(Point [] points) {
        this(points[0], points[1], points[2]);
    }

    @Override
    public double calcSquare() {
        if (checkExistTriangle())
        {
            double p = calcPerimeter() / 2;
            return (float) Math.sqrt(p * (p - a) * (p - b) * (p - c));
        } else {
            return -1;
        }
    }

    @Override
    public double calcPerimeter() {
        if (checkExistTriangle()) {
            return a + b + c;
        } else {
            return -1;
        }
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

    private boolean checkExistTriangle() {
        if (a + b <= c || a + c <= b || a + c <= b) {
            System.out.println("Triangle does not exists.");
            return false;
        } else {
            return true;
        }
    }
}
