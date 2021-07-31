package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "Postagens")
public class Postagens {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@NotNull
@Size (min = 5, max = 255)
private String título;
@NotNull
@Size (min = 10, max = 500)
private String texto;

@Temporal (TemporalType.TIMESTAMP)
private Date date = new java.sql.Date(System.currentTimeMillis());


@ManyToOne
@JsonIgnoreProperties("postagem")
private Tema tema;

public Tema getTema() {
	return tema;
}
public void setTema(Tema tema) {
	this.tema = tema;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getTítulo() {
	return título;
}
public void setTítulo(String título) {
	this.título = título;
}
public String getTexto() {
	return texto;
}
public void setTexto(String texto) {
	this.texto = texto;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}








}
