
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class GraphicsPanel extends JPanel implements MouseListener
{

	/**
	 * Width and height for the panel. Width matches the enclosing frame.
	 */
	private static final int PANEL_WIDTH = 20;
	private static final int PANEL_HEIGHT = 20;

	/**
	 * Size of the regions.
	 */
	private static final int REGION_WIDTH = 50;
	private static final int REGION_HEIGHT = 50;

	/**
	 * Define top-left corner of first region and x increment between corners.
	 */
	private static final int REGION_START_X = 50;
	private static final int REGION_START_Y = 50;
	private static final int REGION_INC_X = 60;

	public GraphicsPanel()
	{
		this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}

