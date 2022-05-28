package ar.edu.iua.iw3.modelo.Historico;

import java.util.Date;

public class HistoricoDTO {

    public HistoricoDTO(Historico h){
        this.categoria = h.getCategoria();
        this.fechaHoraRecepcion = h.getFechaHoraRecepcion();
        this.rawData = h.getRawData();
        this.subCategoria = h.getSubCategoria();
        this.identificador = h.getId_historico();
    }

    @Override
    public String toString() {
        return "HistoricoDTO{" +
                "fechaHoraRecepcion=" + fechaHoraRecepcion +
                ", rawData=" + rawData +
                ", categoria='" + categoria + '\'' +
                ", subCategoria='" + subCategoria + '\'' +
                ", identificador='" + identificador + '\'' +
                '}';
    }

    private Date fechaHoraRecepcion;

    private RawData rawData;

    private String categoria;

    private String subCategoria;

    private Long identificador;



    //-------Setters and Getters---------

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

    public void setIndicador(Long identificador) {
        this.identificador = identificador;
    }



}

