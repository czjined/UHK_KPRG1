package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private final List<Point> points;
    private final List<Line> polygonBorders = new ArrayList<>();
    private final int polygonPerimeterColor;

    public Polygon() {
        this(new ArrayList<>());
    }

    public Polygon(int polygonPerimeterColor) {
        this(new ArrayList<>(), polygonPerimeterColor);
    }

    public Polygon(List<Point> points) {
        this(points, Color.MAGENTA.getRGB());
    }

    public Polygon(List<Point> points, int polygonPerimeterColor) {
        this.points = points;
        this.polygonPerimeterColor = polygonPerimeterColor;
    }


    public List<Point> getPoints() {
        return points;
    }

    public List<Line> getPolygonBorders(List<Point> polygonPoints) {
        if (polygonPoints.size()>2) {
            Point tmpPoint = new Point();
            Point firstPoint = new Point();
            int listIndex = 0;
            for (Point point : polygonPoints) {
                listIndex++;
                if (listIndex == 1) {
                    firstPoint = new Point(point.getX(), point.getY());

                } else {
                    polygonBorders.add(new Line(tmpPoint, point, Color.GREEN.getRGB()));
                    if (listIndex == polygonPoints.size()) {
                        polygonBorders.add(new Line(point, firstPoint, Color.GREEN.getRGB()));
                    }
                }
                tmpPoint = new Point(point.getX(), point.getY());
            }
//            return polygonBorders;
        } else {System.out.println("Malo vrcholovych bodu, nejedna se o polygon!");}
        return polygonBorders;
    }

    public int getPolygonPerimeterColor() {
        return polygonPerimeterColor;
    }

    public void pointToAdd(Point point) {
        points.add(point);
    }

    public void pointsToAdd(List<Point> pointsList) {
        points.addAll(pointsList);
    }

    public void clearPoints() {
        points.clear();
    }
}
