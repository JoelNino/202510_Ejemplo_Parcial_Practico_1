package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {

	@Autowired
	MedicoRepository medicoRepository;
	
	/**
	 * Se encarga de crear un Author en la base de datos.
	 *
	 * @param author Objeto de AuthorEntity con los datos nuevos
	 * @return Objeto de AuthorEntity con los datos nuevos y su ID.
	 * @throws IllegalOperationException 
	 */
	@Transactional
	public MedicoEntity createMedico(MedicoEntity medicoEntity) throws IllegalOperationException {
		log.info("Inicia proceso de creación del autor");
		
        if (!medicoEntity.getRegistro().startsWith("RM"))
        throw new IllegalOperationException("El registro no inicia con RM");

        log.info("Termina proceso de creación del medico");
		return medicoRepository.save(medicoEntity);
	}
}