import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		String StID = "FAIR";
		MesoAscii Asciiavg = new MesoAscii(new MesoStation(StID));
		int AsciiAvg = Asciiavg.calAverage();
		
		HashMap<String, Integer> AsciiVal = new HashMap<String, Integer>();
		AsciiVal.put(StID, AsciiAvg);
		System.out.print("ASCII average: ");
		System.out.println(AsciiVal.get(StID));
		
		MesoEqual mesoequal = new MesoEqual(StID);
		System.out.println("Stations are: "+ mesoequal.calAsciiEqual());
		
		System.out.print("##");
		System.out.println("Unsorted:");
		AsciiVal=mesoequal.calAsciiEqual();		
		for (String stid : AsciiVal.keySet()) 
		{
		    System.out.println(stid + " " + AsciiVal.get(stid));		    
		}
		
		System.out.print("##");
		System.out.println("Sorted:");
		new StationLexicographical(AsciiVal);	  
	}
}