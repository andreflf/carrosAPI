package com.app.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.carro.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
