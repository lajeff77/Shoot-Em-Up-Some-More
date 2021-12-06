package states;

import game.*;
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
        EnemySpawner.init();
        UI.init();
        // gameContainer.setShowFPS(false);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int delta = i;
        Ship.update(gameContainer, delta);
        EnemySpawner.update(delta);
        StarAnimator.update(gameContainer, delta);
        UI.update(gameContainer);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(24,20,37));
        graphics.setAntiAlias(true);
        StarAnimator.render();
        EnemySpawner.render(graphics);
        Ship.render(graphics);
        UI.render(graphics);
    }

    @Override
    public int getID() {
        return 0;
    }
}
