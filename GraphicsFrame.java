import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class GraphicsFrame extends JFrame
{
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT= 1000;
	GraphicsPanel gp= new GraphicsPanel();
	
	/*
	 * Making the slider and the Text Field 
	 */
	JSlider slider= new JSlider(JSlider.HORIZONTAL,1,4,1);
	JTextField sliderVal= new JTextField("0");
	/*
	 * Making the Buttons
	 */
	JButton Show_Station= new JButton();
	JButton Calculate_HD= new JButton();
	JButton Add_Station= new JButton();
	/*
	 * Making the Labels
	 */
	JLabel Enter_Dist= new JLabel("Enter Hamming Dist:");
	JLabel Comp_With= new JLabel("Compare with:");
	JLabel Dist0= new JLabel("Distance 0");
	JLabel Dist1= new JLabel("Distance 1");
	JLabel Dist2= new JLabel("Distance 2");
	JLabel Dist3= new JLabel("Distance 3");
	JLabel Dist4= new JLabel("Distance 4");
	/*
	 * Making the Panels
	 */
	JPanel sliderPan= new JPanel();
	JPanel buttons= new JPanel();
	JPanel labels= new JPanel();
	
	public GraphicsFrame()
	{
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setLayout(new GridLayout(2,2));
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		
		sliderPan.add(Enter_Dist);
		sliderPan.add(sliderVal);
		sliderPan.add(slider);
		
		buttons.add(Show_Station);
		buttons.add(Calculate_HD);
		buttons.add(Add_Station);
		labels.add(Comp_With);
		labels.add(Dist0);
		
		this.add(sliderPan);
		this.add(buttons);
		this.add(labels);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//this.setupTextPoints();
		//this.addMouseListener(this);
	}
	
	public static void main(String[] args)
	{
		GraphicsFrame GP= new GraphicsFrame();
	}
	

}
