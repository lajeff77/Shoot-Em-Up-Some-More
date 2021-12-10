package game;

import org.newdawn.slick.*;
import ui.UI;

import java.util.ArrayList;

public class Ship {

    private static final int START_X = 299;
    private static final int START_Y = 416;
    private static final int SHIP_WIDTH = 42;
    private static final int SHIP_HEIGHT = 64;
    private static final int DURATION = 150;
    private static final int SHIP_VELOCITY = 5;
    private static final int BULLET_RATE = 75;

    private static final String REF = "res/sprites/ship.png";

    private static Animation ship;
    private static int x, y;
    private static int score;
    private static int timeSinceLastShot;

    private static ArrayList<Bullet> bullets;
    private static ArrayList<Bullet> removeQueue;
    private static ArrayList<Enemy> damageQueue;

    public static void init() throws SlickException {
        x = START_X;
        y = START_Y;
        ship = new Animation(new SpriteSheet(REF, SHIP_WIDTH, SHIP_HEIGHT), DURATION);
        score = 0;
        bullets = new ArrayList<Bullet>();
        removeQueue = new ArrayList<Bullet>();
        damageQueue = new ArrayList<Enemy>();
        timeSinceLastShot = 0;
    }

    public static void update(GameContainer gameContainer, int delta) {
        Input input = gameContainer.getInput();

        //detect movement of ship
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
            x -= SHIP_VELOCITY;
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
            x += SHIP_VELOCITY;
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
            y -= SHIP_VELOCITY;
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
            y += SHIP_VELOCITY;

        //adjust ship based on screen boundaries
        if (x < 0)
            x = 0;
        if (x > gameContainer.getWidth() - SHIP_WIDTH)
            x = gameContainer.getWidth() - SHIP_WIDTH;
        if (y < 0)
            y = 0;
        if (y > gameContainer.getHeight() - SHIP_HEIGHT - 100)
            y = gameContainer.getHeight() - SHIP_HEIGHT - 100;

        timeSinceLastShot += delta;

        //shoot a bullet
        if(input.isKeyDown(Input.KEY_SPACE) && timeSinceLastShot > BULLET_RATE) {
            try {
                bullets.add(new Bullet(x + SHIP_WIDTH/2 - 2, y , UI.getAttackType()));
                timeSinceLastShot = 0;
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        for(Bullet b: bullets)
        {
            b.update();
            //check if bullet left screen
            if (b.offScreen())
                removeQueue.add(b);
            else {
                //check for bullet collision
                for(Enemy e: EnemySpawner.getEnemies()) {
                    if (e.isIntersecting(b.getX(), b.getY())) {
                        System.out.println("hitting enemy of type " + e.getClass().getName());
                        damageQueue.add(e);
                        removeQueue.add(b);
                    }
                }
            }
        }
        //damage enemies
        for(Enemy e: damageQueue)
            e.takeDamage();
        damageQueue.clear();

        //remove bullets
        for(Bullet b: removeQueue)
            bullets.remove(b);
        removeQueue.clear();

        ship.update(delta);
    }

    public static void addToScore(int num)
    {
        score += num;
    }

    public static int getScore()
    {
        return score;
    }

    public static void render(Graphics graphics)
    {
        ship.draw(x,y);
        for(Bullet b: bullets)
            b.render(graphics);
    }

}
