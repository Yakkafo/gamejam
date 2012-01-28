package utility;

public interface IDynamic
{
	/// NESTED DEFINITIONS
	public static enum Rtn
	{
		CONTINUE,
		DELETE_OBJECT,
		CHANGE_SCENE,
		EXIT_GAME
	}
	
	/// INTERFACE METHODS
	public Rtn update();
}
