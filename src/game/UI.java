package game;

import org.newdawn.slick.*;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    private static Image uiPanel;
    private static Image gaugeBackHo, gaugeBackVert, rivet, terminal;
    private static Image overheat, health, blue, red, green;
    private static ButtonPanel attack, defense;
    private static boolean fontLoaded;
    private static org.newdawn.slick.Font font;
    private static final Color FONT_COLOR = new Color(59,69,102);
    public static void init() throws SlickException {
        uiPanel = new Image("res/ui/UI Panel.png");
        gaugeBackHo = new Image("res/ui/Gauge Back.png");
        gaugeBackVert = new Image("res/ui/Gauge Back.png");
        gaugeBackVert.rotate(90f);
        rivet = new Image("res/ui/Rivet.png");
        terminal = new Image("res/ui/Terminal.png");
        overheat = new Image("res/ui/Overheat Bar.png");
        health = new Image("res/ui/Health Bar.png");
        blue = new Image("res/ui/Blue Meter.png");
        red = new Image("res/ui/Red Meter.png");
        green = new Image("res/ui/Green Meter.png");

        ArrayList<Button> attackButtons = new ArrayList<Button>();
        attackButtons.add(new Button("res/ui/Blue Button.png",213,523, Input.KEY_8));//Blue button
        attackButtons.add(new Button("res/ui/Red Button.png",250,523, Input.KEY_9));//Red button
        attackButtons.add(new Button("res/ui/Green Button.png",286,523, Input.KEY_0));//Green button
        attack = new ButtonPanel(attackButtons);

        ArrayList<Button> defenseButtons = new ArrayList<Button>();
        defenseButtons.add(new Button("res/ui/Blue Button.png",373,523, Input.KEY_I));//Blue button
        defenseButtons.add(new Button("res/ui/Red Button.png",410,523, Input.KEY_O));//Red button
        defenseButtons.add(new Button("res/ui/Green Button.png",446,523,Input.KEY_P));//Green button
        defense = new ButtonPanel(defenseButtons);
    }

    public static void update(GameContainer gameContainer){
        Input input = gameContainer.getInput();
        boolean mousePressed;
        mousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
        attack.update(gameContainer, mousePressed);
        defense.update(gameContainer, mousePressed);
    }

    public static void render(Graphics graphics){
        //set font
        if(!fontLoaded)
            font = loadFont();

        //background
        graphics.drawImage(uiPanel,0,480);

        //health
        font.drawString( 40,490, "Health", FONT_COLOR);
        graphics.drawImage(gaugeBackHo, 30, 515);// x+3, y+3
        graphics.drawImage(health,33,518);

        //overheat
        font.drawString( 27,525, "Overheat", FONT_COLOR);
        graphics.drawImage(gaugeBackHo, 30, 550);
        graphics.drawImage(overheat,33,553);

        //blue meter
        graphics.drawImage(gaugeBackVert, 90, 525);
        graphics.drawImage(blue,121,500);

        //red meter
        graphics.drawImage(gaugeBackVert, 110, 525);
        graphics.drawImage(red,141,500);

        //green meter
        graphics.drawImage(gaugeBackVert, 130, 525);
        graphics.drawImage(green,161,500);

        //attack buttons
        font.drawString( 245,490, "Attack", FONT_COLOR);
        graphics.drawImage(rivet, 190, 520);
        attack.render(graphics);

        //defense buttons
        font.drawString( 400,490, "Defense", FONT_COLOR);

        graphics.drawImage(rivet, 350, 520);
        defense.render(graphics);

        //score
        //System.out.println();
        String score = String.format("%07d", Ship.getScore() );
        font.drawString(545, 490, "Score", FONT_COLOR);
        graphics.drawImage(terminal, 513,515);
        font.drawString( 527,527,score , Color.white);
    }

    private static org.newdawn.slick.Font loadFont()
    {
        org.newdawn.slick.Font font = null;

        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/munro.ttf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(20f); // set font size
            font = new TrueTypeFont(awtFont, false);
        } catch (Exception e) {
            Font awtFont = new Font("Times New Roman", Font.BOLD, 20);
            font = new TrueTypeFont(awtFont, false);
            e.printStackTrace();
        }

        fontLoaded = true;
        return font;
    }

    public static int getAttackType()
    {
        //TODO write this method
        return 0;
    }

}
