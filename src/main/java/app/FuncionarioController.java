package app;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
