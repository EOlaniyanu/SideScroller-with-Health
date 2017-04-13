import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * Name: Emmanuel Olaniyanu
 * Teacher: Mr Hardman
 * Assingment #1, SideScroller
 * Date Last Modified: 27th of Febuary // Actually added the date last modified 
 * @version (a version number or a date)
 * 
 * Mark: 13.5/10
 * +5 Mark:
 * -1 Mark: Incomplete assignment comment block ---- Corrected
 * -1 Mark: Incorrect comment blocks - Still some floating around in your code. Changed to -0.5 Mark
 */

public class ScrollerWorld extends World
{
    //Add platformCounter and score variables here
    private int platformCounter = 25;
    private int score = 0;
    private int spawnCounter = 55;
    
    /**
     * ScrollerWorld creates the size of the world and starts the prepareWorld and displayScore methods
     * @param There are no parameters
     * @return There is nothing to return
     */
    public ScrollerWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);

        //Method call to prepareWorld method which will add objects to world
        prepareWorld();

        //when created, add method call to displayScore here
        displayScore();
    }

    /**
     * prepareWorld adds objects to world to prepare the game for use
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void prepareWorld()
    {
        
        //Add for loop here to fill bottom of world with platforms
        for( int i = 0; i <= getWidth()/50; i++)
        {
            addObject ( new Platform(), i*50 , getHeight() - 8 );
        }

        //Add Hero object to the world
        addObject ( new Hero(), 30 , getHeight() - 30);
        addObject ( new HealthBar(), getWidth()/2, 13);
        addObject ( new HealthFruit(), Greenfoot.getRandomNumber(500) + 5, Greenfoot.getRandomNumber(15)+ 255);
    }
    
    // Realized the comment block did not start with waht the method does but how to create the method
    // Add an act method here that will handle adding platforms to the right side
    // * of the world so that the hero doesn't run out of platform to run on. This
    // * method will also add Enemies to the world 0.67% of the time. The last thing
    // * the act method will need is a method call to the displayScore method
    /**
     * --act makes sure the platfroms continue to spawn in front of Hero as the right key is pressed and
     * also spawns a number of Enemy actors as well as startung the displayScore method
     * 
     */
    public void act()
    {
        
        if( Greenfoot.isKeyDown("right") )
        {
            
            if( platformCounter >= 25 )
            {
                platformCounter = 0;
                addObject ( new Platform(), getWidth() - 25, getHeight() - 8);
            }
        
            platformCounter++;
        }
        
        if( Greenfoot.getRandomNumber(150) < 1)
        {
            addObject ( new Enemy(), 599, getHeight() - 27);
        }
        
        if( spawnCounter >= 600 )
        {
            addFruit();
            spawnCounter = 0;
        }
        else
        {
            spawnCounter++;
        }
        
        displayScore();
    }
    
    /**
     * --addFruit adds a HealthFruit to the world 
     * 
     * @param There are no parameters
     * @return Nothing is retuurned
     */
    public void addFruit()
    {
        addObject ( new HealthFruit(), Greenfoot.getRandomNumber(500) + 5, Greenfoot.getRandomNumber(15)+ 255);
    }
    
    
    //Add gameOver method here that will display game over message and stop the scenario
    /**
     * gameOver stops the game when its launched and also prints a message to the user
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void gameOver()
    {
        showText("You have been defeated! Score:" + score, getWidth()/2, getHeight()/2);
        Greenfoot.stop();
    }
    
    //Add displayScore method here that will display the score in the top left of the world
    /**
     * displayScore displays the score at the left corner of the world
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void displayScore()
    {
        showText( "Score:" + score, 50, 25);
    }
    
    //Add addToScore method here that will increment the score variable
    /**
     * --addToScore adds 1 to the score variable
     * 
     * @param c is an int variable that contains the change that is to be applied to score
     * @return Nothing is returned
     */
    public void addToScore(int c)
    {
        score += c;
        if( score < 0 )
        {
            score = 0;
        }
    }
}
