package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Bullet {

    //bullet selector constants
    public static final int REG_BULLET = 0;
    public static final int RED_BULLET = 1;
    public static final int BLUE_BULLET = 2;
    public static final int GREEN_BULLET = 3;

    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;

    private static final int VEL = 5;

    private static SpriteSheet bullets;
    private Image bulletSprite;

    private int x, y;

    public Bullet(int x, int y, int bulletType) throws SlickException {
        this.x = x;
        this.y = y;
        if(bullets == null)
            init();
        setImage(bulletType);
    }

    public static void init() throws SlickException {
       bullets = new SpriteSheet("res/sprites/Bullets.png", WIDTH, HEIGHT);
    }

    private void setImage(int choice)
    {
        switch (choice)
        {
            case RED_BULLET:
                bulletSprite = bullets.getSprite(1,0);
                break;
            case BLUE_BULLET:
                bulletSprite = bullets.getSprite(0,1);
                break;
            case GREEN_BULLET:
                bulletSprite = bullets.getSprite(1,1);
                break;
            default:
                bulletSprite = bullets.getSprite(0,0);
                break;
        }
    }

    public void update()
    {
        y -= VEL;
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(bulletSprite,x,y);
    }

    public boolean offScreen()
    {
        return y < 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
