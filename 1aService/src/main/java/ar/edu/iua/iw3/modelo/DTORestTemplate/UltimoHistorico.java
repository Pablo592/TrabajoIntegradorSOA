package ar.edu.iua.iw3.modelo.DTORestTemplate;

import java.io.Serializable;
import java.util.Date;

public class UltimoHistorico implements Serializable {

    @Override
    public String toString() {
        return "UltimoHistorico{" +
                "fechaHoraRecepcion=" + fechaHoraRecepcion +
                ", rawData=" + rawData +
                ", categoria='" + categoria + '\'' +
                ", subCategoria='" + subCategoria + '\'' +
                ", identificador=" + identificador +
                ", ubicacion=" + ubicacion +
                '}';
    }

    private Date fechaHoraRecepcion;

    private RawData rawData;

    private String categoria;

    private String subCategoria;

    private Long identificador;

    private Ubicacion ubicacion;

    public Date getFechaHoraRecepcion() {
        return fechaHoraRecepcion;
    }

    public void setFechaHoraRecepcion(Date fechaHoraRecepcion) {
        this.fechaHoraRecepcion = fechaHoraRecepcion;
    }

    public RawData getRawData() {
        return rawData;
    }

    public void setRawData(RawData rawData) {
        this.rawData = rawData;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}

