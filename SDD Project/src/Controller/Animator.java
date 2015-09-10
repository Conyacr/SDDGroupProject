
public class Animator implements Runnable {
    boolean running;
    GamePanel gamePanel = null;
    GameData gameData = null;
    
    public Animator() {
       
    }
    
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
             
            System.out.println("Before Update");
            gameData.update();
             System.out.println("Before GameRender");
            gamePanel.gameRender();
            System.out.println("Before Game Panel");
            gamePanel.printScreen();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                
            }
        }
        System.exit(0);
    }
    
}