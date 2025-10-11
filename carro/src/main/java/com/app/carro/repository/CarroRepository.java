package com.app.carro.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.carro.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{ //Interface sem nada, mas só por extender do JpaRepository já tem uma gama de funçoes

}
