package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@CrossOrigin /* (origins = "http://localhost:8989/") */
/* PARA RESTRINGIR ACESSO DE ALGUM SERVIDOR */
@RestController /* Arquitetura */
@RequestMapping(value = "/usuario")
public class IndexController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/* Serviço Restfull */
	/* @CrossOrigin(origins = { "www.sistema10.com.br", "www.sistema80.com.br"}) */ /*
																					 * Somente de onde quero autorizar
																					 * para acessar os END POINTS
																					 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	/* PARA DELETAR */
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {

		usuarioRepository.deleteById(id);

		return "Deletado com sucesso";
	}

	/* PARA LISTAR */
	/* @CrossOrigin(origins = "www.teste.com.br") */
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario() {

		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	/*
	 * @PostMapping(value = "/{iduser}/idvenda{idvenda} ou idpedido ou idpagamento",
	 * produces = "application/json")
	 */
	/*
	 * public ResponseEntity<Usuario> cadastrar(@PathVariable Long
	 * iduser, @PathVariable Long idpedido ou id venda )
	 */

	/* PARA CADASTRAR */
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

		for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}

	/* PARA ATUALIZAR */
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {

		/* OUTRAS ROTINAS ANTES DE ATUALIZAR */

		for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity("Usuário Atualizado", HttpStatus.OK);
	}

}
