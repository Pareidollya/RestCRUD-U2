package domain;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long>{
	Optional<Funcionario> findByNomeContainingIgnoreCase(String nome); // partes do nome ignorando maiusculo ou minusculo
	List<Funcionario> findAllByNomeContainingIgnoreCase(String nome); //retornar funcionarios com nome repetido
}
