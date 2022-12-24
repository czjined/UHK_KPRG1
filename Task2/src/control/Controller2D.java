package control;

import fill.ScanLine;
import fill.SeedFill;
import rasterize.*;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;
    private final Raster raster;

    private boolean polygonVykreslen = false;
    private int x,y;
    private model.Polygon polygon2d;
    LineRasterizer trivialLineRasterizer;
    LineRasterizer dashedLineRasterizer;
    LineRasterizer polygonRasterizer;
    SeedFill seedFiller;
    ScanLine scanLineFiller;
    private LineRasterizerGraphics rasterizer;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
//        rasterizer = new LineRasterizerGraphics(raster);
        trivialLineRasterizer = new TrivialLineRasterizer(raster);
        dashedLineRasterizer = new DashedLineRasterizer(raster);
        polygonRasterizer = new PolygonRasterizer(raster);
        polygon2d = new model.Polygon();
//        seedFiller = new SeedFill(raster);
     }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) {return;


                } else if (SwingUtilities.isLeftMouseButton(e)) {
//                    rasterizer.drawLine(x,y,e.getX(),e.getY());
                    x = e.getX();
                    y = e.getY();

                } else if (SwingUtilities.isMiddleMouseButton(e)) {

                } else if (SwingUtilities.isRightMouseButton(e)) {
                    x = e.getX();
                    y = e.getY();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    return;
                }
                if (e.isShiftDown()) {                              // Vybarvovani Seedem (dle pozadi)
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        seedFiller = new SeedFill(raster, e.getX(), e.getY(), Color.YELLOW.getRGB());
                        seedFiller.fill();
                    }
                } else {
                    if (SwingUtilities.isLeftMouseButton(e)) {      // Zadavam body polygonu
                        int x1 = e.getX();
                        int y1 = e.getY();
                        if (polygonVykreslen) {  // Zacinam zadavat body noveho polygonu
                            update();
//                            polygon2d.clearPoints();
                        }
                        model.Point clickedPoint = new model.Point(x1,y1);
                        raster.setPixel(x1, y1, Color.GREEN.getRGB());
                        polygon2d.pointToAdd(clickedPoint);
                        polygonVykreslen = false;


                    } else if (SwingUtilities.isRightMouseButton(e)) {      // Vykreslim polygon
                        raster.clear();
                        polygonRasterizer.drawPolygonLines(polygon2d.getPoints());
                        polygonVykreslen = true;

                    }
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    if (e.isControlDown()) {
                        return;
                    }

                    else if (e.isShiftDown()) {


                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        update();
                        trivialLineRasterizer.rasterize(x, y, e.getX(), e.getY(), Color.WHITE);
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        update();
                        dashedLineRasterizer.rasterize(x, y, e.getX(), e.getY(), Color.YELLOW);
                    } else if (SwingUtilities.isMiddleMouseButton(e)) {

                    }
                } catch (ArrayIndexOutOfBoundsException e1) {

                } finally {

                }


            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    update();
                }
                // na klávesu P vykreslit polygon (zelene)
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    raster.clear();
                    polygonRasterizer.drawPolygonLines(polygon2d.getPoints());
                    polygonVykreslen = true;
                }
                // na klávesu F vyplnit polygon metodou Scan Line (cervene)
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    scanLineFiller = new ScanLine(raster, polygon2d.getPoints());
                    scanLineFiller.fill();
                    polygonRasterizer.drawPolygonLines(polygon2d.getPoints());
                    polygonVykreslen = true;
                }
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void update() {
        panel.clear();
        polygon2d.clearPoints();

    }

    private void hardClear() {
        panel.clear();
    }

}
