package states;

import graphics.Ship;
import graphics.StarAnimator;
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
        // gameContainer.setShowFPS(false);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int delta = i;

        Ship.update(gameContainer, delta);
        StarAnimator.update(delta);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(24,20,37));
        graphics.setAntiAlias(true);
        StarAnimator.render();
        Ship.render();

    }

    @Override
    public int getID() {
        return 0;
    }
}
