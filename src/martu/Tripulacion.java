package martu;

public class Tripulacion {
    private String codigo;
    private String origen;


    private Camino camino;


    public Tripulacion(String codigo, String origen) {
        this.codigo = codigo;
        this.origen = origen;
        Camino cam=new Camino();
        cam.Agregar(origen);

    }

    public Camino getCamino() {
        return camino;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getOrigen() {
        return origen;
    }




}