package game;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Enemy {

    protected static final int VELOCITY = 3;
    protected static final int WIDTH = 32;
    protected static final int HEIGHT = 32;

    protected Image image;
    protected int x, y;

    public Enemy(int x, int y, Image image)
    {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void update(int delta)
    {
        y += VELOCITY;
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(image,x,y);
    }

    public boolean isIntersecting(int x, int y)
    {
        return (x >= this.x && x < this.x + WIDTH && y >= this.y && y < this.y + HEIGHT);
    }

    public boolean shouldBeRemoved()
    {
        return y > 580;
    }
}
