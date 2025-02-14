import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.concurrent.TimeUnit;

import gpdraw.*; // for DrawingTool


public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // Constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        double perimeter = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size()); // Loop to the first point
            perimeter += current.distance(next);
        }
        return perimeter;
    }

    public double area() {
        double sum1 = 0.0;
        double sum2 = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size());
            sum1 += current.x * next.y;
            sum2 += current.y * next.x;
        }
        return Math.abs(0.5 * (sum1 - sum2));
        
    }

    public void draw()
    {
        try {
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            if (myPolygon.size() > 0) {
                Point2D.Double first = myPolygon.get(0);
                pen.up();
                pen.move(first.x, first.y);
                pen.down();

                for (int i = 1; i < myPolygon.size(); i++) {
                    Point2D.Double point = myPolygon.get(i);
                    pen.move(point.x, point.y);
                }

                pen.move(first.x, first.y); // Close the polygon
            }
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
