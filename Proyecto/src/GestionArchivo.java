import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionArchivo {

    private static final String ARCHIVOALUMNOS = "totalAlumnos.txt";

    public static List<String> leerRegistros(){

        List<String> registros = new ArrayList<>();

        try(BufferedReader leer = new BufferedReader(new FileReader(ARCHIVOALUMNOS))){
            String linea;
            StringBuilder listado = new StringBuilder("<html><h1 style=\"color:#094AA8\">LISTADO DE ALUMNOS DESDE ARCHIVO</h1>" +

                    "<h2><em><b>Profesor Saady Pacheco</em></b></h2>");

            while((linea = leer.readLine()) != null){
                registros.add(linea);

                for (String registro : registros) {
                    String[] datos = registro.split(",");
                    String nombre = datos[0];
                    String apellido = datos[1];
                    String dni = datos[2];
                    String correo = datos[3];
                    String comision = datos[4];

                    listado.append("<h4><em><b>Nombre: ").append(nombre).append(",</b></em></h4>")
                            .append("<h4><em><b>Apellido: ").append(apellido).append(",</b></em></h4>")
                            .append("<h4><em><b>DNI: ").append(dni).append(",</b></em></h4>")
                            .append("<h4><em><b>Correo: ").append(correo).append(",</b></em></h4>")
                            .append("<h4><em><b>Comisión: ").append(comision).append(",</b></em></h4>")
                            .append("<h4>------------------------------------------------------------------------------</h4>");
                }
            }

            listado.append("</html>");

            JTextPane textPane = new JTextPane();
            textPane.setContentType("text/html");
            textPane.setEditable(false);
            textPane.setText(listado.toString());

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
        } catch(IOException e){
            System.out.println("No se realizó la lectura del registro" + e.getMessage());
        }
        return registros;
    }



    public void altaRegistro(String nombre, String apellido, int dni, String correo, int comision){
        try(BufferedWriter buffer = new BufferedWriter( new FileWriter(ARCHIVOALUMNOS,true))){
            buffer.write(nombre + "," + apellido + "," + dni + "," + correo + "," + comision);
            buffer.newLine();
        }catch(IOException e){
            System.out.println("No se realizó el alta del registro del alumno" + e.getMessage());
        }
    }
    public void bajaRegistro(String nombre, String apellido, int dni, String correo, int comision){
        try(BufferedWriter buffer = new BufferedWriter( new FileWriter(ARCHIVOALUMNOS,true))){
            buffer.write(nombre + "," + apellido + "," + dni + "," + correo + "," + comision);
            buffer.newLine();
        }catch(IOException e){
            System.out.println("No se realizó el alta del registro del alumno" + e.getMessage());
        }
    }

}
