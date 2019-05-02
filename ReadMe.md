GraphicsFrame
UML
--------------------------------------------------------------------
STIDs ArrayList<String>
FRAME_Height int
FRAME_Width int
Slider JSlider
siliderVal JTextField
Enter_Dist JLabel
Show_Station Jbutton 
stationShown JTextArea 
 Add_Station Jbutton 
 AddStationField JTextField
Comp_With JButton
STIDvals JComboBox<String> 
calcHD JButton 
Dist0 JLabel
Dist1 JLabel
Dist2 JLabel
Dist3 JLabel
Dist4 JLabel
dist0Field JTextField
dist1Field JTextField
dist2Field JTextField
dist3Field JTextField
dist4Field JTextField
randomSTID JTextField
random Jbutton
randomPanel JPanel
sliderPan JPanel 
showStationPan JPanel 
compWithPan JPanel 
hammingDistPan JPanel
addStationPan JPanel
------------------------------
 +GraphicsFrame()
 +setUpVisuals()
 +readInfo()
 +main()
---------------------------------------------------------------------------------------------------------------------------------
 
 Thought Process:
    Setup:
 The first thing that I worked on doing was getting the frame and all of the things inside of it set up. My intial move was to create 
 all the nesscary buttons, labels, textFields and anything else I could need. Once I created all of the components I needed for each 
 action I created diffrent Panels to hold their respective componets. To add all the componets to the corresponding Panels I made a 
 method. 
    setUpVisuals:
  In this method I add all the specific componets to their corresponding Panel. Before I added all the componets to the Panels I edited 
  the functionality of the components such as making the slider click to ticks and making the text fields not editable. I added all the 
  Panels to an arrayList of Panels that is returned.
    Next methods:
  I took the next methods from our previous projects and edited them to work for this project. 
    ReadInfo():
     This method reads in the txt file and adds all the Strings to an ArrayList that is returned and then set equal to a Global
     ArrayList. I had to edit the method to work with the new txt file because it was diffrent than the previous txt file.
    singleHammDist():
     This method compares two strings and calcualtes the hamming distance. The Hamming distance is returned as one int.
    numNodes(): 
     Compares a STID string to the entire file of STID values. This method had to be edited to include a hamming distance of 0 because
     we were not supposed to include 0 in the previous project. 
   Constructor:
     The first thing I did in the readInfo method to get the arrayList of STID values from the txt file. The next thing that I did 
     was to add all the values to the drop down box by looping through the arraylist and adding every Item to the JComboBox.
     Then I added the panels to the frame by calling the setup visuals and adding all the panels from the arrayList. Then I added my 
     Action Listeners. The first thing that I did was the slider change listener which checks the point of the Slider and then updates
     the TextField. Then the Show station button which finds all the STID values at the set hamming distance and then outputs them in 
     the text area. Next I did the Caclulate hamming distance button that just uses the num nodes method then updates the text fields.
     Then I did the Add station button that just added the user input to the ArrayList of STID values and then added it to the Drop down
     box. Finally I did my creative part I made a button that when clicked it maked a random STID and then adds it to the ArrayList and
     adds it to the DropDown box.
   
