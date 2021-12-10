package game;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Enemy {

    protected static final int VELOCITY = 1;
    protected static final int WIDTH = 32;
    protected static final int HEIGHT = 32;

    protected Image image;
    protected int x, y;
    protected int colX, colY, colWidth, colHeight;
    protected boolean shouldBeRemoved;

    public Enemy(int x, int y, Image image, int colX, int colY, int colWidth, int colHeight)
    {
        this.x = x;
        this.y = y;
        this.image = image;
        this.colX = colX;
        this.colY = colY;
        this.colWidth = colWidth;
        this.colHeight = colHeight;
        shouldBeRemoved = false;
    }

    public Enemy(int x, int y, Image image)
    {
        this(x,y,image,x,y,WIDTH,HEIGHT);
    }


    public void update(int delta)
    {
        y += VELOCITY;
        colY += VELOCITY;
        if( y > 580)
            shouldBeRemoved = true;
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(image,x,y);
        drawCollisionBox(graphics);
    }

    public boolean isIntersecting(int x, int y)
    {
        return (x >= colX && x < colX +colWidth && y >= colY && y < colY + colHeight);
    }

    public void takeDamage(){
        //TODO implement enemy damage system
        shouldBeRemoved = true;
    }

    public boolean shouldBeRemoved()
    {
        return shouldBeRemoved;
    }

    protected void drawCollisionBox(Graphics graphics)
    {
        graphics.setColor(Color.red);
        graphics.drawRect(colX,colY,colWidth,colHeight);
    }
}
