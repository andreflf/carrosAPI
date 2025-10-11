package com.app.carro.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.carro.entity.Marca;
import com.app.carro.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	
	public String save(Marca marca) {
		//lógica para inserir no BD
		this.marcaRepository.save(marca);
		return marca.getNome() +" salvo com sucesso";
	}
	
	public Marca findById(Long id) {
		//lógica para localizar no BD pelo id 
		Marca marca = this.marcaRepository.findById(id).get(); 
		return marca;
	}
	
	public List<Marca> findAll(){
		return this.marcaRepository.findAll();
	}
	
	public String deleteById(Long id) {
		this.marcaRepository.deleteById(id);
		return "Marca deletada com sucesso";
	}
	
	public String update(Marca marca, Long id) {
		if(marcaRepository.existsById(id)) {
			marca.setId(id);
			this.marcaRepository.save(marca);
			return "Marca atualizada com sucesso!";
			}else
				throw new RuntimeException("Marca não encontrada, ID inválido.");
	}
}
