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
import com.app.carro.entity.Carro;
import com.app.carro.service.CarroService;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin("*") //aceitar conexao com os endpoints de qualquer origem
public class CarroController {
	
	@Autowired //sempre do obj da service
	private CarroService carroService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Carro carro){
		
		try {	
			String mensagem = this.carroService.save(carro);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Erro ao salvar dados!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Carro> findById(@PathVariable Long id){ //PathVariable pega o que colocar no Id da URL no POSTMAN
		
		try {	
			Carro carro = this.carroService.findById(id);
			return new ResponseEntity<>(carro, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasAuthority('ADMIN')") //para que apenas um user com role ADMIN possa consumir esse endpoint (evitar "ataques" de fora)
	@GetMapping("/findAll")
	public ResponseEntity<List<Carro>> findAll(){
		try {
			List<Carro> listaDeCarros = this.carroService.findAll();
			return new ResponseEntity<>(listaDeCarros, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		try {
			String retorno = this.carroService.deleteById(id);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao deletar", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Carro carro, @PathVariable Long id) {
		try {
			String retorno = this.carroService.update(carro, id);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Nao foi possível atualizar os dados", HttpStatus.BAD_REQUEST);
		}
	}
}
