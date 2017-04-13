import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor
{
    //Add GreenfootImage variables for original image and jumping image here
    private GreenfootImage original = new GreenfootImage( "Hero.png" );
    private GreenfootImage jumping = new GreenfootImage( "Hero_Jumping.png" );
    
    //Add the following variables here: y, ySpeed, smallUp, up, cannotJump, and lookingRight
    private int y = 0;
    private int ySpeed = 1;
    private int smallUp = -6;
    private int up = -15;
    private boolean cannotJump = false;
    private boolean lookingRight = true;
    private int reloadCounter = 25;
    
    
    /**
     * -- This constructor comment is still a problem. Look at your constructor
     * comment for ScrollerWorld for help.
     * 
     */
    public Hero()
    {
        original.scale(30, 30);
        jumping.scale(32, 32);
        original.mirrorHorizontally();
        setImage(original);
    }
    
    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * act launches the movement and checkCollisions method
     */
    public void act() 
    {
        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        // Add method call to movement method here
        movement();
        
        //Add method call to checkCollision here
        checkCollisions();
        
        reloadCounter++;
    }
    
    // Add movement method here that will handle the movement right, left, and up
    // for the Hero
    /**
     * movement controls the movement of the Hero 
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void movement()
    {
        if( Greenfoot.isKeyDown("right") )
        {
            
            if ( lookingRight == false )
            {
                original.mirrorHorizontally();
                jumping.mirrorHorizontally();
            }
            
            lookingRight = true;
            setLocation( getX() + 3, getY());
        }
        else if( Greenfoot.isKeyDown("left") )
        {
            
            if ( lookingRight == true )
            {
                original.mirrorHorizontally();
                jumping.mirrorHorizontally();
            }
            
            lookingRight = false;
            setLocation( getX() - 3, getY());
        }
       
        if( Greenfoot.isKeyDown("up") )
        {
            
            if( cannotJump == false )
            {
                setImage(jumping);
                y = up;
                fall();
            }
            
        }
        
        if( Greenfoot.isKeyDown("space") )
        {
            // Correction: spaced out code and made it easier to read
            if( reloadCounter >= 25 ) 
            {
                
                if( lookingRight == true )
                {
                    getWorld().addObject ( new Rasengan(this), getX() + 10 , getY() );
                }
                else
                {
                    getWorld().addObject ( new Rasengan(this), getX() - 10 , getY() );
                }
                
                reloadCounter = 0;
            }
            
        }
        
        if( getY() >= 360)
        {
            setLocation( getX(), 370);
            y = 0;
        }
        
    }
    
    // Add fall method here to handle the Hero's jumping and falling movement
    /**
     * fall causes the Hero to fall when he jumps or moves up
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void fall()
    {
        cannotJump = true;
        setLocation(getX() , getY() + y);
        y = y + ySpeed;
    }
    
    //   Add checkCollision method here that will check if we've landed on the top
    //   of an Enemy, which will increase the score; touched an Enemy otherwise, which
    //   will have us lose the game; touched a platform which will allow us to jump again;
    //   or fall
    /**
     * --checkCollisions checks if the Hero is in contact with other actors and responds accordinly
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void checkCollisions()
    {
        
        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        HealthBar healthBar = getWorld().getObjects(HealthBar.class).get(0);
        
        
        if( getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class) != null )
        {
            getWorld().removeObject( getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class));
            myWorld.addToScore(1);
            y = smallUp;
        }
        else if( isTouching(Enemy.class))
        {
            getWorld().removeObject( getOneIntersectingObject(Enemy.class) );
            healthBar.add(-200);
        }
        else if( isTouching(HealthFruit.class) )
        {
            getWorld().removeObject( getOneIntersectingObject(HealthFruit.class) );
            healthBar.add(25);
        }
        else if( getOneObjectAtOffset(0, getImage().getHeight()-15, Platform.class) != null)
        {
            setImage(original);
            cannotJump = false;
            y = 0;
        }
        else
        {
            fall();
        }
        
        
    }
    
    /**
     * getRight sends the lookingRight variable to Rasengan's code
     * 
     * @param There are no parameters
     * @return returns lookingRight variable
     */
    public boolean getRight()
    {
        return lookingRight;
    }
}
