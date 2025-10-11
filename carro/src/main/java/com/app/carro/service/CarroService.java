package com.app.carro.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.carro.entity.Carro;
import com.app.carro.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
	
	public String save(Carro carro) {
		//lógica para inserir no BD
		this.carroRepository.save(carro);
		return carro.getNome() +" salvo com sucesso";
	}
	
	public Carro findById(Long id) {
		//lógica para localizar no BD pelo id 
		Carro carro = this.carroRepository.findById(id).get(); 
		return carro;
	}
	
	public List<Carro> findAll(){
		return this.carroRepository.findAll();
	}
	
	public String deleteById(Long id) {
		this.carroRepository.deleteById(id);
		return "Carro deletado com sucesso";
	}
	
	public String update(Carro carro, Long id) {
		if(carroRepository.existsById(id)) {
			carro.setId(id);
			this.carroRepository.save(carro);
			return "Carro atualizado com sucesso!";
			}else
				throw new RuntimeException("Carro não encontrado, ID inválido.");
	}
}
