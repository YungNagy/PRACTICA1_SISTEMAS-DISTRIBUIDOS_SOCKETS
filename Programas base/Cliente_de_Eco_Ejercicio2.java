import java.io.*;
import java.net.*;

public class Cliente_de_Eco_Ejercicio2 {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Uso desde consola: java Cliente_de_Eco <nombre de host (computadora)> <numero puerto>");
            System.exit(1);
        }

        String nombreHost = args[0];
        int numeroPuerto = Integer.parseInt(args[1]);

        try (
            Socket socketEco = new Socket(nombreHost, numeroPuerto);
            PrintWriter escritor = new PrintWriter(socketEco.getOutputStream(), true);
            
            BufferedReader lector = new BufferedReader(new InputStreamReader(socketEco.getInputStream()));
            BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in))
        ) {
            String EcoServidor,Escritura;
            int num;
            
            while ((Escritura = teclado.readLine()) != null) {
                num=Integer.parseInt(teclado.readLine());
                if(num==0){
                    break;
                }else{
                escritor.println(Integer.parseInt(teclado.readLine()));
               EcoServidor = lector.readLine();
               System.out.println("El numero es:  " + EcoServidor);
                }
               
            }
        } catch (UnknownHostException e) {
            System.err.println("No conozco al host " + nombreHost);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("no se pudo obtener E/S para la conexion " +
                nombreHost);
            System.exit(1);
        } 
    }
}
