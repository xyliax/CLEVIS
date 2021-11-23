package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import javax.swing.*;
import java.awt.*;

/**
 * The GUI class for Clevis.
 */
public class GraphDrawer {

	/**
	 * The width.
	 */
	public static final int F_WIDTH = 800;

	/**
	 * The height.
	 */
	public static final int F_HEIGHT = 600;
	private final JFrame jFrame;
	private final JPanel jPanel;
	private float zoomBase;

	/**
	 * Constructor of GraphDrawer.
	 */
	public GraphDrawer() {
		jFrame = new JFrame();
		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.ORANGE);
		jFrame.setSize(F_WIDTH, F_HEIGHT);
		jFrame.add(jPanel);
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * Set the graph to be visible.
	 */
	public void show() {
		jFrame.setVisible(true);
	}

	/**
	 * Close the graph for refreshing.
	 */
	public void close() {
		jFrame.setVisible(false);
	}


	/**
	 * Puts a new image in the graph.
	 *
	 * @param panel a new image
	 */
	public void draw(JPanel panel) {
		panel.setSize(F_WIDTH, F_HEIGHT);
		jPanel.add(panel);
	}

	/**
	 * Getter of zoomBase.
	 *
	 * @return zoomBase
	 * @see #zoomBase
	 */
	public float getZoomBase() {
		return zoomBase;
	}

	/**
	 * Setter of zoomBase.
	 *
	 * @param bW bounding width
	 * @param bH bounding height
	 * @see #zoomBase
	 */
	public void setZoomBase(float bW, float bH) {
		float k;
		if (bW > bH)
			k = bW / F_WIDTH * 2;
		else
			k = bH / F_HEIGHT * 2;
		zoomBase = k;
	}
}