package FigureTask;

 public class Rectangle implements Figure{
     private double a,b;
     private final String name = "Rectangle";

    public Rectangle (double a, double b){
        this.a = Math.abs(a);
        this.b = Math.abs(b);
    }
    @Override
    public double calcSquare() {
        return a * b;
    }

    @Override
    public double calcPerimeter() {
        return 2*(a + b);
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
