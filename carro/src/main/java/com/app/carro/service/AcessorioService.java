package com.app.carro.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.carro.entity.Acessorio;
import com.app.carro.repository.AcessorioRepository;

@Service
public class AcessorioService {
	@Autowired
	private AcessorioRepository acessorioRepository;
	
	
	public String save(Acessorio acessorio) {
		//lógica para inserir no BD
		this.acessorioRepository.save(acessorio);
		return acessorio.getNome() +" salvo com sucesso";
	}
	
	public Acessorio findById(Long id) {
		//lógica para localizar no BD pelo id 
		Acessorio acessorio = this.acessorioRepository.findById(id).get(); 
		return acessorio;
	}
	
	public List<Acessorio> findAll(){
		return this.acessorioRepository.findAll();
	}
	
	public String deleteById(Long id) {
		this.acessorioRepository.deleteById(id);
		return "Acessorio deletado com sucesso";
	}
	
	public String update(Acessorio acessorio, Long id) {
		if(acessorioRepository.existsById(id)) {
			acessorio.setId(id);
			this.acessorioRepository.save(acessorio);
			return "Acessorio atualizado com sucesso!";
			}else
				throw new RuntimeException("Acessorio não encontrado, ID inválido.");
	}
}
