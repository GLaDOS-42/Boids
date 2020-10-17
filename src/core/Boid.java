package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Boid 
{
	private float identifier;
	private Image image;
	private float dx;
	private float dy;
	private float x;
	private float y;
	private float scale= .8f;
	
	private float turnFactor;
	private float maxSpeed;
	

	public Boid()
	{
		setImage("res/Boid.png");
		x = Utility.randomFloat(0, Game.gc.getWidth());
		y = Utility.randomFloat(0, Game.gc.getHeight());
		
		turnFactor = Values.MARGIN_TURNFACTOR;
		maxSpeed = Values.MAX_SPEED;
		identifier = Utility.randomFloat(-999999f, 999999f);
		
		float theta = Utility.randomFloat(0, 2 * Math.PI);
		dx = maxSpeed * (float)Math.cos(theta);
		dy = maxSpeed * (float)Math.sin(theta);
	}
	
	public void setImage(String filepath)
	{
		try
		{
			image = new Image(filepath);
		}
		catch(SlickException e)		
		{
			System.out.println("Image not found!");
		}
	}
	
	//returns distance from another boid
	private float getDistance(Boid other) 
	{
		return (float)Math.sqrt((double)((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)));
	}
	
	private void avoid()
	{
		float moveX = 0;
		float moveY = 0;
		for(Boid other : Game.boids)
		{
			if(other.identifier != this.identifier)
			{
				if(getDistance(other) < Values.MIN_DISTANCE)
				{
					moveX += x - other.x;
					moveY += y - other.y;
				}
			}
		}
		
		dx += moveX * Values.AVOID_FACTOR;
		dy += moveY * Values.AVOID_FACTOR;
	}
	
	//match velocity with nearby boids
	private void align()
	{
		float avgDX = 0;
		float avgDY = 0;
		float numNeighbors = 0;
		
		for(Boid other : Game.boids)
		{
			if(other.identifier != this.identifier)
			{
				if(getDistance(other) < Values.ALIGNMENT_DISTANCE)
				{
					avgDX += other.dx;
					avgDY += other.dy;
					numNeighbors++;
				}
			}
		}
		
		if(numNeighbors > 0)
		{
			avgDX /= numNeighbors;
			avgDY /= numNeighbors;
			
			dx += (avgDX - dx) * Values.ALIGNMENT_FACTOR;
			dy += (avgDY - dy) * Values.ALIGNMENT_FACTOR;
		}
	}
	
	//adjust velocity toward center of mass of nearby boids
	private void coherence()
	{
		float centerX = 0;
		float centerY = 0;
		float numNeighbors = 0;
		
		for(Boid other : Game.boids)
		{
			if(other.identifier != this.identifier)
			{
				if(getDistance(other) < Values.COHERENCE_DISTANCE)
				{
					centerX += other.x;
					centerY += other.y;
					numNeighbors++;
				}
			}
		}
		
		if(numNeighbors > 0)
		{
			centerX /= numNeighbors;
			centerY /= numNeighbors;
			
			dx += (centerX - x) * Values.COHERENCE_FACTOR;
			dy += (centerY - y) * Values.COHERENCE_FACTOR;
		}
	}
	
	private void checkBoundaries()
	{
		if(x >= Game.gc.getWidth()) { x = 0; }
		if(x < 0) { x = Game.gc.getWidth(); }
		if(y > Game.gc.getHeight()) { y = 0; }
		if(y < 0) { y = Game.gc.getHeight(); }
	}
	
	public void render(Graphics g)
	{
		image.setCenterOfRotation(image.getWidth() * scale / 2, image.getHeight() * scale / 2);
		image.setRotation((float)(90 + 180 * Math.atan2((double)dy, (double)dx) / Math.PI));
		image.draw(x + scale * image.getWidth(), y + scale * image.getHeight(), scale);
	}
	
	public void update()
	{	
		coherence();
		align();
		avoid();
		//avoidBoundaries();
		checkBoundaries();
		
		dx = Values.MAX_SPEED * (dx / (float)Math.sqrt(dx * dx + dy * dy));
		dy = Values.MAX_SPEED * (dy / (float)Math.sqrt(dx * dx + dy * dy));
		
		x += dx;
		y += dy;
	}

}
