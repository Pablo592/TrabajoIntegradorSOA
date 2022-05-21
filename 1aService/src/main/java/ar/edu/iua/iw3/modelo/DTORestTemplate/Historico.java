package ar.edu.iua.iw3.modelo.DTORestTemplate;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Historico implements Serializable{
	
	@Override
	public String toString() {
		return "Historico [id_historico=" + id_historico + ", fechaHoraRecepcion=" + fechaHoraRecepcion + ", rawData="
				+ rawData + ", categoria=" + categoria + ", subCategoria=" + subCategoria + ", indicador=" + indicador
				+ "]";
	}

	public Historico(){}


	private long id_historico;

	private Date fechaHoraRecepcion;

	private String rawData;

	private String categoria;

	private String subCategoria;

	private String indicador;

	

	//-------Setters and Getters---------

	public Historico getClase(String json){
		Historico h = new Historico();
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		h = gson.fromJson(json,h.getClass());
		return h;
	}


	public List<Historico> getListClase(String json){
		List<Historico> h = new ArrayList<Historico>();
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		h = gson.fromJson(json,h.getClass());
		return h;
	}

	public long getId_historico() {
		return id_historico;
	}

	public void setId_historico(long id_historico) {
		this.id_historico = id_historico;
	}

	public Date getFechaHoraRecepcion() {
		return fechaHoraRecepcion;
	}

	public void setFechaHoraRecepcion(Date fechaHoraRecepcion) {
		this.fechaHoraRecepcion = fechaHoraRecepcion;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String string) {
		this.rawData = string;
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

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}


}
