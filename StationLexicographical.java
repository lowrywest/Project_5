import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StationLexicographical extends MesoSortedAbstract
{
	/*
	 * creating a global tree map for this class
	 */
	private TreeMap<String, Integer> sorted;
	
	/*
	 *  constructor for the StationLexicographical object
	 */
	public StationLexicographical(HashMap<String, Integer> AsciiVals)
	{
		/*
		 * calling the sorted map method in the constructor
		 */
		sorted= new TreeMap<String, Integer>();
		sortedMap(AsciiVals);
	}
	
	@Override
	/*
	 * the sorted map method from the abstract class
	 */
	public Map<String, Integer> sortedMap(HashMap<String, Integer> unsorted) 
	{
		/*
		 * putting the unsorted map into a tree map which sorts the map alphabetically
		 */
		sorted.putAll(unsorted);
		/*
		 * looping through the treeMap and printing it out.
		 */
		for (String stid : sorted.keySet()) 
		{
		    System.out.println(stid + " " + sorted.get(stid));		    
		}
		return sorted;
	}
	
	
}