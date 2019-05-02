import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GraphicsFrame extends JFrame 
{
	/*
	 * Global ArrayList to Hold the info from the text file
	 */
	ArrayList<String> STIDs= new ArrayList<String>();
	/*
	 * The constant variables for the Frame width and Height 
	 */
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT= 1000;
	
	/*
	 * Making the slider and the slider text field and the slider label 
	 */
	JSlider slider= new JSlider(JSlider.HORIZONTAL,1,4,1);
	JTextField sliderVal= new JTextField(""+slider.getValue(),10);
	JLabel Enter_Dist= new JLabel("Enter Hamming Dist:");
	/*
	 * Making the Show Station button, Text Area and the ScrollBar
	 */
	JButton Show_Station= new JButton("Show Station");
	JTextArea stationShown= new JTextArea(30,30);
	JScrollPane scroll = new JScrollPane(stationShown);
	
	/*
	 * Making the add Station button and textField
	 */
	JButton Add_Station= new JButton("Add Station");
	JTextField addStationField= new JTextField(5);
	/*
	 *  Making the Compare with Label and Drop down box
	 */
	JLabel Comp_With= new JLabel("Compare with:");
	@SuppressWarnings("rawtypes")
	JComboBox<String> STIDvals = new JComboBox();
	/*
	 * Making the Calculate Hamming Distance button labels and Textfields
	 */
	JButton calcHD= new JButton("Calculate HD");
	JLabel Dist0= new JLabel("Distance 0");
	JLabel Dist1= new JLabel("Distance 1");
	JLabel Dist2= new JLabel("Distance 2");
	JLabel Dist3= new JLabel("Distance 3");
	JLabel Dist4= new JLabel("Distance 4");
	JTextField dist0Field= new JTextField(10);
	JTextField dist1Field= new JTextField(10);
	JTextField dist2Field= new JTextField(10);
	JTextField dist3Field= new JTextField(10);
	JTextField dist4Field= new JTextField(10);
	/*
	 * Making the Random STID creater's textField and Button
	 */
	JTextField randomSTID= new JTextField(10);
	JButton random= new JButton("Click to get a random STID value");
	
	/*
	 * Making the Panels to hold the individual parts of the gui
	 */
	JPanel randomPanel= new JPanel();
	JPanel sliderPan= new JPanel();
	JPanel showStationPan= new JPanel();
	JPanel compWithPan= new JPanel();
	JPanel hammingDistPan= new JPanel(new GridLayout(4,3));
	JPanel addStationPan= new JPanel();
	/*
	 * The Graphics Frame Constructor
	 */
	public GraphicsFrame() throws IOException
	{
		/*
		 * Setting the Global arrayList to the STID values using the Read info method
		 */
		STIDs=readInfo();
		/*
		 * Adding the Strings from the ArrayList into the drop down box by looping through the arrayList
		 */
		for (int i=0; i< STIDs.size(); i++)
		{
			STIDvals.addItem(STIDs.get(i));
		}
		/*
		 * Setting the Size and Layout of the Frame
		 */
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setLayout(new GridLayout(2,1));
		/*
		 * this loop goes through an arrayList that holds all the Panels
		 * which have the buttons, lables, Text Fields, etc inside of them
		 */
		for(int i=0; i<setUpVisuals().size(); i++)
		{
			this.add(setUpVisuals().get(i));
		}
		/*
		 * setting the close operation and setting the window to visible
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		/*
		 * This Listener looks at the slider 
		 */
		slider.addChangeListener((l) ->
		{
			/*
			 * when the slider is changed the text field is updated to the value the slider is at
			 */
			sliderVal.setText(""+slider.getValue());
			sliderPan.updateUI();
		}
		);
		/*
		 * Show Station button is clicked
		 */
		Show_Station.addActionListener((e) ->
		{
			/*
			 * the Hamming distance to compare the hamming distnce and the string selected in the drop down box
			 */
			int hamCompare=slider.getValue();
			String STIDcommpare= STIDs.get(STIDvals.getSelectedIndex());
			/*
			 * holds the ones that are have the specified hamming distance
			 */
			ArrayList<String> equalSTIDs= new ArrayList<String>();
			/*
			 * looping through the arrayList
			 */
			for(int i=0; i< STIDs.size(); i++)
			{
				if(singleHammDist(STIDcommpare, STIDs.get(i))==hamCompare)
				{
					equalSTIDs.add(STIDs.get(i));
				}
			}
			/*
			 * string to hold the output
			 */
			String output=" ";
			/*
			 * looping through to add to the output
			 */
			for(int l=0; l<equalSTIDs.size(); l++)
			{
				output+="  "+equalSTIDs.get(l)+"\n";
			}
			/*
			 * updating the text area
			 */
			stationShown.setText(output);
			
		}
		);
		/*
		 * when calcHD is pressed
		 */
		calcHD.addActionListener((e) ->
		{
			/*
			 * holds the STID value to be compared from the drop down arrow
			 */
			String temp= STIDs.get(STIDvals.getSelectedIndex());
			/*
			 * uses the num nodes methods to get an arraylist of Intergers
			 */
			ArrayList<Integer> numNodes= numNodes(temp);
			/*
			 * updating the text fields
			 */
			dist0Field.setText(""+numNodes.get(0));
			dist1Field.setText(""+numNodes.get(1));
			dist2Field.setText(""+numNodes.get(2));
			dist3Field.setText(""+numNodes.get(3));
			dist4Field.setText(""+numNodes.get(4));
			hammingDistPan.updateUI();
		}
		);
		/*
		 * whne the add Station button is pressed
		 */
		Add_Station.addActionListener((e) ->
		{
			/*
			 * takes the STring from the Text field and adds it to the ArrayList and the DRop down box
			 */
			STIDs.add(addStationField.getText());
			STIDvals.addItem(addStationField.getText());
			hammingDistPan.updateUI();
		}	
		);
		/*
		 * Creating a Random STID value
		 */
		random.addActionListener((e) ->
		{
			/*
			 * string that holds the alphabet
			 */
			String letters= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			/*
			 * String that holds the output
			 */
			String randSTID= "";
			/*
			 * Picking a random number between one and 24 and then taking that character fromm the alphabet
			 */
			for(int i=0; i<4; i++)
			{
			int random = (int)(Math.random() * 24 + 1);
			randSTID+=letters.charAt(random)+"";
			}
			/*
			 * outputting the reandom string ad adding it to the Arraylist and the dropdown
			 */
			randomSTID.setText(randSTID);
			STIDs.add(randSTID);
			STIDvals.addItem(randSTID);
		}
		);
		
		
	}
	
	public ArrayList<JPanel> setUpVisuals()
	{
		/*
		 * ArrayList to hold all the panels
		 */
		ArrayList<JPanel> panelHolder= new ArrayList<JPanel>();
		/*
		 * fomratting the slider
		 */
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		sliderVal.setEditable(false);
		/*
		 * adding the things associated with the slider to the Slider panel
		 */
		sliderPan.add(Enter_Dist);
		sliderPan.add(sliderVal);
		sliderPan.add(slider);
		sliderPan.setLocation(10, 300);
		/*
		 * adding the panel to the arraylist
		 */
		panelHolder.add(sliderPan);

		/*
		 * adding the things associated with the show station to the show station panel
		 */
		showStationPan.add(Show_Station);
		showStationPan.add(stationShown);
		showStationPan.setLocation(300, 300);
		/*
		 * adding the panel to the arraylist
		 */
		panelHolder.add(showStationPan);
		
		/*
		 * adding the things associated with the Compare with to the show compare With panel
		 */
		compWithPan.add(Comp_With);
		compWithPan.add(STIDvals);
		panelHolder.add(compWithPan);
		
		hammingDistPan.add(calcHD);

		/*
		 * adding the things associated with the hamming distance to the show hamming distance panel
		 */
		hammingDistPan.add(Dist0);
		hammingDistPan.add(dist0Field);
		dist0Field.setEditable(false);
		hammingDistPan.add(Dist1);
		hammingDistPan.add(dist1Field);
		dist1Field.setEditable(false);
		hammingDistPan.add(Dist2);
		hammingDistPan.add(dist2Field);
		dist2Field.setEditable(false);
		hammingDistPan.add(Dist3);
		hammingDistPan.add(dist3Field);
		dist3Field.setEditable(false);
		hammingDistPan.add(Dist4);
		hammingDistPan.add(dist4Field);
		dist4Field.setEditable(false);
		panelHolder.add(hammingDistPan);
		/*
		 * adding the things associated with the add station to the add station panel
		 */
		addStationPan.add(Add_Station);
		addStationPan.add(addStationField);
		panelHolder.add(addStationPan);
		/*
		 * adding the things associated with the random station to the random station panel
		 */
		randomSTID.setEditable(false);
		randomPanel.add(random);
		randomPanel.add(randomSTID);
		panelHolder.add(randomPanel);
		
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
