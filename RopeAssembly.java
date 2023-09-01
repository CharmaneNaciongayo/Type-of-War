import java.awt.*;
import javax.swing.ImageIcon;

public class RopeAssembly {
    Image gr, p1w, p2w, ss, p1Won, p2Won;
    Image current;
    int x;
    int winner;
    static int y = 310;
    int width = 400;
    boolean thereIsWinner = false;

    public RopeAssembly() {
        gr = new ImageIcon("DesignAssets/GettingReady.png").getImage();
        p1w = new ImageIcon("DesignAssets/GirlWin.png").getImage();
        p2w = new ImageIcon("DesignAssets/GuyWin.png").getImage();
        ss = new ImageIcon("DesignAssets/Struggling.png").getImage();
        p1Won = new ImageIcon("DesignAssets/P1WinnerScreen.png").getImage();
        p2Won = new ImageIcon("DesignAssets/P2WinnerScreen.png").getImage();
        current = gr;
        x = 280;
        winner = 0;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(current, x, y, width, 120, null);
        g2d.drawLine(480,427,480,540);
        g2d.setColor(new Color(0,100,0));
    }

    public void tug(int v) {
        x += v;
    }

    public void resetRopeAssembly(int vel) {
        if (vel == 0) {
            current = ss;
        } else if (vel < 0) {
            current = p1w;
        } else {
            current = p2w;
        }
    }

    public void setWinner(int i) {
        winner = i;
    }

    public int getWinner() {
        return winner;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
}
