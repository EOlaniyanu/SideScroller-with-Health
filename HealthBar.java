import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private GreenfootImage frame;
    private GreenfootImage healthBar;
    
    private Color good;
    private Color warning;
    private Color danger;
    
    private int target;
    private int current ;
    private int max ;
    private int speed ;
    
    public HealthBar()
    {
        frame = new GreenfootImage( 200, 30);
        healthBar = new GreenfootImage( 200, 30);
        
        frame.setColor(Color.GRAY);
        frame.fillRect(0, 0, 200, 30);
        
        good = Color.GREEN;
        warning = Color.YELLOW;
        danger = Color.RED;
        
        max = 1000;
        current = 1000;
        target = current;
        speed = 1;
        
        updateBar();
    }
    
    public HealthBar(int c, int m, int s)
    {
        frame = new GreenfootImage( 200, 30);
        healthBar = new GreenfootImage( 200, 30);
        
        frame.setColor(Color.GRAY);
        frame.fillRect(0, 0, 200, 30);
        
        good = Color.GREEN;
        warning = Color.YELLOW;
        danger = Color.RED;
        
        max = m;
        current = c;
        target = current;
        speed = s;
        
        updateBar();
    }
    
    public HealthBar(int c, int m, int s, Color g, Color w, Color d)
    {
        frame = new GreenfootImage( 200, 30);
        healthBar = new GreenfootImage( 200, 30);
        
        frame.setColor(Color.GRAY);
        frame.fillRect(0, 0, 200, 30);
        
        good = g;
        warning = w;
        danger = d;
        
        max = m;
        current = c;
        target = current;
        speed = s;
        
        updateBar();
    }
    
    /**
     * --Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        
        if ( current < target)
        {
            current += speed;
            if( current >= target )
            {
                current = target;
            }
        }
        else
        {
            current -= speed;
            if( current <= target )
            {
                current = target;
            }
        }
        
        if ( current == 0)
        {
            myWorld.gameOver();
        }
        
        updateBar();
    }
    
    /**
     * updateBar changes the Health Bar and text based on the changes to the health.
     * 
     * @param There are no parameters.
     * @return Nothing is returned.
     */
    private void updateBar()
    {
        GreenfootImage text = new GreenfootImage( 200, 30);
        double ratio = current/(max * 1.0);
        
        int healthWidth = (int)Math.round(ratio * frame.getWidth());
        
        Font timesNewRoman = new Font ( "Times New Roman", Font.PLAIN, 20 );
        if( current > max/2 ) 
        {
            healthBar.setColor(good);
        }
        else if ( current > max/4 )
        {
            healthBar.setColor(warning);
        }
        else 
        {
            healthBar.setColor(danger);
        }
        
        healthBar.clear();
        healthBar.fillRect(0, 0, healthWidth, 30);
        
        text.clear();
        text.setColor(Color.BLACK);
        text.setFont(timesNewRoman);
        text.drawString(""+ current + "/" + max, 0, 30 - text.getFont().getSize()/2);
        
        frame.clear();
        frame.setColor(Color.GRAY);
        frame.fillRect(0, 0, 200, 30);
        frame.drawImage( healthBar, 0, 0);
        frame.drawImage( text, frame.getWidth()/3, 0);
        
        setImage(frame);
    }
    
    /**
     * add increases or decreases target by a value and checks makes sure the target
     * does not go over the max or go below zero.
     * 
     * @param change is an int variable that contains the value that target is to be changed by.
     * @return Nothing is returned.
     */
    public void add(int change)
    {
        target += change;
        
        if( target > max )
        {
            target = max;
        }
        
        if( target <= 0 )
        {
            target = 0;
        }
    }
    
    /**
     * setTarget sets target to t.
     * 
     * @param t is the parameter for the int variable called target.
     * @return Nothing is returned.
     */
    public void setTarget(int t)
    {
        target = t;
    }
    
    /**
     * setCurrent sets current to c.
     * 
     * @param c is the parameter for the int variable called current.
     * @return Nothing is returned.
     */
    public void setCurrent(int c)
    {
        current = c;
    }
    
    /**
     * setMax sets max to m.
     * 
     * @param m is the parameter for the int variable called max.
     * @return Nothing is returned.
     */
    public void setMax(int m)
    {
        max = m;
    }
    
    /**
     * setSpeed sets speed to s.
     * 
     * @param s is the parameter for the int variable called speed.
     * @return Nothing is returned.
     */
    public void setSpeed(int s)
    {
        speed = s;
    }
    
    /**
     * getMax returns the max variable.
     * 
     * @param There are no parameters.
     * @return int max is returned.
     */
    public int getMax()
    {
        return max;
    }
    
    /**
     * getCurrent returns the current variable.
     * 
     * @param There are no parameters.
     * @return int current is returned.
     */
    public int getCurrent()
    {
        return current;
    }
     
}
