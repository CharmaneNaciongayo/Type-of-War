import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JPanel {
	RopeAssembly ropeAssembly;
	Image currentScreen, bg, p1ws, p2ws;
	int clicks, referenceClicks;
	int velocity;
	TypeRacer typeRacer;
    
    public GameCanvas() {
        setFocusable(true);
		velocity = 0;
		ropeAssembly = new RopeAssembly();
		bg = new ImageIcon("DesignAssets/Background.png").getImage();
		p1ws = new ImageIcon("DesignAssets/P1WinnerScreen.png").getImage();
		p2ws = new ImageIcon("DesignAssets/P2WinnerScreen.jpeg").getImage();
		currentScreen = bg;
    }

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		if (ropeAssembly.getX() > 960/2 || ropeAssembly.getX() + ropeAssembly.getWidth() < 960/2) {
			if (ropeAssembly.getX() > 960/2) {
				currentScreen = p2ws;
				ropeAssembly.setWinner(2);
				g2d.drawImage(currentScreen, 0, 0, 960, 540, null);
			} else if (ropeAssembly.getX() + ropeAssembly.getWidth() < 960/2) {
				currentScreen = p1ws;
				ropeAssembly.setWinner(1);
				g2d.drawImage(currentScreen, 0, 0, 960, 540, null);
			}
		} else {
			currentScreen = bg;
			g2d.drawImage(currentScreen, 0, 0, 960, 540, null);
			ropeAssembly.draw(g2d);
		}
	}

	public void startRepaintTimer() {
			Timer timer = new Timer(100, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (ropeAssembly.getWinner() == 0) {
						ropeAssembly.resetRopeAssembly(velocity);
						ropeAssembly.tug(velocity);
						repaint();
					} else {
						repaint();
					} 
				}
			});
			timer.setRepeats(true);
			timer.start();
	}

	public void startClickTimer() {
		Timer clickTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				referenceClicks = clicks;
				clicks = 0;
			}
		});
		clickTimer.setRepeats(true);
		clickTimer.start();
	}

    public void addKeyBindings() {
		ActionMap am = getActionMap();
		InputMap im = getInputMap();
		AbstractAction sc = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				clicks++;
			}
		};
		am.put("Spacebar Press", sc);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "Spacebar Press");
	}

	public void resetClicks(){
		this.clicks = 0;
	}

	public int getClicks() {
		return referenceClicks;
	}

	public void passVelocity(int s) {
		this.velocity = s;
	}

	public RopeAssembly getRopeAssembly() {
		return ropeAssembly;
	}
}
