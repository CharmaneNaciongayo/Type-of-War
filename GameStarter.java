import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;

public class GameStarter {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("IP Address: ");
        String ip = in.next();
        GameFrame gameFrame = new GameFrame();
        gameFrame.connectToServer(ip);
        gameFrame.setUpGameFrame();
        gameFrame.setUpFrameTimers();
        in.close();
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameCanvas gameCanvas = gameFrame.getCanvas();
                RopeAssembly ropeAssembly = gameCanvas.getRopeAssembly();
                int winner = ropeAssembly.getWinner();      
                if (winner == 0) {
                    TypeRacer typeRacer = new TypeRacer();
                    typeRacer.initialize();
                    typeRacer.startTypeRacerTimer();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}