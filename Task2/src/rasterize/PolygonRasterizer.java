package rasterize;

import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolygonRasterizer extends LineRasterizer{


    public PolygonRasterizer(Raster raster) {
        super(raster);
    }


    @Override
    public void drawPolygonPoints(List<Point> polygonPoint) {
        for (Point point: polygonPoint) {
            raster.setPixel(point.getX(), point.getY(), Color.GREEN.getRGB());
        }
    }

    @Override
    public void drawPolygonLines(List<Point> polygonPoint) {
        if (polygonPoint.size() > 2) {
            Polygon polygon = new Polygon(polygonPoint);
            List<Line> borders = polygon.getPolygonBorders(polygonPoint);
            for (Line border: borders) {
                rasterize(border);
            }
        } else {System.out.println("Nelze vykreslit, nejedna se o polygon!");}
//        if (polygonPoint.size()>2) {
//            Point tmpPoint = new Point();
//            Point firstPoint = new Point();
//            int listIndex = 0;
//            for (Point point : polygonPoint) {
//                listIndex++;
//                if (listIndex == 1) {
//                    firstPoint = new Point(point.getX(), point.getY());
//
//                } else {
//                    rasterize(new Line(tmpPoint, point, Color.GREEN.getRGB()));
//                    if (listIndex == polygonPoint.size()) {
//                        rasterize(new Line(firstPoint, point, Color.GREEN.getRGB()));
//                    }
//                }
//                tmpPoint = new Point(point.getX(), point.getY());
//
//            }
//        }
    }

    @Override
    public void rasterize(Line line) {
        TrivialLineRasterizer trivialLineRasterizer = new TrivialLineRasterizer(raster);
        trivialLineRasterizer.rasterize(line);
    }
}
