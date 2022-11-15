public class Tripulacion {
    private String codigo;
    private String origen;
    private String ciudadActual;
    public Tripulacion(String codigo, String origen, String ciudadActual) {
        this.codigo = codigo;
        this.origen = origen;
        this.ciudadActual = ciudadActual;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(String ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

}