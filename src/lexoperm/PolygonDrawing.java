/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexoperm;

/**
 *
 * @author Owner
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;




public final class PolygonDrawing extends JFrame {
    
        static int sides = 12;
        static int radius = 160;
        static int startingX = 250;
        static int startingY = 250;
        static Double [] xarray = new Double [sides];
        static Double [] yarray = new Double [sides];
        static Integer [] permvector = new Integer [sides];
        final static BasicStroke dashed =new BasicStroke(4.0f);

	public PolygonDrawing(int [] a) {
            super("Polygon Drawing");
            setSize(500, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setUndecorated(true);
            //setExtendedState(MAXIMIZED_BOTH);
            Color c=new Color(0f,0f,0f,0f );
            setBackground(c);
            setLocationRelativeTo(null);
            setupPolygon(a);

	}
        
        void setupPolygon(int [] a) {
            System.out.println("I entered setupPolygon");
            //Angles Array
            //Divide 360 degree circle by sides

            Double theta = 2*Math.PI/sides;
            Double [] angles = new Double[sides];
            Double arc = 0.00;
            for ( int i = 0; i < sides; i++) {
                angles[i] = arc;
                arc = theta + arc;
            }
            for ( int i = 0; i < sides; i++) {
                permvector[i] = a[i];
            }
            for ( int i = 0; i < sides; i++) {
                Double x = (Math.cos(angles[i])*radius) + startingX;
                Double y = (Math.sin(angles[i])*radius) + startingY;
                xarray[i] = x;
                yarray[i] = y;
            }  
        }

	void drawLines(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(dashed);
            System.out.println("I entered drawLines");
            for (int point = 0; point < sides; point++) {
		int mysub = permvector[point];
		int nextsub;
		if (point + 1 < sides) {
			nextsub = permvector[point + 1];
		}
		else {
			nextsub = permvector[0];
			}
		Double xco = xarray[mysub];
		Double yco = yarray[mysub];
		Double nxco = xarray[nextsub];
		Double nyco = yarray[nextsub];
                nyco = (double)Math.round(nyco*100)/100;
                nxco = (double)Math.round(nxco*100)/100;
                yco = (double)Math.round(yco*100)/100;
                xco = (double)Math.round(xco*100)/100; 
		System.out.println("sub = " + mysub + " nextsub " + nextsub);
		System.out.println("xco = " + xco + " yco = " + yco + " nxco = " + nxco + " nyco = " + nyco);
		g2d.draw(new Line2D.Double(xco, yco, nxco, nyco));
            }

	}

        @Override
	public void paint(Graphics g) {
		super.paint(g);
                g.setColor(Color.red);
                drawLines(g);
		
	}
        
//        public static void main(String[] args) {
//            SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        int [] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
//                        PolygonDrawing myp = new PolygonDrawing(a);
//                        myp.setVisible(true);
//                        myp.dispose();
//                    }
//            });
//	}
}
