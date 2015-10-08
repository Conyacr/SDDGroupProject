package Model;


import java.awt.Graphics;

public interface GameFigure {
    //is this level state in the ships or ship state in the ships
    public void render(Graphics g);
    public void update();
    public int getState();
    static final int STATE_TRAVELING = 1;
    static final int STATE_EXPLODING = 2;
    static final int STATE_DONE = 0;
}
