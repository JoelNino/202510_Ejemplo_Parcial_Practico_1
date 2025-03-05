package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

	@Autowired
	EspecialidadRepository especialidadRepository;
	
	@Transactional
	public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity) throws IllegalOperationException {
		log.info("Inicia proceso de creación de la especialidad");
		
        if (especialidadEntity.getDescripcion().length() < 10)
        throw new IllegalOperationException("La descripción debe tener más de 10 caracteres");

        log.info("Termina proceso de creación de la especialidad");
		return especialidadRepository.save(especialidadEntity);
	}
}