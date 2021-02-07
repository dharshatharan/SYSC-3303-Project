package Constants;

/**
 * Enum for the Floors in the building
 * 
 * @author Quinn 
 * @version 02/06/2021
 */
public enum FloorNumber {
	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7);
	
	public int number;
	
	/**
	 * creates and enum based on an int
	 * @param number
	 */
	private FloorNumber(int number) {
		this.number = number;
	}
}
