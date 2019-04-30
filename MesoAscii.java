
public class MesoAscii extends MesoAsciiAbstract
{
	/*
	 * Creating a global array to hold all the char values in the Stid String 
	 */
	private char[] vals;
	/*
	 * Creating the  MesoAcii object
	 */
	public MesoAscii(MesoStation Stid)
	{
		vals=Stid.getStID().toCharArray();
	}
	/*
	 * Defining the abstract method from the abstract class and seeing whether to return the floor or ceil value.
	 */
	@Override
	public int calAverage()
	{
		double avg= (vals[0]+vals[1]+vals[2]+vals[3])/4.0;
		/*
		 * comparing the decimal point value of the average to see whether to return the ceil or floor value
		 */
		if(avg-Math.floor(avg)< .5)
			{
			/*
			 * Setting the third array value to the rounded down average if the decimal value of the average is less than 0.5
			 */
			return (int)Math.floor(avg);
			}
		else
		{
			/*
			 * Setting the third array value to the rounded up average if the decimal value of the average is greater than or equal to 0.5
			 */
			return (int)Math.ceil(avg);
		}
	}
	
}