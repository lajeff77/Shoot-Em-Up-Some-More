package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import states.GameState;

public class GameLauncher extends StateBasedGame {

    public GameLauncher()
    {
        super("Shoot 'Em Up Some More!");
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new GameLauncher());
            app.setDisplayMode(640,480, false);
            app.setTargetFrameRate(60);
            app.start();

        }catch(SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameState());
    }
}
