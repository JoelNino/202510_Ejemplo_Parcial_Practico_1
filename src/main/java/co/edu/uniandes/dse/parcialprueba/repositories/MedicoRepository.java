package co.edu.uniandes.dse.parcialprueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
	List<MedicoEntity> findByName(String nombre);
}

