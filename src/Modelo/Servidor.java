/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;

/**
 *
 * @author andre
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    private ServerSocket servidor;
    private Map<Character, String> ciudad;

    // TODO code application logic here
    public static void main(String[] args) {
        Servidor obj1 = new Servidor();
        obj1.iniciarSocketServidor(100);
    }

    public void iniciarSocketServidor(int puerto) {
        try {
            cargarPlacas();
            servidor = new ServerSocket(puerto);

            while (true) {
                System.out.println("Esperando conexiones en el puerto: " + puerto);
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                char primerCaracterPlaca = entrada.readChar(); 

                String resultado = ciudad.get(Character.toUpperCase(primerCaracterPlaca)); 

                DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

                if (resultado != null) {
                    salida.writeUTF(resultado);
                    System.out.println("La placa pertenece a la ciudad de  " + resultado);
                } else {
                    salida.writeUTF("La placa ingresada no se encuentra en el servidor.");
                }

                cliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarPlacas() {
        ciudad = new HashMap<>();
        ciudad.put('A', "Azuay");
        ciudad.put('B', "Bolivar");
        ciudad.put('U', "Cañar");
        ciudad.put('X', "Cotopaxi");
        ciudad.put('H', "Chimborazo");
        ciudad.put('O', "El Oro");
        ciudad.put('E', "Esmeraldas");
        ciudad.put('Q', "Francisco de Orellana");
        ciudad.put('W', "Galapagos");
        ciudad.put('G', "Guayas");
        ciudad.put('I', "Imbabura");
        ciudad.put('L', "Loja");
        ciudad.put('R', "Los Rios");
        ciudad.put('M', "Manabi");
        ciudad.put('V', "Morona Santiago");
        ciudad.put('N', "Napo");
        ciudad.put('S', "Pastaza");
        ciudad.put('P', "Pichincha");
        ciudad.put('Y', "Santa Elena");
        ciudad.put('J', "Santo Domingo de los Tsachilas");
        ciudad.put('K', "Sucumbios");
        ciudad.put('T', "Tungurahua");
        ciudad.put('Z', "Zamora Chinchipe");
    }

}
