package app;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Funcionario;
import domain.FuncionarioRepository;


@RestController
@RequestMapping(value = "/Funcionario")
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/test")
	public String test() {
		return "sim";
	}
	
	@PostMapping("/Cadastro")
	public ResponseEntity<Funcionario> CadastrarFuncionario(@RequestBody Funcionario f){
		Date DataCadastro = new Date();
		f.setCadastro(DataCadastro.toString());
		Funcionario novo = funcionarioRepository.save(f);
		System.out.println(novo.toString());
		return ResponseEntity.ok(novo);	
	}
	
	@GetMapping("/Consulta")
	public ResponseEntity<List<Funcionario>> consultarFuncionarios(){
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		if (funcionarios != null) {
			return ResponseEntity.ok(funcionarios);
			
		}else {
			System.out.println("tem nada");
			return ResponseEntity.badRequest().body(null);
		}
	} 
	
	@GetMapping("/Consulta/nome/{nome}")
	public ResponseEntity<Funcionario> consultarNome (@PathVariable("nome") String nome){
		try { 
			//optional valida se o dado esta presente no banco
			Optional<Funcionario> funcionario = funcionarioRepository.findByNomeContainingIgnoreCase(nome); //retorna apenas 1 
			if(funcionario.isPresent()) {
				return ResponseEntity.ok(funcionario.get());
					
			}else {
				return ResponseEntity.status(404).body(null); //caso nao tenha 
			}
			
		}catch(Exception e) { //caso haja mais de um com mesmo nome 
			List<Funcionario> funcionarios = funcionarioRepository.findAllByNomeContainingIgnoreCase(nome);
			return ResponseEntity.ok(funcionarios.get(0)); //retorna o primeiro de uma lista de funcionarios
			
		}
	}
	@PutMapping("/Altera") 
	public ResponseEntity<Object> alterarFuncionario(@RequestBody Funcionario funcionario){ //deve retornar o funcionario com a matricula em BODY
		if(funcionarioRepository.existsById(funcionario.getMatricula())) {
			
			if(funcionario.getCadastro() == null) { 
				funcionario.setCadastro(funcionarioRepository.findById(funcionario.getMatricula()).get().getCadastro());
			}
			
			funcionarioRepository.save(funcionario);		
			return ResponseEntity.ok(funcionarioRepository.findById(funcionario.getMatricula()));
			
		} else {
			return ResponseEntity.status(404).body("tem ngm com esses dados 👍");
		}
	}
	
	
	
	@DeleteMapping("/Delete/{matricula}")
	public ResponseEntity<String> deletarFuncionario(@PathVariable("matricula") long matricula){
		try {
			String a = funcionarioRepository.findById(matricula).get().getNome()+", " + funcionarioRepository.findById(matricula).get().getMatricula();
			
			funcionarioRepository.deleteById(matricula);
			return ResponseEntity.ok(a + " removido!");
			
		}catch(Exception e){
			return ResponseEntity.status(404).body("não há funcionarios com essa matricula: " + matricula );
			
		}
	}
	
	
}