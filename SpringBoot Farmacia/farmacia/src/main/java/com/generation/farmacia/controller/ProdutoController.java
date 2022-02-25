package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired /*P maiusculos é interface e p minusculo é o objeto da interface*/
	private ProdutoRepository produtoRepository;
	
	@Autowired /*Para cada injeção de depencia usar o proprio @Autowired se não dara erro*/
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){ /*select * from*/
		return ResponseEntity.ok(produtoRepository.findAll()); /*Status ok = 200*/
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) /*map é um tipo de optional e impede de dar erro*/ 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}") /*Se nao declarar o nome pode achar que o caminho da Id, 
	*e este sseria o mesmo entre {é o nome da categoria} 
	*Dica:nao se pode ter 2 caminhos iguais, então seria para evitar dupicidade*/
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping /*é o metodo que vai persistir o dado no banco.
	*(O @PathVariable é utilizado quando o valor da variavel é passada diretamente na URL)*/
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
								/*existById vai chegar se o Id existe que é o ID do objeto que esta dentro da categoria 
								 * produto e significa que estou pegano o objeto categoria que esta dentro do objeto produto*/
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		/*nao foi usado Else pois se ele existe ja vai gravar, se nao ele retornara um badRequest*/
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto){	
		if (produtoRepository.existsById(produto.getId())) {
			/*Primeiro checar se o produto existe e se existe...*/
			
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
					/*Ai checara a Categoria e se a categoria existir...		Ai atualiza o produto...*/
			else 
				return ResponseEntity.badRequest().build();
					/*Se não a categoria nao existe*/
		
		}return ResponseEntity.notFound().build();
		 /*E se o produto nao existir ja ira vir direto para o notFound*/
	}	
			
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id){
		
		return produtoRepository.findById(id)
				.map(resposta ->{ produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	/*Consulta por nome ou laboratorio*/
	@GetMapping("/nome/{nome}/oulaboratorio/{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeOuLaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findAllByNomeOrLaboratorio(nome, laboratorio));
	}
	
	/*Consulta por nome e laboratorio*/
	@GetMapping("/nome/{nome}/elaboratorio/{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeELaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findAllByNomeAndLaboratorio(nome, laboratorio));
	}
	
	/*Consulta por preço entre dois valores (BetWeen)*/
	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Produto>> getByPrecoEntre(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(produtoRepository.buscarProdutosEntre(inicio, fim));
	} 
	
}
