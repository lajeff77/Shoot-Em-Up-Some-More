package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.Random;

public class StarAnimator {
    private static final int MAX_STAR_COUNT = 50;
    private static final int STAR_VELOCITY = 3;
    private static final int LANES = 10;
    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 480;
    private static final String[] starRefs = {"res/sprites/star1.png", "res/sprites/star2.png", "res/sprites/star3.png"};

    private static Random r;
    private static Star[] stars;
    private static boolean[] xPickHistory;
    private static int lastPick;


    public static void createStars() throws SlickException
    {
        r = new Random();
        stars = new Star[MAX_STAR_COUNT];
        xPickHistory = new boolean[LANES];
        lastPick = 0;
        for(int i = 0; i < MAX_STAR_COUNT; i++)
        {
            makeNewStar(i);
            int pseudoRandomY = r.nextInt(SCREEN_HEIGHT/MAX_STAR_COUNT) + (SCREEN_HEIGHT/MAX_STAR_COUNT)*i;
            stars[i].setY(pseudoRandomY);
        }
    }

    private static void makeNewStar(int index) throws SlickException
    {
        String ref = starRefs[r.nextInt(starRefs.length)];
        int x = pickXBasedOnRange();
        int y = 0;
        stars[index] = new Star(ref,x,y);
    }

    private static int pickXBasedOnRange()
    {
        boolean alreadyPicked = true;
        int index = 0;

        // pick a slice on the screen that hasn't been used in the cycle and isn't the same as the last quadrant
        while(alreadyPicked || index == lastPick)
        {
            index = r.nextInt(LANES);
            alreadyPicked = xPickHistory[index];
        }
        xPickHistory[index] = true;

        //check if all true and reset array
        boolean allUsed = true;
        for(int i = 0; i < LANES; i++)
        {
            allUsed = allUsed && xPickHistory[i];
        }

        //reset if necessary
        if(allUsed) {
            for (int i = 0; i < LANES; i++)
            {
                xPickHistory[i] = false;
            }
        }

        lastPick = index;
        return r.nextInt(SCREEN_WIDTH/LANES) + (SCREEN_WIDTH/LANES)*index;
    }

    public static void update(GameContainer gameContainer, int delta) throws SlickException
    {
        for (int i = 0; i < MAX_STAR_COUNT; i++) {
            stars[i].setY(stars[i].getY() + STAR_VELOCITY);

            //check if star moved off screen and replace with new star
            if(stars[i].getY() > SCREEN_HEIGHT)
                makeNewStar(i);
            stars[i].getStarAnimation().update(delta);
        }
    }

    public static void render()
    {
        for(Star s: stars)
            s.getStarAnimation().draw(s.getX(),s.getY());
    }
}
