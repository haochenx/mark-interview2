package name.haochenxie.tryout.mark_interview_question2.task1;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class BallWorldFrame extends JFrame {
	
	private static final long serialVersionUID = 3033089494450937975L;

	private WorldCanvas canvas;
	private MouseDragListener mouseDragListener;
	
	public BallWorldFrame() {
		mouseDragListener = new MouseDragListener();
		
		initUi();
		setVisible(true);
	}
	
	protected void initUi() {
		getContentPane().setLayout(new CardLayout());
		
		canvas = new WorldCanvas();
		getContentPane().add(canvas);
		
		addMouseListener(mouseDragListener);
		addMouseMotionListener(mouseDragListener);
		
		setSize(new Dimension(400, 400));
	}
	
	private void onMouseDragged(Point original, Point current) {
		Point offset = canvas.getOffset();
		canvas.setOffset(new Point(
				offset.x + (current.x - original.x),
				offset.y + (current.y - original.y)));
	}
	
	private class MouseDragListener extends MouseInputAdapter {
		public Point lastPosition;

		@Override
		public void mousePressed(MouseEvent e) {
			lastPosition = e.getPoint();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			Point currentPosition = e.getPoint();
			
			onMouseDragged(lastPosition, currentPosition);
			lastPosition = currentPosition;
		}
		
	}
	
	public static void main(String[] args) {
		BallWorldFrame frame = new BallWorldFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
