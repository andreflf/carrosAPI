package com.app.carro.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.carro.entity.Marca;
import com.app.carro.service.MarcaService;

@RestController
@RequestMapping("/api/carro/marca")
@CrossOrigin("*")
public class MarcaController {
	
	@Autowired //sempre do obj da service
	private MarcaService marcaService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Marca marca){
		
		try {	
			String mensagem = this.marcaService.save(marca);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Erro ao salvar dados!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Marca> findById(@PathVariable Long id){ //PathVariable pega o que colocar no Id da URL no POSTMAN
		
		try {	
			Marca marca = this.marcaService.findById(id);
			return new ResponseEntity<>(marca, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Marca>> findAll(){
		try {
			List<Marca> listaDeMarcas = this.marcaService.findAll();
			return new ResponseEntity<>(listaDeMarcas, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		try {
			String retorno = this.marcaService.deleteById(id);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao deletar", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Marca marca, @PathVariable Long id) {
		try {
			String retorno = this.marcaService.update(marca, id);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Nao foi possível atualizar os dados", HttpStatus.BAD_REQUEST);
		}
	}
	
}
