package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import modelos.Modelo;
import vistas.Vista;


public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setTitle("Administrador de procesos");
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
        this.vista.btnMatar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnActualizar) {
            System.out.println("Actualizando...");
            List<List<String>> matrizProcesos = modelo.ListarProcesos();
            System.out.println(matrizProcesos);
            
            DefaultTableModel model = new DefaultTableModel();
            
            for (int j=0; j<3; j++) {
                model.addColumn(matrizProcesos.get(0).get(j));
            }
            for (int i=1; i < matrizProcesos.size(); i++) {
                model.addRow(new Object[]{matrizProcesos.get(i).get(0), matrizProcesos.get(i).get(1), matrizProcesos.get(i).get(2)});
            }
            vista.tblProcesos.setModel(model);
            vista.txtTotalProcesos.setText(Integer.toString(matrizProcesos.size()) + " procesos");
            
        } else if (e.getSource() == vista.btnNuevo) {
            modelo.LanzarProcesos();
        } else if (e.getSource() == vista.btnMatar) {
            modelo.MatarProcesos();
        }
    }
}
