package com.generation.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;		/*Classe categoria é o lado principal da model*/
import javax.persistence.Id;					 /* Sem ela nao faz sentido ter a outra*/
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/* A anotação @NotBlank é utilizada para validar se o campo não esta vazio,
	   logo ela não permite nulo ou vazio.*/
	
	@NotBlank(message = "Categoria é obrigatorio")
	private String categoria;
	
	private String descricao; 
										/*cascade = tudo que eu alterar em categoria ira se propagar em produto*/
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) /*@OneToMany = "Um para muitos"*/
	@JsonIgnoreProperties("categoria")
	private List<Produto>produto; /*É uma lista pois se eu quero ter varios produtos, eu preciso ter uma lista de produtos*/
									/*Dica: caso aparesça o erro MipedBY no console significa que foi esquecido de fazer a relação do outro lado na classe produto*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
