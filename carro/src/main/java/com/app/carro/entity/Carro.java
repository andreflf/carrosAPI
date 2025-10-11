package com.app.carro.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity  //marcar que essa classe é a entidade/objeto/campos/tabela que será criada/adicionada no bd
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //criar automaticamente um id, nao precisa passar no Postman
	private Long id;
	private String nome;
	
	@ManyToOne
	private Marca marca;
	
	@ManyToMany
	@JoinTable(name = "carro_acessorio")
	private List<Acessorio> acessorios;
	
	
}
