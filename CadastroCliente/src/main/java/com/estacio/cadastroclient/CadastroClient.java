package com.estacio.cadastroclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.estacio.models.Produto;

public class CadastroClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket s1 = new Socket("localhost", 4321);

        ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(s1.getInputStream());

        out.writeObject("admin");
        out.writeObject("admin");
        out.writeObject("L");

        List<Produto> lista = (List<Produto>) in.readObject();

        for (Produto s : lista) {
            System.out.println(s.getNome()+" - "+s.getQuantidade()+" Unidade(s)");
        }

        s1.close();
    }
}
