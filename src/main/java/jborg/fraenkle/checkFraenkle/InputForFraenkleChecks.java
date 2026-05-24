package jborg.fraenkle.checkFraenkle;



import consoleTools.InputStreamSession;


public class InputForFraenkleChecks
{

	static InputStreamSession iss;
    public static void main(String[] args)
    {

        System.out.println("Hello World!");
        iss = new InputStreamSession(System.in);
        
        String s = iss.forcedString("Hey whats up?");
        
        System.out.println(s + " thats up!");
    }
    
}
