package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {
    public static final int ALIEN = 0;
    public static final int ROCK = 1;
    public static final int COMET = 2;


    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private static final int ENEMY_SPAWN_RATE_MIN = 3000;
    // private static final int ENEMY_SPAWN_RATE_MAX = 1000;


    private static ArrayList<Enemy> enemies;
    private static SpriteSheet enemySheet;
    private static Random randomGenerator;

    private static int lastSpawn;

    public static void init() throws SlickException {
        enemySheet = new SpriteSheet("res/sprites/Enemies.png",WIDTH,HEIGHT);
        lastSpawn = 0;
        randomGenerator = new Random();
        enemies = new ArrayList<Enemy>();
    }

    public static void update(int delta)
    {
        lastSpawn += delta;

        if(lastSpawn > ENEMY_SPAWN_RATE_MIN)
        {
            //spawn new enemy
            int randomImg = randomGenerator.nextInt(3);
            try{
                enemies.add(choseEnemy(randomImg));
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            lastSpawn = 0;
        }

        for(Enemy e: enemies)
            e.update(delta);
    }

    public static void render(Graphics graphics)
    {
        for(Enemy e: enemies)
            e.render(graphics);
    }

    private static Enemy choseEnemy(int choice) throws SlickException {
        int randomX = randomGenerator.nextInt(640);
        switch(choice)
        {
            case ALIEN:
                return new AlienEnemy(randomX,0,enemySheet.getSprite(0,0));
            case ROCK:
                return new Enemy(randomX, 0, enemySheet.getSprite(1,0));
            case COMET:
                return new CometEnemy(randomX,0, enemySheet.getSprite(2,0));
            default:
                return new Enemy(randomX, 0, enemySheet.getSprite(2,0));
        }
    }

    public static ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }

}
