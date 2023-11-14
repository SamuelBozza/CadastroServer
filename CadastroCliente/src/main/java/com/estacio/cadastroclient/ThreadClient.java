package com.estacio.cadastroclient;

import com.estacio.models.Produto;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JTextArea;

public class ThreadClient extends Thread {

    private ObjectInputStream entrada;
    private JTextArea textArea;

    public ThreadClient(ObjectInputStream entrada, JTextArea textArea) {
        this.entrada = entrada;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object obj = entrada.readObject();

                if (obj instanceof String) {
                    EventQueue.invokeLater(() -> {
                        textArea.append(obj.toString() + "\n");
                    });
                } else if (obj instanceof List) {
                    textArea.append("Produtos List: \n");
                    List<Produto> produtos = (List<Produto>) obj;
                    for (Produto produto : produtos) {
                        EventQueue.invokeLater(() -> {
                            textArea.append(produto.getNome() + " - " + produto.getQuantidade() + "\n");
                        });
                    }
                }
            } catch (IOException | ClassNotFoundException e) {}
        }
    }
}