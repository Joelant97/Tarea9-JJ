package modelos;

import java.util.ArrayList;




public class Encuesta {
    private int id;
    private String nombre;
    private String sector;
    private String nivelEducativo;
    private double latitud;
    private double longitud;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }


    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }


    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Encuesta() {
    }

    public Encuesta(int id, String nombre, String sector, String nivelEducativo, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.sector = sector;
        this.nivelEducativo = nivelEducativo;
        this.latitud = latitud;
        this.longitud = longitud;
    }


    public static ArrayList<Encuesta> getListadoEncuesta() {
        return listadoEncuesta;
    }

    public static void setListadoEncuestados(ArrayList<Encuesta> listadoEncuesta) {
        Encuesta.listadoEncuesta = listadoEncuesta;
    }


    public static ArrayList<Encuesta> listadoEncuesta;


}
