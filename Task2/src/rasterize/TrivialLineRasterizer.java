package rasterize;

import java.awt.*;

public class TrivialLineRasterizer extends LineRasterizer {

    public TrivialLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        int forStart = x1;
        int forEnd = x2;

        if (x2 - x1 != 0) {
            float k = (y2 - y1) / (float) (x2 - x1);
            float q = y1 - k * x1;
            if (Math.abs(k) <= 1) {
                if (x1 > x2) {
                    forStart = x2;
                    forEnd = x1;
                }
                for (int i = forStart; i < forEnd; i++) {
                    float y = i * k + q;
                    if ((i > 0 || i < raster.getWidth() - 1) && (Math.round(y) > 0 || Math.round(y) < raster.getHeight() - 1)) {
                        raster.setPixel(i, Math.round(y), color.getRGB());
                    }
                }
            } else {
                if (y1 > y2) {
                    forStart = y2;
                    forEnd = y1;
                } else {
                    forStart = y1;
                    forEnd = y2;
                }
                for (int i = forStart; i < forEnd; i++) {
                    float x = (i - q) / k;
                    if ((Math.round(x) > 0 || Math.round(x) < raster.getWidth() - 1) && (i > 0 || i < raster.getHeight() - 1)) {
                        raster.setPixel(Math.round(x), i, color.getRGB());
                    }
                }

            }


        } else {
            if (y2 > y1) {
                forStart = y1;
                forEnd = y2;
            } else {
                forStart = y2;
                forEnd = y1;
            }
            for (int i = forStart; i <= forEnd; i++) {
                if ((x1 > 0 || x1 < raster.getWidth() - 1) && (i > 0 || i < raster.getHeight() - 1)) {
                    raster.setPixel(x1, i, color.getRGB());
                }
            }
        }
    }
}
