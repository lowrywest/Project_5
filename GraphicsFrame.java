import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class GraphicsFrame extends JFrame 
{
	ArrayList<String> STIDs= new ArrayList<String>();
	
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT= 1000;
	GraphicsPanel gp= new GraphicsPanel();
	
	/*
	 * Making the slider and the Text Field 
	 */
	JSlider slider= new JSlider(JSlider.HORIZONTAL,1,4,1);
	JTextField sliderVal= new JTextField(""+slider.getValue(),10);
	JLabel Enter_Dist= new JLabel("Enter Hamming Dist:");
	/*
	 * Making the Buttons
	 */
	JButton Show_Station= new JButton("Show Station");
	JTextArea stationShown= new JTextArea(20,20);
	
	JButton Add_Station= new JButton("Add Station");
	JTextField addStationField= new JTextField(5);
	/*
	 * Making the Labels
	 */
	JLabel Comp_With= new JLabel("Compare with:");
	@SuppressWarnings("rawtypes")
	JComboBox<String> STIDvals = new JComboBox();
	
	JButton calcHD= new JButton("Calculate HD");
	JLabel Dist0= new JLabel("Distance 0");
	JLabel Dist1= new JLabel("Distance 1");
	JLabel Dist2= new JLabel("Distance 2");
	JLabel Dist3= new JLabel("Distance 3");
	JLabel Dist4= new JLabel("Distance 4");
	JTextField dist0Field= new JTextField(5);
	JTextField dist1Field= new JTextField(5);
	JTextField dist2Field= new JTextField(5);
	JTextField dist3Field= new JTextField(5);
	JTextField dist4Field= new JTextField(5);
	/*
	 * Making the Panels
	 */
	JPanel sliderPan= new JPanel();
	//GroupLayout sliderLayout=new GroupLayout(sliderPan);
	
	JPanel showStationPan= new JPanel();
	//GroupLayout showStationLayout= new GroupLayout(showStationPan);
	
	JPanel compWithPan= new JPanel();
	//GroupLayout compWithLayout= new GroupLayout(compWithPan);
	
	JPanel hammingDistPan= new JPanel();
	//GroupLayout hammingDistLayout= new GroupLayout(hammingDistPan);
	
	JPanel addStationPan= new JPanel();
	//GroupLayout addStationLayout= new GroupLayout(addStationPan);
	
	JPanel globalPanel= new JPanel();
	//GroupLayout globalLayout= new GroupLayout(globalPanel);
	public GraphicsFrame() throws IOException
	{
		STIDs=readInfo();
		for (int i=0; i< STIDs.size(); i++)
		{
			STIDvals.addItem(STIDs.get(i));
		}
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setLayout(new GridLayout(2,2));
		
		for(int i=0; i<setUpVisuals().size(); i++)
		{
			this.add(setUpVisuals().get(i));
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
		//this.setupTextPoints();
		//this.addMouseListener(this);
		
		slider.addChangeListener((l) ->
		{
			sliderVal.setEditable(true);
			sliderVal = new JTextField(""+slider.getValue(), 10);
			sliderVal.setEditable(false);
			sliderPan.updateUI();
		}
		);
		
	}
	
	public ArrayList<JPanel> setUpVisuals()
	{
		ArrayList<JPanel> panelHolder= new ArrayList<JPanel>();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		sliderVal.setEditable(false);
		sliderPan.add(Enter_Dist);
		sliderPan.add(sliderVal);
		sliderPan.add(slider);
		sliderPan.setLocation(10, 300);
		panelHolder.add(sliderPan);
		
		showStationPan.add(Show_Station);
		showStationPan.add(stationShown);
		showStationPan.setLocation(300, 300);
		panelHolder.add(showStationPan);
		
		compWithPan.add(Comp_With);
		compWithPan.add(STIDvals);
		panelHolder.add(compWithPan);
		
		hammingDistPan.add(calcHD);
		hammingDistPan.add(new JLabel());
		hammingDistPan.add(Dist0);
		hammingDistPan.add(dist0Field);
		hammingDistPan.add(Dist1);
		hammingDistPan.add(dist1Field);
		hammingDistPan.add(Dist2);
		hammingDistPan.add(dist2Field);
		hammingDistPan.add(Dist3);
		hammingDistPan.add(dist3Field);
		hammingDistPan.add(Dist4);
		hammingDistPan.add(dist4Field);
		panelHolder.add(hammingDistPan);
		
		addStationPan.add(Add_Station);
		addStationPan.add(addStationField);
		panelHolder.add(addStationPan);
		
		return panelHolder;
	}
	
	public ArrayList<String> readInfo() throws IOException
	{
		/*
		 * holds the number of the STID value being read in 
		 */
		int loc=0;
		String temp="";
		/*
		 * Constructing the Buffered reader
		 */
		BufferedReader STIDVals= new BufferedReader(new FileReader("Mesonet.txt"));

		/*
		 * loop through lines of the Mesonet.txt file
		 */
		while(loc<120)
		{
			/*
			 * setting temp to the entire line at that location
			 */
			temp=STIDVals.readLine();
			/*
			 * changing the value from the entire line to just the STID value
			 */
			temp=temp.substring(0, 4);
			/*
			 * adding the STID value to the ArrayList
			 */
			STIDs.add(temp);
			/*
			 * Incrementing the location being read in
			 */
			loc++;
		}
	/*
	 * Closing the BufferReader 
	 */
	STIDVals.close();
	return STIDs;
	}
	
	
	public static void main(String[] args) throws IOException
	{
		GraphicsFrame GP= new GraphicsFrame();
		
	}
	

}
