package utility;

public interface IDynamic
{
	/// NESTED DEFINITIONS
	public static enum Rtn
	{
		CONTINUE,
		KILLME,
		LOSE,
		EXIT
	}
	
	/// INTERFACE METHODS
	public Rtn update();
}
