package com.volley.profuturo.en501863.learningproyectv07.model;

/**
 * Created by edrag on 15/10/2017.
 */

public class EmpleadoInformacion {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    //private String  COLUMNS_USUARIO  COLUMNS_NUMERO_EMPLEADO  COLUMNS_CONTRASENIA  COLUMNS_FECHA_INICIO_SESION
    private int ID;
    private String usuario;
    private String numEmpleado;
    private String contrasenia;
    private String fecha_inicio;
    private String imagePath;
    //private boolean isChecked;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

//    public EmpleadoInformacion() {
//        isChecked = false;
//    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

//    public boolean isChecked() {
//        return isChecked;
//    }

//    public void setChecked(boolean checked) {
//        isChecked = checked;
//    }


    @Override
    public String toString() {
        return "EmpleadoInformacion{" +
                "usuario='" + usuario + '\'' +
                ", numEmpleado='" + numEmpleado + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
