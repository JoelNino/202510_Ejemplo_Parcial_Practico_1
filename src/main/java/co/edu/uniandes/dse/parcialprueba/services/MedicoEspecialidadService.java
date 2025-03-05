package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private EspecialidadRepository especialidadRepository;
	
	/**
	 * Asocia un Author existente a un Book
	 *
	 * @param bookId   Identificador de la instancia de Book
	 * @param authorId Identificador de la instancia de Author
	 * @return Instancia de AuthorEntity que fue asociada a Book
	 */
	@Transactional
	public MedicoEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociar una especialidad a un medico");
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("No se encontró el medico");

		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
		if (especialidadEntity.isEmpty())
			throw new EntityNotFoundException("No se encontró la especialidad");

		medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
		log.info("Termina proceso de asociarle una especialidad a un medico");

        return medicoEntity.get();
	}

}