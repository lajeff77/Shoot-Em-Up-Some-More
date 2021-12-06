package game;

import org.newdawn.slick.Image;

import java.util.Random;

public class AlienEnemy extends Enemy{
    private static final int SIDE_VELOCITY = 4;
    private boolean isMovingLeft;
    private int lowerX;
    private int upperX;

    public AlienEnemy(int x, int y, Image image) {
        super(x, y, image);
        Random randomGenerator = new Random();
        isMovingLeft = randomGenerator.nextBoolean();
        selectLane(x);
    }

    @Override
    public void update(int delta)
    {
        super.update(delta);
        if(isMovingLeft)
            x -= SIDE_VELOCITY;
        else
            x += SIDE_VELOCITY;

        if(x < lowerX)
            isMovingLeft = false;
        if(x > upperX)
            isMovingLeft = true;
    }

    private void selectLane(int pos)
    {
        if(pos < 159)
        {
            lowerX = 0;
            upperX = 159;
        }
        else{
            if(pos < 319)
            {
                lowerX = 160;
                upperX = 319;
            }
            else{
                if(pos < 479) {
                    lowerX = 320;
                    upperX = 479;
                }
                else {
                    lowerX = 480;
                    upperX = 640;
                }
            }
        }

    }
}
