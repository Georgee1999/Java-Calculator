import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

    // Rámeček
    JFrame frame;
    // Textové pole
    JTextField textField;
    // Čísla 1-9 a 0
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    // Addition (+), subtract (-), multiply (*), divided (/)
    JButton addButton, subButton, mulButton, divButton;
    // Decimal (.), Equals (=), Delete (delete), Clear (C)
    JButton decButton, equButton, delButton, clrButton;
    // Hold all the separate buttons
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD,30);

    double num1 = 0, num2 = 0, result = 0;

    char operator;

    Calculator(){

        frame = new JFrame("Calculator");
        // Metoda setDefaultCloseOperation() vybere akci, která se má provést po kliknutí na tlačítko "zavřít". Toto je malé tlačítko X v pravé horní části rámu. Pokud zapomenu zavolat tuto metodu s příslušnou konstantou, kliknutím na tlačítko "zavřít" rámeček zmizí, ale program bude stále běžet.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        // Budeme používat grid Layout
        frame.setLayout(null);


        textField = new JTextField();
        // Poloha,šířka / výška rámečku
        textField.setBounds(50,25,300,50);
        // Uložíme si náš font
        textField.setFont(myFont);
        // Omezení, že nemůžemem klikat a psát do buňky
        textField.setEditable(false);



        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0;i < 8;i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            // Pomocí setFocusable vypneme, zaostření na tlačítko, když na to klikneme, tak se nám neoznačí okolo modře
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0;i < 10;i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            // Implementujete ActionListener, který definuje, co se má udělat, když uživatel provede určitou operaci. Událost akce nastane vždy, když uživatel provede nějakou akci. Příklady: Když uživatel klepne na tlačítko, vybere položku nabídky a stiskne Enter v textovém poli.
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }
        
        delButton.setBounds(50,430,145,50);
        clrButton.setBounds(205,430,145,50);


        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));
        //panel.setBackground(Color.GRAY);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        // Tato setVisible(true) metoda způsobí, že se rámeček objeví na obrazovce. Pokud to zapomenete udělat, objekt rámečku bude existovat jako objekt v paměti, ale na obrazovce se nezobrazí žádný obrázek
        frame.setVisible(true);

    }

    public static void main(String[] args){

        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Přidání čísel na které se klikne do textFiled(buńky)
        for (int i = 0;i < 10; i++){
            if (e.getSource() == numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton){
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == subButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource() == divButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equButton){
            num2 = Double.parseDouble(textField.getText());

            switch (operator){
                case '+':
                    result = num1 + num2;
                    break;
                case'-':
                    result = num1 - num2;
                    break;
                case'*':
                    result = num1 * num2;
                    break;
                case'/':
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton){
            textField.setText("");
        }
        if (e.getSource() == delButton){
            String string = textField.getText();
            textField.setText("");
            for (int i = 0;i < string.length()-1;i++){
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
    }
}
