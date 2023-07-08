public class Alumno {

    private String nombre;
    private String apellido;
    private int dni;
    private String mail;
    private int comision;

    public Alumno(String nombre, String apellido, int dni, String mail, int comision) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        this.comision = comision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return '\n'+"   Alumno:" + '\n' +
                "   Nombre = " + nombre + '\n' +
                "   Apellido = " + apellido + '\n' +
                "   DNI = " + dni + '\n' +
                "   Mail = " + mail + '\n' +
                "   Comisión = " + comision +
                '\n' +
                "----------------------------------------------------------------------";
    }
}
