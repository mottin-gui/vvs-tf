package com.vvs.trabalhofinal.repositorios;

import com.vvs.trabalhofinal.modelos.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Declaração da interface do repositório a ser utilizada no sistema.
 * Os métodos CRUD serão herdados para serem utilizados com objetos Carro.
 */
@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
    
}
