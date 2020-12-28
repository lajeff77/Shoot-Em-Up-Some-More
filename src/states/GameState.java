package states;

import game.Ship;
import game.StarAnimator;
import game.UI;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends BasicGameState {

    private StateBasedGame game;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        StarAnimator.createStars();
        Ship.init();
        UI.init();
        // gameContainer.setShowFPS(false);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int delta = i;
        Ship.update(gameContainer, delta);
        StarAnimator.update(gameContainer, delta);
        //UI.update();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(24,20,37));
        graphics.setAntiAlias(true);
        StarAnimator.render();
        Ship.render();
        UI.render(graphics);
    }

    @Override
    public int getID() {
        return 0;
    }
}
