package com.estacio.cadastroclient;

import javax.swing.*;

public class SaidaFrame extends JDialog {
    private JTextArea texto;

    public SaidaFrame() {
        setBounds(500, 500, 450, 450);
        setModal(false);

        texto = new JTextArea();
        add(texto);
    }


    public JTextArea getTextArea() {
        return this.texto;
    }
}
