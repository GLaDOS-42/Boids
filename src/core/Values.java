package core;

public interface Values 
{
	public final static int NUM_BOIDS = 900;
	
	public final static float MARGIN_X = 150;
	public final static float MARGIN_Y = 150;
	public final static float MARGIN_TURNFACTOR = .5f;
	
	public final static float MAX_SPEED = 4f;
	
	public final static float MIN_DISTANCE = 32;
	public final static float AVOID_FACTOR = .03f;
	
	public final static float ALIGNMENT_DISTANCE = 60f;
	public final static float ALIGNMENT_FACTOR = .04f;
	
	public final static float COHERENCE_DISTANCE = 60f;
	public final static float COHERENCE_FACTOR = .005f;
}
