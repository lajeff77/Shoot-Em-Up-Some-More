package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Star {
    private static final int STAR_WIDTH = 9;
    private static final int STAR_HEIGHT = 9;
    private static final int DURATION = 500;

    private Animation starAnimation;
    private int x, y;

    public Star(String ref, int x, int y) throws SlickException {
        starAnimation = new Animation(new SpriteSheet(ref,STAR_WIDTH, STAR_HEIGHT), DURATION);
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public Animation getStarAnimation()
    {
        return starAnimation;
    }
}
