package fill;

import model.Line;
import model.Point;
import model.Polygon;
import rasterize.Raster;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScanLine implements Filler {

    private final Raster raster;
    private final List<Point> vrcholyPolygonu;
    private int fillColor = Color.RED.getRGB();
    private final Polygon polygonToFill;
    int ymin, ymax;


    public ScanLine(Raster raster, List<Point> vrcholyPolygonu) {
        this.raster = raster;
        this.vrcholyPolygonu = vrcholyPolygonu;
        this.polygonToFill = new Polygon(vrcholyPolygonu);
    }

    @Override
    public void fill() {
        fillPolygon();
    }

    private void fillPolygon() {
//        polygonToFill = new Polygon(vrcholyPolygonu);
        List<Line> lines = prepareLines(polygonToFill.getPolygonBorders(vrcholyPolygonu));

    }

    /**
     * Vnitrni trida pro vytvoreni usecek z hran polygonu
     */

    private class Usecka extends Line {

        public Usecka(int x1, int y1, int x2, int y2, int color) {
            super(x1, y1, x2, y2, color);
        }

        // TODO - implementovat metody dle Slide 43 v Olive
    }



    private List<Line> prepareLines(List<Line> lineList) {
        List<Line> preparedLines = new ArrayList<>();
        for (Line line: lineList) {
            if (line.getY1() != line.getY2()) {                 // Neberu vodorovne hrany
                if (line.getY2() > line.getY1()) {              // Smerovani usecek dolu (Y2 > Y1)
                    preparedLines.add(new Line(line.getX1(), line.getY1(), line.getX2(), line.getY2()-1, fillColor));
                } else {
                    preparedLines.add(new Line(line.getX2(), line.getY2(), line.getX1(), line.getY1()-1, fillColor));
                }
            }
        }
        return preparedLines;
    }
}
