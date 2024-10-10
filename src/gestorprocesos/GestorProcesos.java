package gestorprocesos;

import controladores.Controlador;
import modelos.Modelo;
import vistas.Vista;


public class GestorProcesos {
    
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
        
        vista.setVisible(true);
        vista.btnActualizar.doClick();
    }
}
