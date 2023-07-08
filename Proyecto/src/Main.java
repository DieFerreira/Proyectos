import javax.swing.*;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Main { // Versión 4 Comisión 23428 Grupo 3
    public static Scanner teclado = new Scanner(System.in);

        public static void main(String[] args) {
            GestionAlumno miGestion = new GestionAlumno();

            int menu = 0;
            while (menu != 100){
            menu = Integer.parseInt(JOptionPane.showInputDialog("GESTION ALUMNOS Codo a Codo" + "\n" +
                    "1.- Alta de alumnos" + "\n" +
                    "2.- Baja de alumnos" + "\n" +
                    "3.- Modificación de alumnos" + "\n" +
                    "4.- Listado de alumnos" + "\n" +
                    "100.- Salir" + "\n"));
            switch(menu){
                case 1:
                    miGestion.altaAlumno();break;
                case 2:
                    miGestion.eliminarAlumnoPorDni();break;
                case 3:
                    miGestion.modificacionAlumno();break;
                case 4:
                    miGestion.leerListaAlumnos();break;
                default:
                    String salida = "<html><h1 style=\"color:#094AA8\">Codo a Codo Inicial</h1>" +
                            "<h2 style=\"color:#094AA8\">Comisión 23428</h2>" +
                            "<h2><em><b>Profesor Saady Pacheco</em></b></h2>" +
                            "<h1 style=\"color:#094AA8\">Grupo 3</h1>" +
                            "<h3 style=\"color:#630C9C\"><b>INTEGRANTES</b></h3>" +
                            "<h3><em><b>De Cicco, Maria</b></em></h3>" +
                            "<h3><em><b>Ferreira, Diego</b></em></h3>" +
                            "<h3><em><b>González, Selva</b></em></h3>" +
                            "<h3><em><b>Klainsek, Verónica</b></em></h3>" +
                            "<h3><em><b>Martínez, Jimena</b></em></h3>" +
                            "<h3><em><b>Nicolini, Fausto</b></em></h3>" +
                            "<h3><em><b>Rodríguez, Sabrina</b></em></h3>" +
                            "<h3><em><b>Sajama, Pedro</b></em></h3></html>";

                    JTextPane textPane = new JTextPane();
                    textPane.setContentType("text/html");
                    textPane.setEditable(false);
                    textPane.setText(salida);

                    HTMLDocument doc = (HTMLDocument) textPane.getDocument();
                    HTMLEditorKit editorKit = (HTMLEditorKit) textPane.getEditorKit();
                    doc.getStyleSheet().addRule("body {text-align: center;}");

                    JScrollPane scrollPane = new JScrollPane(textPane);

                    JPanel buttonPanel = new JPanel();
                    JButton closeButton = new JButton("Cerrar");
                    buttonPanel.add(closeButton);

                    JPanel mainPanel = new JPanel(new BorderLayout());
                    mainPanel.add(scrollPane, BorderLayout.CENTER);
                    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

                    JDialog dialog = new JDialog((Dialog)null, "Gestión de Alumnos Codo a Codo", true);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setContentPane(mainPanel);

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int dialogWidth = (int)(screenSize.width * 0.25);
                    int dialogHeight = 630;
                    int dialogX = (screenSize.width - dialogWidth) / 2;
                    int dialogY = (screenSize.height - dialogHeight) / 2;
                    dialog.setBounds(dialogX, dialogY, dialogWidth, dialogHeight);

                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose();
                        }
                    });
                    dialog.setVisible(true);
            }
        }
    }
}
