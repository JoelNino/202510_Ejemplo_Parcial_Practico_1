package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class MedicoEntity extends BaseEntity {
    String nombre;
    String apellido;
    String registro;

    @PodamExclude
	@ManyToMany(mappedBy = "medicos")
	private List<EspecialidadEntity> especialidades = new ArrayList<>();
    

}
