package Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	@Column(name="id",nullable=false)
	@Id
	private int id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emisor")
	private List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
}
