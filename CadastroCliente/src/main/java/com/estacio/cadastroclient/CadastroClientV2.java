package com.estacio.cadastroclient;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class CadastroClientV2 {
    private static volatile boolean isRunning = true;

    public static void main(String[] args) {
        SaidaFrame saidaFrame = new SaidaFrame();
        try {
            Socket socket = new Socket("localhost", 4321);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            outputStream.writeObject("admin");
            outputStream.writeObject("admin");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            SwingUtilities.invokeLater(() -> {
                saidaFrame.setVisible(true);
            });

            ThreadClient threadClient = new ThreadClient(inputStream, saidaFrame.getTextArea());
            threadClient.start();

            while (isRunning) {

                System.out.println("Opções: ");
                System.out.println("L - Listar");
                System.out.println("E - Entrada");
                System.out.println("S - Saída");
                System.out.println("X _ Finalizar");
                System.out.print("Escolha uma opção: ");
                String comando = reader.readLine();

                if (comando.equalsIgnoreCase("L")) {
                    outputStream.writeObject("L");
                } else if (comando.equalsIgnoreCase("X")) {
                    isRunning = false;
                    socket.close();
                    System.exit(0);
                    break;
                } else if (comando.equalsIgnoreCase("E") || comando.equalsIgnoreCase("S")) {

                    outputStream.writeObject(comando);

                    System.out.print("Digite o ID da pessoa: ");
                    int pessoaId = Integer.parseInt(reader.readLine());
                    outputStream.writeObject(pessoaId);

                    System.out.print("Digite o ID do produto: ");
                    int produtoId = Integer.parseInt(reader.readLine());
                    outputStream.writeObject(produtoId);

                    System.out.print("Digite a quantidade: ");
                    int quantidade = Integer.parseInt(reader.readLine());
                    outputStream.writeObject(quantidade);

                    System.out.print("Digite o valor unitário: ");
                    float valorUnitario = Float.parseFloat(reader.readLine());
                    outputStream.writeObject( valorUnitario);
                } else {
                    System.out.println("Comando inválido.");
                }
            }
        } catch (IOException e) {}
    }
}
