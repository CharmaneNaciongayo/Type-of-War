import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class GameFrame {
    int playerID, ropeSpeed;
    JFrame gameFrame, endScreenFrame;
    JButton startButton;
    JLabel jl;
    Socket s;
    boolean isStarting;
    GameCanvas gameCanvas;
    JPanel startScreen;
    ReadFromServer rfsRunnable;
    WriteToServer wtsRunnable;
    Player player;

    public GameFrame() {

        this.gameFrame = new JFrame();
        this.startScreen = new StartScreen();
        this.gameCanvas = new GameCanvas();
        this.isStarting = false;
        this.startButton = new JButton("Start");
        startButton.setBounds(430, 500, 100, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    startButton.setVisible(false);
                    startScreen.setVisible(false);
                    gameCanvas.addKeyBindings();
                    gameCanvas.startClickTimer();
                    gameCanvas.startRepaintTimer();
                    gameFrame.add(gameCanvas);
                    isStarting = true;
                   }
        });
        startScreen.add(startButton);
        gameFrame.add(startScreen);
        this.playerID = 0;
        this.player = null;
        this.ropeSpeed = 0;
    }

    public void setUpGameFrame() {
        gameFrame.setTitle("Type of War | Player #" + playerID);
        gameFrame.setSize(960, 540);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        try {
            File file = new File("DesignAssets/bgm.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Music is not working.");
        }
    }

    public void connectToServer(String ip) {
        try {
            s = new Socket(ip, 2000);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            DataInputStream in = new DataInputStream(s.getInputStream());
            System.out.println("Connected to server " + ip);
            playerID = in.readInt();
            System.out.println("You are Player #" + playerID + ".");
            player = new Player(playerID);
            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMessage();
            Thread read = new Thread(rfsRunnable);
            Thread write = new Thread(wtsRunnable);
            read.start();
            write.start();
            if (playerID == 1) System.out.print("Waiting for Player #2 to connect."); // for Player 1 only
        } catch (Exception e) {
            System.out.println("Unable to connect to server.");
        }
    }

    private class ReadFromServer implements Runnable {
        private DataInputStream in;
        private ReadFromServer(DataInputStream d) {
            in = d;
            System.out.println("RFS Runnable created");
        }
        @Override
        public void run() {
            try {
                while (true) {
                    if (player != null) ropeSpeed = in.readInt();

                }
            } catch (IOException e) {
                System.out.println("RFS Run failed.");
            }
        }

        public void waitForStartMessage() {
             try {
                String startMsg = in.readUTF();
                System.out.println("Message from server: " + startMsg); 
             } catch (IOException e) {
                System.out.println("IO Exception from waitForStartMessage");
             }
        }
    }

    private class WriteToServer implements Runnable {
        private DataOutputStream out;
        private WriteToServer(DataOutputStream d) {
            out = d;
            System.out.println("WTS Runnable created");
        }
        @Override
        public void run() {
            try {
                while (true) {
                    if (player != null) {
                        int clicks = gameCanvas.getClicks();
                        player.calculateSpeed(clicks);
                        out.writeInt(player.getSpeed());
                        out.flush();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }
            } catch (Exception e) {
                System.out.println("WTS Runnable failed.");
            }
        }
    }

    public void setUpFrameTimers() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameCanvas.passVelocity(ropeSpeed);
            }
            
        });
        timer.setRepeats(true);
        timer.start();
    }

    public GameCanvas getCanvas() {
        return gameCanvas;
    }
}
