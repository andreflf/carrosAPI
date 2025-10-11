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
import com.app.carro.entity.Acessorio;
import com.app.carro.service.AcessorioService;

@RestController
@RequestMapping("api/carro/acessorio")
@CrossOrigin("*")
public class AcessorioController {

	@Autowired //sempre do obj da service
	private AcessorioService acessorioService;
	
	//@PreAuthorize("hasAuthority('ADMIN') OR hasRole('USER')")
	@PreAuthorize("hasAuthority('ADMIN')") //para que apenas um user com role ADMIN possa consumir esse endpoint (evitar "ataques" de fora)
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Acessorio acessorio){
		
		try {	
			String mensagem = this.acessorioService.save(acessorio);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Erro ao salvar dados!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Acessorio> findById(@PathVariable Long id){ //PathVariable pega o que colocar no Id da URL no POSTMAN
		
		try {	
			Acessorio acessorio = this.acessorioService.findById(id);
			return new ResponseEntity<>(acessorio, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/findAll")
	public ResponseEntity<List<Acessorio>> findAll(){
		try {
			List<Acessorio> listaDeAcessorios = this.acessorioService.findAll();
			return new ResponseEntity<>(listaDeAcessorios, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasAuthority('ADMIN')") //para que apenas um user com role ADMIN possa consumir esse endpoint (evitar "ataques" de fora)
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		try {
			String retorno = this.acessorioService.deleteById(id);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao deletar", HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasAuthority('ADMIN')") //para que apenas um user com role ADMIN possa consumir esse endpoint (evitar "ataques" de fora)
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Acessorio acessorio, @PathVariable Long id) {
		try {
			String retorno = this.acessorioService.update(acessorio, id);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Nao foi possível atualizar os dados", HttpStatus.BAD_REQUEST);
		}
	}
}
