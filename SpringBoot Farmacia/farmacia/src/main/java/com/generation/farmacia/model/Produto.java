package com.generation.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity /*Define uma entidade "tabela*/
@Table(name = "tb_produtos") /*Esta declarando o nome da tabela, caso nao coloque, usara o nome da classe e por boas praticas se usa sempre "tb_<nome da tabela>*/
public class Produto {
	
	@Id /*Esta dizendo para o mySQL ou outro banco de dados, que o ID sera á chave primaria*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) /* Esta dizendo que o id sera valor de auto incremento e transmite a reponsabilidade do banco de dados de gerar os Ids*/
	private Long id;
	
	/* A anotação @NotBlank é utilizada para validar se o campo não esta vazio,
	   logo ela não permite nulo ou vazio.*/
	
	@NotBlank(message = "Nome é obrigatório!") /*@NotBlank nao permite espaços vazios ou nulos*/
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória!")  /*@NotBlank nao permite espaços vazios ou nulos*/
	private String descricao;
	
	private int quantidade;
	
	private String laboratorio;
	
	/*A anotação @JsonFormat(shape = JsonFormat.Shape.STRING) é utilizada
	 * para formatar o valor do preço do produto como uma String. Desta forma,
	 * da para visualizar a parte decimal do preço mesmo sendo 00.*/
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "Preço é obrigatório!") /*@NotNull permite espaços vazios*/
	@Positive(message = "Digite um valor maior do que zero") /*@Positive sera para forçar o valor a ser positivo*/
	private BigDecimal preco;
	
	private String foto;
	
	@ManyToOne  /*@ManyToOne = "Muitos para Um" e nao possui parametros pois quem manda é a classe categoria*/
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	/*Getters And Setters*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	
}
