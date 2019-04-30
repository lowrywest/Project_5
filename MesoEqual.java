import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MesoEqual
{
	/*
	 * Global String variable to hold the STID value from the constructor
	 */
	private String StiD;
	private HashMap<String, Integer> equalAsciiVals;
	private ArrayList<String> keyValues= new ArrayList<String>();
	
	/*
	 * The mesoEqual constructor
	 */
	public MesoEqual(String Stid) throws IOException
	{
		readInfo();
		StiD=Stid;
		equalAsciiVals= new HashMap<String, Integer>();
	}
	
	public void readInfo() throws IOException
	{
			/*
			 * holds the number of the STID value being read in 
			 */
			int loc=0;
			/*
			 * holds the current STID value
			 */
			String temp="";
			/*
			 * Constructing the Buffered reader
			 */
			BufferedReader STIDVals= new BufferedReader(new FileReader("Mesonet.txt"));
			/*
			 * Priming reads to skip the first 3 lines of Mesonet.txt
			 */
			STIDVals.readLine();
			STIDVals.readLine();
			STIDVals.readLine();
			
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
				temp=temp.substring(2, 6);
				/*
				 * adding the STID value to the ArrayList
				 */
				keyValues.add(temp);
				/*
				 * Incrementing the location being read in
				 */
				loc++;
			}
		/*
		 * Closing the BufferReader 
		 */
		STIDVals.close();
	}
	
	/*
	 * This method converts a string into a mesoAscii object
	 */
	public MesoAscii convert(String string) 
	{
		MesoStation placeHold =new MesoStation(string);
		MesoAscii calAvgStid= new MesoAscii(placeHold);
		return calAvgStid;
	}
	
	/*
	 * this method caclulates the STID values with equal average Ascii values
	 */
	public HashMap<String, Integer> calAsciiEqual()
	{
		/*
		 * this holds the average that we are comparing the whole list to
		 */
		int comparisonAvg= convert(StiD).calAverage();
		/*
		 * looping through an arraylist of key values from the hashMao
		 */
		for(int i=0; i<keyValues.size(); i++)
		{
			/*
			 * comparing the set Ascii average to the average of the all the key values in the array list
			 */
			if(comparisonAvg==(convert(keyValues.get(i)).calAverage()))
			{
				/*
				 * converting the value to an Integer object and adding it to the hash map
				 */
				Integer avg=new Integer(convert(keyValues.get(i)).calAverage());
				equalAsciiVals.put(keyValues.get(i), avg);
				
			}
		}
		return equalAsciiVals;
	}
	
}