package com.app.carro.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.carro.auth.Usuario;
import com.app.carro.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	public List<Usuario> findAll(){
		List<Usuario> lista = this.usuarioRepository.findAll();
		for(int i=0; i<lista.size(); i++) {
			lista.get(i).setPassword(""); //seta as senhas para nao leva-las criptografadas junto.
		}
		return lista;
	}
	
	public Usuario findById(long id) {
		Optional<Usuario> usuario = this.usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			Usuario user = usuario.get();
			user.setPassword("");
			return user;
		}
		else
			return null;
	}
		
	public String save(Usuario usuario) {
		
		String senhaCriptografada = bcryptEncoder.encode(usuario.getPassword()); //criptografa a senha que virá na requisiçao
		usuario.setPassword(senhaCriptografada); //passa a senha criptografada para o usuário
		
		this.usuarioRepository.save(usuario); //salva o usuário com a senha criptografada
		return "Usuario salvo com sucesso";
	}
	
	public String update(Usuario usuario, long id) {
		usuario.setId(id);
		
		if(!usuario.getPassword().equals("")) { //se o usuário nao tiver senha ou nao quiser alterar mantemos a mesma senha
			String senhaCriptografada = bcryptEncoder.encode(usuario.getPassword());
			usuario.setPassword(senhaCriptografada);
		}else { //se usuário já tiver senha
			String senhaCriptografada = this.findSenhaCriptografada(id);
			usuario.setPassword(senhaCriptografada);
		}
		
		this.usuarioRepository.save(usuario);
		return "Usuario atualizado com sucesso";
	}
	
	public String alterarSenha(String novaSenha, long id) {
		
		String senhaCriptografada = bcryptEncoder.encode(novaSenha);

		this.usuarioRepository.alterarSenha(senhaCriptografada, id);
		return "Senha atualizada com sucesso";
	}
	

	private String findSenhaCriptografada(long id) {
		Optional<Usuario> usuario = this.usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			Usuario user = usuario.get();
			return user.getPassword();
		}
		else
			return null;
	}
	
	
	public String deleteById(long id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario deletado com sucesso";
	}

}
