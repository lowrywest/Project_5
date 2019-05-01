import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class GraphicsPanel extends JFrame
{
	JSlider slider= new JSlider(1,4);
	JButton Show_Station= new JButton();
	JButton Calculate_HD= new JButton();
	JButton Add_Station= new JButton();
	JLabel Enter_Dist= new JLabel("Enter Hamming Dist:");
	JLabel Comp_With= new JLabel("Compare with:");
	JLabel Dist0= new JLabel("Distance 0");
	JLabel Dist1= new JLabel("Distance 1");
	JLabel Dist2= new JLabel("Distance 2");
	JLabel Dist3= new JLabel("Distance 3");
	JLabel Dist4= new JLabel("Distance 4");
	
	public GraphicsPanel()
	{
		this.setPreferredSize(new Dimension(1000, 1000));
	}

}
