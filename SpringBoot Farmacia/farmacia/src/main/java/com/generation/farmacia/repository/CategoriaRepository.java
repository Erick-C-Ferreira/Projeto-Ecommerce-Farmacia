package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	/*Java nao aceita herança multipla, apenas simples*/
	/*Na interface cria uma assinatura para implementar posteriormente ou seja
	 	nao se coda apenas se poem a assinatura do metodo*/
	
	public List <Categoria> findAllByCategoriaContainingIgnoreCase(String categoria);
	
	/*	find     All       By                        Categoria            Containing                    Ignore Case*
	 /*Select * from   tb_categoria   where    Categoria( = atributo)   like %categoria%   Ignorar Letras Maiusculas ou minusculas */

}
