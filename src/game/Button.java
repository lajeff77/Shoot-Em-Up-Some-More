package game;

import org.newdawn.slick.*;

public class Button {

    private static int idCounter = 0;
    private boolean isDown;
    private boolean isDisabled;
    private boolean wasJustPushedDown;
    private int id;
    private int x,y;
    private int key;
    private final int WIDTH = 32, HEIGHT =32;
    private final int DURATION = 200;
    private Image buttonDisabled, buttonUp, buttonDown;

    public Button(String ref, int x, int y, int key) throws SlickException {
        SpriteSheet buttons = new SpriteSheet(ref,WIDTH, HEIGHT);
        buttonDisabled = buttons.getSprite(0,0);
        buttonUp = buttons.getSprite(1,0);
        buttonDown = buttons.getSprite(2,0);

        id = idCounter++;
        this.x = x;
        this.y = y;
        this.key = key;
        isDown = false;
        isDisabled = false;//true;
        wasJustPushedDown = false;
    }

    public void update(GameContainer gameContainer, boolean mousePressed)
    {
        wasJustPushedDown = false;
        Input input = gameContainer.getInput();

        if(!isDisabled && ((mousePressed && isIntersecting(input.getMouseX(), input.getMouseY())) || input.isKeyPressed(key)))
        {
            pushButton();
            if(isDown)
                wasJustPushedDown = true;
        }
    }

    public void render(Graphics graphics)
    {
            if(isDisabled)
            {
                graphics.drawImage(buttonDisabled, x, y);
            }
            else
            {
                if(isDown)
                    graphics.drawImage(buttonDown, x, y);
                else
                    graphics.drawImage(buttonUp, x, y);
            }
    }

    public boolean isDown()
    {
        return isDown;
    }

    public void pushButton()
    {
        isDown = !isDown;
    }

    public boolean isDisabled()
    {
        return isDisabled;
    }

    public void disableButton()
    {
        isDisabled = true;
    }

    public void enableButton()
    {
        isDisabled = false;
    }

    public boolean wasJustPushedDown()
    {
        return wasJustPushedDown;
    }

    public int getId()
    {
        return id;
    }

    public boolean isIntersecting(int x, int y)
    {
        return (x >= this.x && x < this.x + WIDTH && y >= this.y && y < this.y + HEIGHT);
    }

}
