package modelos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    public void LanzarProcesos() {
        String nombreProceso = JOptionPane.showInputDialog("Introduce el nombre del proceso a lanzar:");
        if (nombreProceso != null && !nombreProceso.trim().isEmpty()) {
            ProcessBuilder pb = new ProcessBuilder(nombreProceso);
            try {
                pb.start();
                JOptionPane.showMessageDialog(null, "Proceso '" + nombreProceso + "' lanzado correctamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al lanzar el proceso: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes ingresar un proceso válido.");
        }
    }

    public void MatarProcesos() {
    String pid = JOptionPane.showInputDialog("Introduce el PID del proceso que quieras matar:");
    if (pid != null && !pid.trim().isEmpty()) {
        ProcessBuilder pb = new ProcessBuilder("kill", pid);
        try {
            pb.start();
            JOptionPane.showMessageDialog(null, "El proceso ha sido terminado correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al matar el proceso: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Ingresa un PID válido.");
    }
}
    
    public List<List<String>> ListarProcesos() {
        List<List<String>> tablaProcesos = new ArrayList<>();
        ProcessBuilder pb = new ProcessBuilder("ps", "-eo", "pid,euser,comm");

        try {
            Process p = pb.start();
            BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String linea;
            while ((linea = lector.readLine()) != null) {
                List<String> filaProceso = new ArrayList<>();
                filaProceso.add(linea.substring(0, 7).trim());
                filaProceso.add(linea.substring(8, 15).trim());
                filaProceso.add(linea.substring(16).trim());
                tablaProcesos.add(filaProceso);
            }
            int codRet = p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        System.out.println("Funciona");
        return tablaProcesos;
    }
}
