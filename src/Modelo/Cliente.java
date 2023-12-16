/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author andre
 */
public class Cliente {

    int puerto;
    Socket cliente;
    BufferedReader reader;

    public static void main(String[] args) {
        Cliente obj1 = new Cliente(100);
        obj1.iniciarSocketCliente();
    }

public void iniciarSocketCliente() {
        try {
            DataOutputStream salida;
            DataInputStream entrada;
            String respuesta;

            reader = new BufferedReader(new InputStreamReader(System.in));

            this.cliente = new Socket("localhost", puerto);

            System.out.print("Ingrese una placa para buscar en el servidor ");
            String placa = reader.readLine();

            // Enviar solo el primer carácter al servidor
            if (!placa.isEmpty()) {
                char primerCaracter = placa.charAt(0);

                salida = new DataOutputStream(this.cliente.getOutputStream());
                salida.writeChar(primerCaracter);

                entrada = new DataInputStream(cliente.getInputStream());

                respuesta = entrada.readUTF();
                System.out.println("Servidor: " + respuesta);
            } else {
                System.out.println("La placa ingresada no es válida.");
            }

            // Cerrar el cliente después de que el usuario haya terminado
            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente(int puerto) {
        this.puerto = puerto;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
}
