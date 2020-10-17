package core;

public class Utility 
{
	public static int random(int max) 
	{
		return (int) (Math.random() * max);
	}

	public static int randomInt(int min, int max) 
	{
		return (int) (Math.random() * (max - min + 1)) + min;

	}

	public static float randomFloat(double min, double max) 
	{
		return (float) (min + (Math.random() * (max - min)));
	}
	
	public static float getDistance(float x, float y, float mx, float my) 
	{
		return (float) Math.sqrt((x - mx) * (x - mx) + (y - my) * (y - my));
	}
}
