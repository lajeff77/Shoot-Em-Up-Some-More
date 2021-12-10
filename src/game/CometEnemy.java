package game;

import org.newdawn.slick.*;

public class CometEnemy extends Enemy{
    Animation cometAnim;
    private final String REF = "res/sprites/Comet.png";
    private final int DURATION = 150;
    public CometEnemy(int x, int y, Image image) throws SlickException {
        super(x, y, image);
        colX += 9;
        colWidth = 14;
        colY +=1;
        colHeight -= 1;
        cometAnim = new Animation(new SpriteSheet(REF, WIDTH, HEIGHT), DURATION);
    }

    @Override
    public void update(int delta)
    {
        super.update(delta);
        cometAnim.update(delta);
    }

    @Override
    public void render(Graphics graphics){
        cometAnim.draw(x,y);
        //drawCollisionBox(graphics);
    }
}
