package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ButtonPanel {
    private ArrayList<Button> buttons;
    private boolean aButtonWasPressed;

    public ButtonPanel(ArrayList<Button> buttons)
    {
       this.buttons = buttons;
       aButtonWasPressed = false;
    }

    public void update(GameContainer gameContainer, boolean mousePressed)
    {
        //TODO fix simultaneous button press with keys
        aButtonWasPressed = false;
        for(Button b: buttons)
        {
            b.update(gameContainer, mousePressed);

            //if this button was just pressed
            if(!aButtonWasPressed && b.wasJustPushedDown())
            {
                aButtonWasPressed = true;
                int currId = b.getId();
                //make sure all other buttons are up
                for(Button change: buttons)
                    if(change.getId() != currId && change.isDown())
                        change.pushButton();
            }

        }
    }

    public void render(Graphics graphics)
    {
        for(Button b: buttons)
            b.render(graphics);
    }
}
