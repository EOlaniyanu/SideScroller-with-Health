import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    //Add constructor here to scale the platform's image
    /**
     * Platform scales the Platform to the desired size 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public Platform()
	{
		//Add code to scale image here
		getImage().scale(100, 16);
	}

    
    /**
     * act controls the movement and existence of the Platform actors in the world
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act() 
    {
        // Add code to move platforms if right key is pressed
        if( Greenfoot.isKeyDown("right") )
        {
            move(-1);
        }
        
        // Add code to remove platforms if it's x coordinate is < 0
        if( getX() < 0 )
        {
            getWorld().removeObject(this);
        }
        
    }    
}
