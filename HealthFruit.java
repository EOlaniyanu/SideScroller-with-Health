import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthFruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthFruit extends Actor
{
    private GreenfootImage fireFruit = new GreenfootImage("NewPodoboo.png");
    private int value;
    
    private int y = 0;
    private int ySpeed = 1;
    
    public HealthFruit()
    {
        value = 50;
        fireFruit.scale(30, 30);
        setImage(fireFruit);
    }

    public HealthFruit(  int v )
    {
        value = v;
        fireFruit.scale(30, 30);
        setImage(fireFruit);
    }

    /**
     * --Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        move(-2);

        fall();
        if( getY() >= 360)
        {
            setLocation( getX(), 370);
            y = 0;
        }

        if( getX() <= 0 )
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * --fall causes the fruit to fall to the ground whenever it spawns
     * 
     * @param There are no parameters
     * @return Nothing is retuurned
     */
    private void fall()
    {
        setLocation(getX() , getY() + y);
        y = y + ySpeed;
    }
    
}    
