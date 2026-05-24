package jborg.fraenkle.checkFraenkle;

public class FraenkleException extends Exception
{

	private static final long serialVersionUID = 1L;

	private final String msg;
	
	public FraenkleException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMessage()
	{
		return msg;
	}
}
