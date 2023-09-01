import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class TypeRacer extends JFrame {
    RopeAssembly ropeAssembly;
    private JTextField textField;
    private JLabel typeThis;
    boolean isCorrect = false;
    int i = 0;

    public void initialize() {
        setTitle("TypeRacer");
        setLayout(null);
        setSize(300,150);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
        setContentPane(new JLabel(new ImageIcon("E:\\_GitHub\\TEST\\src\\typeRacer_BACKGROUND.jpg")));
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("typeRacer_LIST.txt"));
            String line; 
            while ((line = reader.readLine()) != null) {
                words.add(line); 
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String randomWord = words.get((int) (Math.random() * words.size())); //Selects a random word.
        typeThis = new JLabel("Type this: " + randomWord);
        textField = new JTextField();
        textField.setSize(100,20);
        typeThis.setBounds(10,10,300,20);
        textField.setBounds(10,40,100,20);
        add(textField);
        add(typeThis);
        System.out.println("Random word: " + randomWord);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    for (i = 0; i < textField.getText().length(); i++) {
                        if (textField.getText().charAt(i) == randomWord.charAt(i)) {
                            if (textField.getText().equals(randomWord)) {
                                isCorrect = true;
                                System.out.println("Correct!");
                                setVisible(false);
                            }
                        } else {
                            isCorrect = false;
                            System.out.println("Incorrect!");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        setVisible(true);
    }
    
    public void startTypeRacerTimer() {
        Timer timer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}
