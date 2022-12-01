package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Program extends Frame implements ActionListener {
    TextField argument;
    Button zamiana;
    Button losuj;
    Button wyczysc;
    static Dialog d;

    public Program() {
        super("Kalkulator wartości ręki");
        add(new Label("Sekwencja kart: "));
        setSize(800, 400);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);
        argument = new TextField(20);
        add(argument);
        zamiana = new Button("Punkty");
        zamiana.addActionListener(this);
        add(zamiana);
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu menu = new Menu("Plik");
        MenuItem mi = new MenuItem("Zamknij", new MenuShortcut('1'));
        menu.add(mi);
        menu.addActionListener(this);
        menuBar.add(menu);
        losuj = new Button("Losuj");
        losuj.addActionListener(this);
        add(losuj);
        wyczysc = new Button("Wyczyść");
        wyczysc.addActionListener(this);
        add(wyczysc);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (label.equals("Punkty")) {
            try {
                System.out.println(argument.getText());
                String text = argument.getText();
                System.out.println(text);
                Integer value = new Calc().run(argument.getText());
                Frame f= new Frame();
                d = new Dialog(f , "Wynik" , true);
                d.setLayout( new FlowLayout() );
                Button b = new Button ("OK");
                b.addActionListener (e1 -> Program.d.setVisible(false));
                d.add( new Label ("Wartość ręki: " + value + "\n") );
                d.add(b);
                d.setSize(350,350);
                d.setVisible(true);
            } catch (NumberFormatException ev) {
                System.out.println("Blad argumentow!? Wpisz poprawne wartosci!");
            }
        } else if (label.equals("Losuj")) {
            String output = new RandomDeck().run();
            argument.setText(output);
        } else if (label.equals("Wyczyść")) {
            argument.setText("");
        } else if (label.equals("Zamknij")) {
            System.exit(0);
        }

    }
}
