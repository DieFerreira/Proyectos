import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionAlumno {
    List<Alumno> losAlumnos = new ArrayList<>();
    GestionArchivo miArchivo = new GestionArchivo();

    public GestionAlumno() {
        this.losAlumnos = new ArrayList<>();
       /* this.losAlumnos = miArchivo.leer();*/

    }
    public void altaAlumno() {
        int menu = 0, dni, comision, validacionComision = 1, validacionNombre = 1, validacionApellido = 1, validacionDni = 1, validacionCorreo = 1;
        String nombre, apellido, mail;
        do {
            comision = Integer.parseInt(JOptionPane.showInputDialog(null, "Comisión con la que desea ingresar alumnos:" + "\n" + "(desde la 23420 hasta 23450)", "COMISION", JOptionPane.PLAIN_MESSAGE));
            validacionComision = validarComision(comision);
            if (validacionComision == 0) {
                JOptionPane.showMessageDialog(null, "Ingrese una comisión válida.", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
            }
        } while (validacionComision == 0);

        do {
            do {
                nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del alumno", "NOMBRE", JOptionPane.PLAIN_MESSAGE);
                validacionNombre = validarNombre(nombre);
                if (validacionNombre == 0) {
                    JOptionPane.showMessageDialog(null,"Ingrese un nombre válido." + "\n" + "De 2 a 20 caracteres.", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                }
            } while (validacionNombre == 0);
            do {
                apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del alumno", "APELLIDO", JOptionPane.PLAIN_MESSAGE);
                validacionApellido = validarApellido(apellido);
                if (validacionApellido == 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un apellido válido." + "\n" + "De 2 a 20 caracteres.", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                }
            } while (validacionApellido == 0);
            do {
                dni = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el DNI del alumno:", "DNI", JOptionPane.PLAIN_MESSAGE));
                validacionDni = validarDni(dni);
                if (validacionDni == 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un DNI válido." + "\n" + "Hasta 8 dígitos.", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                }
            } while (validacionDni == 0);
            do {
                mail = JOptionPane.showInputDialog(null, "Ingrese el correo electrónico del alumno", "CORREO ELECTRÓNICO", JOptionPane.PLAIN_MESSAGE);
                validacionCorreo = validarCorreo(mail);
                if (validacionCorreo == 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un correo válido.", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                }
            } while (validacionCorreo == 0);

            Alumno nuevoAlumno = new Alumno(nombre, apellido, dni, mail, comision);
            losAlumnos.add(nuevoAlumno);
            miArchivo.altaRegistro(nombre, apellido, dni, mail, comision);

            menu = Integer.parseInt(JOptionPane.showInputDialog(null, "Desea continuar con el ingreso de alumnos?  1. para continuar" + "\n" + "100. para volver al menú principal", "MODIFICACION", JOptionPane.PLAIN_MESSAGE));


        } while (menu != 100);
    }
    public void leerListaAlumnos(){
        int opcion = 0;
        opcion = Integer.parseInt(JOptionPane.showInputDialog("     LISTADO DE ALUMNOS" + "\n" +
                "1. Listado de archivo predefinido" + "\n" +
                "2. Listado desde registros" + "\n" +
                "3. Listado desde el archivo" + "\n" +
                "100. Volver al menú anterior"));

        switch (opcion){
            case 1:
                ArrayList<Alumno> listadoPredefinido = new ArrayList<>();

                listadoPredefinido.add(new Alumno("Maria","De Cicco",29900000,"deciccomaria@codoacodo.com.ar",23428));
                listadoPredefinido.add(new Alumno("Selva","Gonzalez",36500000,"gonzalezselva@codoacodo.com.ar",23428));
                listadoPredefinido.add(new Alumno("Sabrina","Rodriguez",35200000,"rodriguezsabrina@codoacodo.com.ar",23428));
                listadoPredefinido.add(new Alumno("Verónica","Klainsek",29000000,"klainsekveronica@codoacodo.com.ar",23428));
                listadoPredefinido.add(new Alumno("Pedro","Sajama",29400000,"sajamapedro@codoacodo.com.ar",23428));
                listadoPredefinido.add(new Alumno("Fausto","Nicolini",45000000,"nicolinifausto@codoacodo.com.ar",23428));
                listadoPredefinido.add(new Alumno("Jimena","Martínez",36500000,"martinezjimena@codoacodo.com.ar",23428));
                listadoPredefinido.add(new Alumno("Diego","Ferreira",25800000,"ferreiradiego@codoacodo.com.ar",23428));

                StringBuilder sb = new StringBuilder();
                for (Alumno alumno : listadoPredefinido) {
                    sb.append(alumno.toString()).append("\n");
                }

                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                JScrollPane scrollPane = new JScrollPane(textArea);

                JPanel buttonPanel = new JPanel();
                JButton closeButton = new JButton("Cerrar");
                buttonPanel.add(closeButton);

                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.add(scrollPane, BorderLayout.CENTER);
                mainPanel.add(buttonPanel, BorderLayout.SOUTH);

                JDialog dialog = new JDialog((Dialog)null, "Lista predeterminada de Alumnos", true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setContentPane(mainPanel);

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int dialogWidth = (int)(screenSize.width * 0.25);
                int dialogHeight = 450;
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

            case 2:
                StringBuilder sb2 = new StringBuilder();
                for (Alumno alumno : losAlumnos) {
                    sb2.append(alumno.toString()).append("\n");
                }

                JTextArea textArea2 = new JTextArea(sb2.toString());
                textArea2.setLineWrap(true);
                textArea2.setWrapStyleWord(true);

                JScrollPane scrollPane2 = new JScrollPane(textArea2);

                JPanel buttonPanel2 = new JPanel();
                JButton closeButton2 = new JButton("Cerrar");
                buttonPanel2.add(closeButton2);

                JPanel mainPanel2 = new JPanel(new BorderLayout());
                mainPanel2.add(scrollPane2, BorderLayout.CENTER);
                mainPanel2.add(buttonPanel2, BorderLayout.SOUTH);

                JDialog dialog2 = new JDialog((Dialog)null, "Lista predeterminada de Alumnos", true);
                dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog2.setContentPane(mainPanel2);

                Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
                int dialogWidth2 = (int)(screenSize2.width * 0.25);
                int dialogHeight2 = 450;
                int dialogX2= (screenSize2.width - dialogWidth2) / 2;
                int dialogY2 = (screenSize2.height - dialogHeight2) / 2;
                dialog2.setBounds(dialogX2, dialogY2, dialogWidth2, dialogHeight2);

                closeButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog2.dispose();
                    }
                });

                dialog2.setVisible(true);

            case 3:
                GestionArchivo.leerRegistros();break;

            default:
        }



    }
    public void eliminarAlumnoPorDni() {
        int dniEliminar = 0;
        boolean encontrado = false;

        Alumno alumnoAEliminar = null;

        dniEliminar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese DNI del alumno a dar de baja: "+ JOptionPane.PLAIN_MESSAGE));

        for (Alumno alumno : losAlumnos) {
            if (alumno.getDni() == dniEliminar) {
                encontrado = true;
                alumnoAEliminar = alumno;
                break; // opcional: si solo esperas un objeto con ese atributo, puedes salir del bucle
            }
        }

        // Verifica si se encontró el objeto a eliminar
        if (encontrado) {
            // Construye el mensaje con los datos del objeto a eliminar
            String mensaje = "Atención: se procede a eliminar el registro del siguiente alumno:\n"
                    + "Nombre: " + alumnoAEliminar.getNombre() + "\n"
                    + "Apellido: " + alumnoAEliminar.getApellido() + "\n"
                    + "Correo: " + alumnoAEliminar.getMail() + "\n"
                    + "DNI: " + alumnoAEliminar.getDni() + "\n"
                    + "Comision: " + alumnoAEliminar.getComision();

            // Muestra el cuadro de diálogo con el mensaje y los botones "Aceptar" y "Cancelar"
            int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación de borrado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    new Object[]{"Aceptar", "Cancelar"}, null);

            if (opcion == JOptionPane.YES_OPTION) {
                // El usuario seleccionó "Aceptar"
                // Elimina el objeto del ArrayList
                losAlumnos.remove(alumnoAEliminar);
                miArchivo.bajaRegistro(alumnoAEliminar.getNombre(),alumnoAEliminar.getApellido(), alumnoAEliminar.getDni(), alumnoAEliminar.getMail(), alumnoAEliminar.getComision());
                JOptionPane.showMessageDialog(null,"El registro del alumno fue eliminado exitosamente.", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "El borrado del registro del alumno fue cancelado.", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un objeto con el atributo especificado.", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

        }
    }
    public void modificacionAlumno() {

        List<Alumno> listaModificada = new ArrayList<>();

        int dniModificar = 0,opcionMenuModificar=0;
        boolean encontrado = false;

        Alumno alumnoAModificar= null;

        dniModificar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese DNI del alumno a modificar: " + JOptionPane.PLAIN_MESSAGE));

        for (Alumno alumno : losAlumnos) {
            if (alumno.getDni() == dniModificar) {
                encontrado = true;
                alumnoAModificar = alumno;
                break; // opcional: si solo esperas un objeto con ese atributo, puedes salir del bucle
            }
        }

        // Verifica si se encontró el objeto a eliminar
        if (encontrado) {
            // Construye el mensaje con los datos del objeto a eliminar
            String mensaje = "Atención: se procede a modificar datos del alumno:\n"
                    + "Nombre: " + alumnoAModificar.getNombre() + "\n"
                    + "Apellido: " + alumnoAModificar.getApellido() + "\n"
                    + "DNI: " + alumnoAModificar.getDni() + "\n"
                    + "Correo: " + alumnoAModificar.getMail() + "\n"
                    + "Comision: " + alumnoAModificar.getComision() + "\n";

            opcionMenuModificar = Integer.parseInt(JOptionPane.showInputDialog(null, "MODIFICACION DE ALUMNO" + "\n" +
                    "Ingrese modificación:" + "\n" +
                    "1.Nombre" + "\n" +
                    "2.Apellido" + "\n" +
                    "3.DNI" + "\n" +
                    "4.Correo" + "\n" +
                    "5.Comisión" + "\n" +
                    "100.Volver al menú anterior" + JOptionPane.PLAIN_MESSAGE));

            String modificado, nombre, apellido, mail;
            int dni, comision;

            switch (opcionMenuModificar) {
                case 1:
                    nombre = JOptionPane.showInputDialog("Nombre :");
                    alumnoAModificar.setNombre(nombre);
                    System.out.println("Nombre modificado");
                    break;
                case 2:
                    apellido = JOptionPane.showInputDialog("Apellido :");
                    alumnoAModificar.setApellido(apellido);break;
                case 3:
                    dni = Integer.parseInt(JOptionPane.showInputDialog("DNI: "));
                    alumnoAModificar.setDni(dni);break;
                case 4:
                    mail = JOptionPane.showInputDialog("Correo: ");
                    alumnoAModificar.setMail(mail);break;
                case 5:
                    comision = Integer.parseInt(JOptionPane.showInputDialog("Comisión: "));
                    alumnoAModificar.setComision(comision);break;
                default:
                    JOptionPane.showMessageDialog(null, "No se realizó el registro");
            }
        }
    }

    //--------------------------------------------VALIDACIONES---------------------------------------------//
    public static int validarNombre(String nombre) {

        if (nombre.length() > 1 && nombre.length() < 21) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int validarApellido(String apellido) {

        if (apellido.length() > 1 && apellido.length() < 21) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int validarDni(int dni) {

        if (dni > 15000000 && dni < 47000000) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int validarCorreo(String mail) {

        Pattern pattern = Pattern.compile("@");
        Matcher matcher = pattern.matcher(mail);
        boolean validMail = matcher.find();

        if (validMail) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int validarComision(int comision) {

        if (comision >= 23420 && comision <= 23450) {
            return 1;
        } else {
            return 0;
        }
    }
    //-----------------------------------------------------------------------------------------------------//


}
