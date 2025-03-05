package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@DataJpaTest
@Transactional
@Import(MedicoService.class)
class MedicoServiceTest {

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

    private List<MedicoEntity> medicoList = new ArrayList<>();

	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
	}

	private void insertData() {
		for (int i = 0; i < 3; i++) {
			MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
			medicoEntity.setRegistro("RM" + i);
			entityManager.persist(medicoEntity);
			medicoList.add(medicoEntity);
		}
	}

	@Test
	void testCreateMedico() throws IllegalOperationException {
		MedicoEntity newMedico = factory.manufacturePojo(MedicoEntity.class);
		newMedico.setRegistro("RM123");

		MedicoEntity result = medicoService.createMedico(newMedico);
		assertNotNull(result);

		MedicoEntity entity = entityManager.find(MedicoEntity.class, result.getId());

		assertEquals(newMedico.getId(), entity.getId());
		assertEquals(newMedico.getRegistro(), entity.getRegistro());
	}

	@Test
	void testCreateMedicoConRegistroInvalido() {
		MedicoEntity newMedico = factory.manufacturePojo(MedicoEntity.class);
		newMedico.setRegistro("XX123");

		assertThrows(IllegalOperationException.class, () -> {
			medicoService.createMedico(newMedico);
		});
	}
}
