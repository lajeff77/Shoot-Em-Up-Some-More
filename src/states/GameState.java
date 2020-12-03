package states;

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
        // gameContainer.setShowFPS(false);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        // Input input = gameContainer.getInput();
        int delta = i;
        StarAnimator.update(delta);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(24,20,37));
        StarAnimator.render();
    }

    @Override
    public int getID() {
        return 0;
    }
}
