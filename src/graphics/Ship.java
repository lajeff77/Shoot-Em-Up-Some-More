package graphics;

import org.newdawn.slick.*;

public class Ship {

    private static final int START_X = 0;
    private static final int START_Y = 0;
    private static final int SHIP_WIDTH = 128;
    private static final int SHIP_HEIGHT = 128;
    private static final int DURATION = 150;
    private static final int SHIP_VELOCITY = 5;

    private static final String REF = "res/sprites/ship.png";

    private static Animation ship;
    private static int x,y;

    public static void init() throws SlickException {
        x = START_X;
        y = START_Y;
        ship = new Animation(new SpriteSheet(REF,SHIP_WIDTH,SHIP_HEIGHT),DURATION);
    }

    public static void update(GameContainer gameContainer, int delta)
    {
        Input input = gameContainer.getInput();

        //detect movement of ship
        if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
            x -= SHIP_VELOCITY;
        if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
            x += SHIP_VELOCITY;
        if(input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
            y -= SHIP_VELOCITY;
        if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
            y += SHIP_VELOCITY;

        //adjust ship based on screen boundaries
        if(x < 0)
            x = 0;
        if(x > gameContainer.getWidth() - SHIP_WIDTH)
            x = gameContainer.getWidth() - SHIP_WIDTH;
        if(y < 0)
            y = 0;
        if(y > gameContainer.getHeight() - SHIP_HEIGHT)
            y = gameContainer.getHeight() - SHIP_HEIGHT;

        ship.update(delta);
    }

    public static void render()
    {
        ship.draw(x,y);
    }

}
