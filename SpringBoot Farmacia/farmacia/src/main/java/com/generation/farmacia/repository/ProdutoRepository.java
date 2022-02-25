package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produto;

	
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
		
		/*Java nao aceita herança multipla, apenas simples*/
		/*Na interface cria uma assinatura para implementar posteriormente ou seja
		 	nao se coda apenas se poem a assinatura do metodo*/
		
		public List <Produto> findAllByNomeContainingIgnoreCase(String nome);
		
		/*	find     All       By                      Nome            Containing                    Ignore Case*
		 /*Select * from   tb_produto   where    Nome( = atributo)   like %nome% 	  Ignorar Letras Maiusculas ou minusculas */

		public List<Produto> findAllByNomeOrLaboratorio (String nome, String laboratorio);
		
		public List<Produto>findAllByNomeAndLaboratorio (String nome, String laboratorio);
		
		public List<Produto> buscarProdutosEntre(BigDecimal inicio, BigDecimal fim);		
}