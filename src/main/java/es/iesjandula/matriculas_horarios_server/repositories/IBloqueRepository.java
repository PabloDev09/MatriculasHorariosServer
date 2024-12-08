package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.Bloque;

@Repository
public interface IBloqueRepository extends JpaRepository<Bloque, String>
{

}
