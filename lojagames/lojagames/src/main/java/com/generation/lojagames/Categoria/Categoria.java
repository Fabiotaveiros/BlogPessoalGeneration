package com.generation.lojagames.Categoria;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.lojagames.model.Produtos;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 2, max = 15)
	private String tipo;
	
	@NotBlank
	@Size(min = 2, max = 255)
	private String descricao;

	// mappedBy sempre usamos o nome do Atribudo da classe que queremos relacionar
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE) //sempre vira com o cascadeType Remove (Receita de bolo kkk)
	@JsonIgnoreProperties("categoria")
	private List<Produtos> produtos;
	
	public long getId() {
		return id;
	}

	/**
	 * @return the produtos
	 */
	public List<Produtos> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
