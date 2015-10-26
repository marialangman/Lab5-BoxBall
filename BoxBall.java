import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
/**
 * Class to create BoxBall objects that have random speed, random diameter, random color, and random intial 
 * coordinates.  They bounce off set boundaries, determined by BallDemo Canvas object by an offset value.
 * 
 * @author Maria Langman 
 * @version October 26, 2105
 */
public class BoxBall
{
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private int ySpeed;                 // initial downward speed
    private int xSpeed;                // initial horizontal speed
    private Canvas canvas;
    private int x_Max;
    private int y_Max;
    private int minValue;               //due to offset for boundaries
    /**
     * Constructor for objects of class BoxBall
     * Instance variables color, diameter, xPosition, yPosition, xSpeed, and ySpeed generated randomly while
     * minValue holds the offset value from the edge of the canvas.
     * @param xMax  the maximum value for the x-coordinate so it bounces within the given bounds
     * @param yMax  the maximum value for the y-coordinate so it bounces within the given bounds
     * @param drawingCanvas  the canvas to draw this ball on
     * @param offset the value (offset) from the edge of the canvas
     */
    public BoxBall(int xMax, int yMax, Canvas drawingCanvas, int offset)
    {
        Random generator = new Random();

        //generate random diameter;
        diameter = generator.nextInt(20)+5;  //at least 5px so the ball can be seen

        //generate random speed in x and y directions
        xSpeed = generator.nextInt(7)+1;    //ensure no horizontal
        ySpeed = generator.nextInt(7)+1;    //ensure no vertical

        //genereate a random rgb for color
        int r = generator.nextInt(250);
        int g = generator.nextInt(250);
        int b = generator.nextInt(250);
        color = new Color(r, g, b);

        canvas = drawingCanvas;

        x_Max = xMax;
        y_Max = yMax;
        minValue = offset;

        //generate random coordinates of BoxBall object
        xPosition = generator.nextInt(xMax);
        yPosition = generator.nextInt(yMax);

    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball so its directional speed changes (deflects) when it hits a boundary.  Set position
     * and speed then redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();

        //move the ball at set speed
        xPosition+=xSpeed;
        yPosition+=ySpeed; 

        // check if it has hit a boundary
        if (xPosition < minValue) {
            xPosition = diameter + minValue;
            xSpeed = -xSpeed;
        }
        if (xPosition > x_Max - diameter) {
            xPosition = x_Max - diameter;
            xSpeed = -xSpeed;
        }
        if (yPosition < minValue) {
            yPosition = diameter + minValue;
            ySpeed = -ySpeed;
        }
        if (yPosition > y_Max - diameter) {
            yPosition = y_Max - diameter;
            ySpeed = -ySpeed;
        }

        // draw again at new position
        draw();
    }
    /**
     * Set the ball to drop and stop bouncing.
     */
    public void dropBall()
    {
        yPosition = y_Max-diameter;
    }

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
