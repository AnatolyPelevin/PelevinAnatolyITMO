package FigureTask;

import java.util.ArrayList;
import java.util.List;

public class TestFigure {

    public static void testFigure(){
        System.out.println("****Start test figure.****");
        List<Figure> figures = new ArrayList<Figure>();
        figures. add(new Circule(5.23));
        figures.add(new Rectangle(6.2, 1.41));
        figures.add(new Triangle(3.42, 8.48, 9.45));

        for (Figure f : figures)
            System.out.println(f);
    }
}
