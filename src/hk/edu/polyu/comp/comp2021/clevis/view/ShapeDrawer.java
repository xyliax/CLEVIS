package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;

public class ShapeDrawer {
	private final JFrame jFrame;
	private float zoomBase;

	public ShapeDrawer(float bX, float bY, float bW, float bH) {
		jFrame = new JFrame();
		jFrame.setSize(1200, 800);
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void show() {
		jFrame.setVisible(true);
	}

	public void drawCircle(float x, float y, float r) {

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
