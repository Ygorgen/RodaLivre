package com.rodalivregen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.rodalivregen.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findAllByMarcaContainingIgnoreCase(@Param("marca") String marca);
}
