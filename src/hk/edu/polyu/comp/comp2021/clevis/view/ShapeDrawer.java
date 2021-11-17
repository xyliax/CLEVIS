package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;

public class ShapeDrawer {
	private final JFrame jFrame;

	public ShapeDrawer() {
		jFrame = new JFrame();
		jFrame.setSize(1200, 800);
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public void drawCircle() {
		float boundingboxreturn = 60000;

		int boundingboxH = 60000;
		int boundingboxW = 55000;
		int boundingboxX = 340;
		int boundingboxY = 560;
		int ZoomBase = boundingboxH;
		do {
			ZoomBase /= 10;
		} while (ZoomBase > 800);

		if (boundingboxreturn < 50) {
			do {
				ZoomBase *= 10;
			} while (ZoomBase < 12);
		}
		System.out.print(ZoomBase);
		int x1 = 50;
		int y1 = 20;
		int x2 = 20;
		int y2 = 10;


		int circle_radium = 60000;
		int a = 10000;
		int b = 8000;

		int finalZoomBase = ZoomBase;
		JPanel jpanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics graphics) {
				super.paint(graphics);
				graphics.drawOval((x1 - boundingboxX + circle_radium) / finalZoomBase, (boundingboxY - y1 + circle_radium) / finalZoomBase, (2 * circle_radium) / finalZoomBase, (2 * circle_radium) / finalZoomBase);
				graphics.drawRect((x2 - boundingboxX) / finalZoomBase, (y2 - boundingboxY) / finalZoomBase, a / finalZoomBase, b / finalZoomBase);
			}
		};

		getjFrame().add(jpanel);
		getjFrame().setVisible(true);
	}

	/**
	 *
	 */
	public Frame getjFrame() {
		return jFrame;
	}


	public void drawLineSegment() {

	}
}
