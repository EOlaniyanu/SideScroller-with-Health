import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rasengan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rasengan extends Actor
{
    private GreenfootImage rasengan = new GreenfootImage( "RasenganCharging.png" );
    private GreenfootImage rasenganFired = new GreenfootImage( "RasenganFired.png" );
    
    private boolean chargedFull = true;
    private boolean goRight;
    private Hero player;

    /**
     * Rasengan sets the image of the Rasengan when launched
     * @param h gets a refrence to Hero object
     * @return There is nothing to return
     */
    public Rasengan(Hero h)
    {
        player = h;
        goRight = player.getRight();
        setImage(rasengan);
    }
    
    /**
     * --Act - do whatever the Rasengan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * @param There are no parameters
     * @return There is nothing to return
     */
    public void act() 
    {
       
        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        
        if( goRight == true )
        {
            setLocation( getX() + 5 , getY());
        }
        else
        {
            setLocation( getX() - 5  , getY());
        }
        
        if( isTouching(HealthFruit.class))
        {
            getWorld().removeObject(getOneIntersectingObject(HealthFruit.class));
            getWorld().removeObject(this);
            myWorld.addToScore(-5);
        }
        else if( isTouching(Enemy.class))
        {
            getWorld().removeObject(getOneIntersectingObject(Enemy.class));
            getWorld().removeObject(this);
            myWorld.addToScore(1);
        }
        else if( getX() <= 0 )
        {
            getWorld().removeObject(this);
        }
        else if( getX() >= 599 )
        {
            getWorld().removeObject(this);
        }
        
    }
    
}
