package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="mensaje")
public class Mensaje {
	@Column(name="id",nullable=false)
	@Id
	private int codigo;
	
	@JoinColumn(name="emisor", referencedColumnName = "id")
	private Usuario emisor;
	
	@JoinColumn(name="receptor", referencedColumnName = "id")
	private Usuario receptor;
	
	@Column(nullable=false)
	private String contenido;
	
	
}
