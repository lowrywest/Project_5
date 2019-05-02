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
			sliderVal.setText(""+slider.getValue());
			sliderPan.updateUI();
		}
		);
		
		calcHD.addActionListener((e) ->
		{
			String temp= STIDs.get(STIDvals.getSelectedIndex());
			ArrayList<Integer> numNodes= numNodes(temp);
			dist0Field.setText(""+numNodes.get(0));
			dist1Field.setText(""+numNodes.get(1));
			dist2Field.setText(""+numNodes.get(2));
			dist3Field.setText(""+numNodes.get(3));
			dist4Field.setText(""+numNodes.get(4));
			hammingDistPan.updateUI();
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
	
	  //Method for finding the hamming distance between two strings
		public int singleHammDist(String first, String second)
		{
			//Variable to hold the hamming distance
			int hamDist=0;
			//loop through the string
			for(int loc=0;loc<first.length();loc++)
				{
					//comparing the characters at the location
					if(first.charAt(loc)!=second.charAt(loc))
					{
						//increment the hamming distance accordingly
						hamDist++;
					}
				}
			//Returning the hamming distance
			return hamDist;
		}

		//comparing the whole ArrayList of STID Values to a given STID value
		public ArrayList <Integer> numNodes(String inputSTID)
		{
			//Constructing a ArrayList to whold the number of nodes
			ArrayList <Integer> nodes = new ArrayList<Integer>();
			//Initializing the values to 0
			nodes.add(0);
			nodes.add(0); 
			nodes.add(0);
			nodes.add(0);
			nodes.add(0);
			//temp holds the current e count
			int hammingDistance=0;
			//Loops through the entire ArrayList of STID values
			for(int i=0; i<STIDs.size();i++)
			{
				//checks to make sure the STID value is different than the one from the ArrayList
				
					   //gets the hamming Distance between the two STID values
					   hammingDistance= singleHammDist(inputSTID, STIDs.get(i));
					   if(hammingDistance==0)
					   {
						   nodes.set(0, nodes.get(0)+1);
					   }
					    //Looks to see if the hamming distance is 1
					   else if(hammingDistance==1)
						{
							//increment the value of the nodes at 0
							nodes.set(1, nodes.get(1)+1);
						}
						//Looks to see if the hamming distance is 2
						else if(hammingDistance==2)
						{
							//increment the value of the nodes at 1
							nodes.set(2, nodes.get(2)+1);
						}
						//Looks to see if the hamming distance is 3
						else if(hammingDistance==3)
						{
							//increment the value of the nodes at 2
							nodes.set(3, nodes.get(3)+1);
						}
						//if the hamming distance is 4
						else 
						{
							//increment the value of the nodes at 3
							nodes.set(4, nodes.get(4)+1);
						}
				
			}
			//return the ArrayList of node values
			return nodes;
			
		}
		
	
	
	public static void main(String[] args) throws IOException
	{
		GraphicsFrame GP= new GraphicsFrame();
		
	}
	

}
