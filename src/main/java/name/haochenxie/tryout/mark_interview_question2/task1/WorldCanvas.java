package name.haochenxie.tryout.mark_interview_question2.task1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class WorldCanvas extends JPanel {
	
	private static final long serialVersionUID = -955003109057302468L;
	
	protected Point offset;
	
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
	protected void paintComponent(Graphics g) {
		
	}
	
	protected Rectangle getView() {
		// TODO not implemented
		return null;
	}
	
	protected void drawGrid(int start_x, int start_y, int x_count, int y_count, Graphics g) {
		// TODO not implemented
	}
	
	protected void drawCircle(Point pos, double radius, Color color, Graphics g) {
		// TODO not implemented
	}

	protected boolean isCircleVisible(Point pos, double radius, Graphics g) {
		// TODO not implemented
		return false;
	}
	
}
