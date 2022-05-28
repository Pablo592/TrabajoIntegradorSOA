package ar.edu.iua.iw3.modelo.Historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "historico")
public class Historico implements Serializable{

	public Historico(){
		this.fechaHoraRecepcion = new Date();
	}

	@Override
	public String toString() {
		return "Historico{" +
				"id_historico=" + id_historico +
				", fechaHoraRecepcion=" + fechaHoraRecepcion +
				", rawData=" + rawData +
				", categoria='" + categoria + '\'' +
				", subCategoria='" + subCategoria + '\'' +
				", indicador='" + identificador + '\'' +
				'}';
	}

	private static final long serialVersionUID = -2096655693932225923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_historico;
	
	@Column(length = 100, nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaHoraRecepcion;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="rawData")
	private RawData rawData;

	@Column(length = 100,nullable = false)
	private String categoria;
	
	@Column(length = 100,nullable = false)
	private String subCategoria;
	
	@Column(length = 100,nullable = false)
	private String identificador;



	//-------Setters and Getters---------

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

	public String getIdentificador() {
		return identificador;
	}

	public void setIndicador(String identificador) {
		this.identificador = identificador;
	}


		
}
