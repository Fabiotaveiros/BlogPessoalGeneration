package com.generation.lojagames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.lojagames.Categoria.Categoria;

@Entity //companheiro do create table
@Table(name = "tb_produtos") // create table
public class Produtos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private long id;
	
	@NotBlank 								//não nulo e não " "
	@Size(min = 1, max = 100) 				// ?? la da linha 21 é igual a 100
	private String nome; 					//Varchar(??)
	
	@Min(1) 								// valor minimo 1
	private double preco;
	
	private boolean disponivel;
	
											//ManyToOne criando uma chave estrangeira (categoria_id)
	@ManyToOne 								//O ultimo nome da anotação refere-se a quantidade de dados que eu estou recebendo
	@JsonIgnoreProperties("produtos")
	private Categoria categoria; 			//O tipo dado deve ser o mesmo nome da classe que eu quero relacionar 

	
											//Não esquecer dos getters and setters da categoria
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * @return the disponivel
	 */
	public boolean isDisponivel() {
		return disponivel;
	}

	/**
	 * @param disponivel the disponivel to set
	 */
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}
