package core;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;

public class Game extends BasicGameState 
{	
	private int id;
	public static GameContainer gc;
	
	public static ArrayList<Boid> boids;
	
	Game(int id) 
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.gc = gc;
		gc.setShowFPS(false);
		boids = new ArrayList<Boid>();
		for(int i = 0; i != Values.NUM_BOIDS; i++)
		{
			boids.add(new Boid());
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		for(Boid a : boids)
		{
			a.render(g);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		for(Boid a : boids)
		{
			a.update();
		}
	}

	// Returns the ID code for this game state
	public int getID() 
	{
		return id;
	}
}
