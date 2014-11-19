package name.haochenxie.tryout.mark_interview_question2.task1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class WorldCanvas extends JPanel {

	private static final int GRID_SIZE = 40;
	private static final int BOX_SIZE = 40;

	private static final long serialVersionUID = -955003109057302468L;
	private static final Dimension PREFERED_SIZE = new Dimension(400, 400);

	/**
	 * the coordinate of the point at the view's top left should be on the
	 * canvas
	 */
	private Point offset;

	public WorldCanvas(int offset_x, int offset_y) {
		this.offset = new Point(offset_x, offset_y);
	}

	public WorldCanvas() {
		this(0, 0);
	}

	public Point getOffset() {
		return offset;
	}

	public void setOffset(Point offset) {
		this.offset = offset;
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	@Override
	protected void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		// calculate grid parameters and draw it
		{
			int start_x = - offset.x % GRID_SIZE;
			int start_y = - offset.y % GRID_SIZE;

			int x_count = w / GRID_SIZE + 1;
			int y_count = h / GRID_SIZE + 1;

			drawGrid(start_x, start_y, x_count, y_count, g);
		}

		// check whether the boxes are visible, and draw if visible
		{
			Color[] colors = new Color[] { Color.BLUE, Color.RED, Color.ORANGE,
					Color.GREEN };
			Point[] centers = new Point[] {
					new Point(4 * GRID_SIZE, 4 * GRID_SIZE),
					new Point(6 * GRID_SIZE, 4 * GRID_SIZE),
					new Point(4 * GRID_SIZE, 6 * GRID_SIZE),
					new Point(6 * GRID_SIZE, 6 * GRID_SIZE) };
			for (int i = 0; i < colors.length; ++i) {
				Color color = colors[i];
				Point center = centers[i];

				if (isBoxVisible(center, BOX_SIZE, g))
					drawBox(center, BOX_SIZE, color, g);
			}
		}
	}

	protected Rectangle getView() {
		return new Rectangle(offset.x, offset.y, getWidth(), getHeight());
	}

	/**
	 *
	 * @param start_x
	 *            start x on the VIEW (not the canvas)
	 * @param start_y
	 *            start y on the VIEW (not the canvas)
	 * @param x_count
	 *            line count on the x orientation
	 * @param y_count
	 *            line count on the y orientation
	 * @param g
	 *            the graphics handler
	 */
	protected void drawGrid(int start_x, int start_y, int x_count, int y_count,
			Graphics g) {
		int w = getWidth();
		int h = getHeight();
		Color oldColor = g.getColor();

		g.setColor(Color.GRAY);

		for (int ix = 0; ix < x_count; ++ix) {
			int x = ix * GRID_SIZE + start_x;
			g.drawLine(x, 0, x, h - 1);
		}
		for (int iy = 0; iy < y_count; ++iy) {
			int y = iy * GRID_SIZE + start_y;
			g.drawLine(0, y, w - 1, y);

		}

		g.setColor(oldColor);
	}

	/**
	 *
	 * @param center
	 *            center of the box on the CANVAS (not the view)
	 * @param edge_length
	 *            edge length
	 * @param color
	 *            fill color
	 * @param g
	 *            the graphics handler
	 */
	protected void drawBox(Point center, int edge_length, Color color,
			Graphics g) {
		Color oldColor = g.getColor();
		
		// calculate the top-left corner on the view
		int x = center.x - offset.x - BOX_SIZE / 2;
		int y = center.y - offset.y - BOX_SIZE / 2;

		g.setColor(color);
		g.fillRect(x, y, BOX_SIZE, BOX_SIZE);
		
		g.setColor(oldColor);
	}

	protected boolean isBoxVisible(Point center, int edge_length, Graphics g) {
		Rectangle view = getView();
		
		// calculate the top-left and bottom-right point
		Point tl = new Point(center.x - BOX_SIZE / 2, center.y - BOX_SIZE / 2);
		Point br = new Point(tl.x + BOX_SIZE, tl.y + BOX_SIZE);
		
		return view.contains(tl) || view.contains(br);
	}

}
