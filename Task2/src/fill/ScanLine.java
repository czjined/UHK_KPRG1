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
    private Polygon polygonToFill;


    public ScanLine(Raster raster, List<Point> vrcholyPolygonu) {
        this.raster = raster;
        this.vrcholyPolygonu = vrcholyPolygonu;

    }

    @Override
    public void fill() {

    }

    private void fillPolygon() {
        polygonToFill = new Polygon(vrcholyPolygonu);
        List<Line> lines = prepareLines(polygonToFill.getPolygonBorders(vrcholyPolygonu));

    }

    private List<Line> prepareLines(List<Line> lineList) {
        List<Line> preparedLines = new ArrayList<>();
        for (Line line: lineList) {
            if (line.getX1() != line.getX2()) {

            }
        }
        return lineList;
    }
}
