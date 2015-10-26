import java.awt.Color;

/**
 * Class BallDemo - a short demonstration showing animation with the Canvas class.  
 * Added boxBounce method to have balls bounce inside a box within Canvas.
 * 
 * @author Michael Kölling and David J. Barnes / Edited by Maria Langman. October 2015
 * @version 2011.07.31
 *
 * */

public class BallDemo   
{
    private Canvas myCanvas;
    private final int WIDTH = 600;
    private final int HEIGHT = 500;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", WIDTH, HEIGHT);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        // myCanvas.erase();   //refresh the Canvas
        int ground = 400;   // position of the ground line

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }

    /**
     * Method boxBounce is responsible for drawing four boundaries within the canvas such that BoxBall objects 
     * can bounce only inside the boundaries, which are determined by a final int named OFFSET that is drawn 
     * from the edges of the canvas.  Therefore, the WIDTH of the canvas and the HEIGHT set the 
     * boundaries of the lines as per OFFSET.  Bouncing will end as controlled by a 'timer'.
     * @param numberOfBalls to specify how many balls are in the box.
     * @param offset value to set the boundaries of the box
     */
    public void boxBounce(int numberOfBalls, int offset)
    {
        myCanvas.erase();   //refresh the Canvas
        myCanvas.setVisible(true);
        final int OFFSET = offset; //offset from the boundaries
        final int X_MAX = WIDTH - OFFSET;
        final int Y_MAX = HEIGHT - OFFSET;

        //Create BoxBall objects array
        BoxBall[] balls = new BoxBall[numberOfBalls];

        //Create the array of balls
        for (int i=0; i<balls.length; i++) 
        {
            balls[i] = new BoxBall(X_MAX, Y_MAX, myCanvas, OFFSET);
        }

        //Bounce the balls
        int timer=0;       
        while (timer<200)
        {
            myCanvas.wait(30);           // small delay

            //draw boundaries each simulation "tick" to 'mask' erase() method's peculiarity
            //when object is at any of the boundaries
            myCanvas.drawLine(OFFSET, OFFSET, X_MAX, OFFSET);   //top boundary
            myCanvas.drawLine(X_MAX, OFFSET, X_MAX, Y_MAX);     //right boundary
            myCanvas.drawLine(OFFSET, Y_MAX, X_MAX, Y_MAX);     //bottom boundary
            myCanvas.drawLine(OFFSET, OFFSET, OFFSET, Y_MAX);   //left boundary

            //bounce each  balls
            for (int i=0; i<balls.length; i++)
            {
                balls[i].move();
            }
            timer++;
        }

        for (int i=0; i<balls.length; i++)
        {
            balls[i].erase();
            balls[i].dropBall();
            balls[i].draw();
        }
    }
}
