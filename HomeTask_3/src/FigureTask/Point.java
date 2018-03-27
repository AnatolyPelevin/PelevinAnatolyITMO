package FigureTask;

public class Point {
    private double x;
    private double y;
    private final String name = "Point";

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point (double [] coordinates){
        if (coordinates ==null || coordinates.length < 2) {
            System.out.println("No coordinates!");
            throw new ArithmeticException("No coordinates!");
        }
        this.x = coordinates[0];
        this.y = coordinates[1];
    }

    private double calcLengthByCoordinat(Point secondPoint) throws Exception {
        if (secondPoint ==null) {
            System.out.println("No second point!");
            throw new Exception("No second point!");
        }

         double x2 = secondPoint.getX();
         double y2 = secondPoint.getY();
         return Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(name)
                .append(" x = " )
                .append(x)
                .append(" y = ")
                .append(y);
        return sb.toString();
    }

}
