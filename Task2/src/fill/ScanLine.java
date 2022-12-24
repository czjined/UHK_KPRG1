package fill;

import model.Line;
import model.Point;
import model.Polygon;
import rasterize.Raster;
import rasterize.TrivialLineRasterizer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScanLine implements Filler {

    private final Raster raster;
    private final List<Point> vrcholyPolygonu;
    private List<Line> usecky;
    private Color fillColor = Color.RED;
    private final Polygon polygonToFill;
    private List<Point> pruseciky;
    int ymax = 0;
    int ymin;


    public ScanLine(Raster raster, List<Point> vrcholyPolygonu) {
        this.raster = raster;
        this.vrcholyPolygonu = vrcholyPolygonu;
        this.polygonToFill = new Polygon(vrcholyPolygonu);
        ymin = raster.getHeight();
    }

    @Override
    public void fill() {
        fillPolygon();
    }

    private void fillPolygon() {
          usecky = prepareLines(polygonToFill.getPolygonBorders(vrcholyPolygonu));
          getPruseciky(usecky);
          fillHorizontal(10);
          fillBorder();
    }


    private List<Line> prepareLines(List<Line> lineList) {
        List<Line> preparedLines = new ArrayList<>();
        for (Line line: lineList) {
            if (line.getY1() != line.getY2()) {                 // Neberu vodorovne hrany
                if (line.getY2() > line.getY1()) {              // Smerovani usecek dolu (Y2 > Y1)
                    preparedLines.add(new Line(line.getX1(), line.getY1(), line.getX2(), line.getY2()-1, fillColor.getRGB()));
                } else {
                    preparedLines.add(new Line(line.getX2(), line.getY2(), line.getX1(), line.getY1()-1, fillColor.getRGB()));
                }
                if (preparedLines.get(preparedLines.size()-1).getY2() > ymax) {ymax = preparedLines.get(preparedLines.size()-1).getY2();}
                if (preparedLines.get(preparedLines.size()-1).getY1() < ymin) {ymin = preparedLines.get(preparedLines.size()-1).getY1();}
            }
        }
        return preparedLines;
    }

    private List<Point> getPruseciky(List<Line> usecky) {
        pruseciky = new ArrayList<>();
        float k, q;
        for (Line usecka: usecky) {
            k = (usecka.getX2()-usecka.getX1())/(float)(usecka.getY2()-usecka.getY1());
            q = usecka.getX1() - k * usecka.getY1();
            for ( int i = usecka.getY1(); i <= usecka.getY2(); i++) {
                int prusX = Math.round( k * i + q);
                pruseciky.add(new Point(prusX, i));
            }
        }
        pruseciky.sort(Comparator.comparingInt(Point::getY));
        System.out.println("Vypisuji pruseciky:"+ pruseciky);
        return pruseciky;
    }

    private void fillHorizontal(int srafa) {
        int xFill1, xFill2;
        for (int i = ymin; i <= ymax; i += srafa) {
            for (int j = 0; j < pruseciky.size(); j += 2) {
                xFill1 = pruseciky.get(j).getX();
                xFill2 = pruseciky.get(j+1).getX();
                if ((pruseciky.get(j).getY() == i) && (pruseciky.get(j+1).getY() == i)) {
                    TrivialLineRasterizer horizontalRasterizer = new TrivialLineRasterizer(raster);
                    horizontalRasterizer.rasterize(xFill1, i, xFill2, i, fillColor);
                }
            }
        }

    }

    private void fillBorder() {
        TrivialLineRasterizer borderRasterizer = new TrivialLineRasterizer(raster);
        for (Line border: polygonToFill.getPolygonBorders(vrcholyPolygonu)) {
            borderRasterizer.rasterize(border.getX1(), border.getY1(), border.getX2(), border.getY2(), Color.GREEN);
        }

    }

}
