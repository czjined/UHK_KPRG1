package fill;

import model.Point;
import rasterize.Raster;

import java.awt.*;

public class SeedFill implements Filler {

    private final Raster raster;
    private int seedX, seedY;
    private int fillColor;
    private int backgroundColor = Color.BLACK.getRGB();

    public SeedFill(Raster raster) {
        this.raster = raster;
    }

    public SeedFill(Raster raster, int seedX, int seedY) {
        this.raster = raster;
        this.seedX = seedX;
        this.seedY = seedY;
    }

    public SeedFill(Raster raster, int seedX, int seedY, int fillColor) {
        this.raster = raster;
        this.seedX = seedX;
        this.seedY = seedY;
        this.fillColor = fillColor;
    }


    @Override
    public void fill() {
        fillSeed(seedX, seedY);
    }

    private void fillSeed(int x, int y) {
        if (raster.getPixel(x, y) == backgroundColor) {
            raster.setPixel(x, y, fillColor);
            if (x > 1) {fillSeed(x-1, y);}                      // Ohraniceni rastrem
            if (x < raster.getWidth()-1) {fillSeed(x+1, y);}
            if (y < raster.getHeight()-1) {fillSeed(x, y+1);}
            if (y > 1) {fillSeed(x, y-1);}
        }
    }

    public void setSeed(Point seed) {
        seedX = seed.getX();
        seedY = seed.getY();
        backgroundColor = raster.getPixel(seedX, seedY);
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public int getSeedX() {
        return seedX;
    }

    public int getSeedY() {
        return seedY;
    }

    public int getFillColor() {
        return fillColor;
    }
}
