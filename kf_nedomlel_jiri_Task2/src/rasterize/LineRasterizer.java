package rasterize;

import model.Line;
import model.Point;

import java.awt.*;
import java.util.List;

public abstract class LineRasterizer {
    Raster raster;
    Color color;

    public LineRasterizer(Raster raster){
        this.raster = raster;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int color) {
        this.color = new Color(color);
    }

    public void rasterize(Line line) {
        setColor(line.getColor());
        rasterize(line.getX1(), line.getY1(), line.getX2(), line.getY2(), color);
    }

    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        //TODO
    }

    protected void drawLine(int x1, int y1, int x2, int y2) {

    }

//    public void drawPolygonPoints() {
//        drawPolygonPoints();
//    }

    public void drawPolygonPoints(List<Point> polygonPoint) {
        
    }

    public void drawPolygonLines(List<Point> polygonPoint) {

    }
    
}
