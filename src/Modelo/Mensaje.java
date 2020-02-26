package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mensaje")
public class Mensaje {
	@Column(name="id",nullable=false)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@ManyToOne
	@JoinColumn(name="emisor", referencedColumnName = "id")
	private Usuario emisor;
	
	@ManyToOne
	@JoinColumn(name="receptor", referencedColumnName = "id")
	private Usuario receptor;
	
	@Column(nullable=false)
	private String contenido;
	
	
}
