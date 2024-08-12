package com.rodalivregen.Controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.rodalivregen.Repository.CarroRepository;
import com.rodalivregen.model.Carro;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarroController {

	@Autowired
	private CarroRepository carroRepository;

	@GetMapping
	public ResponseEntity<List<Carro>> getAll() {
		return ResponseEntity.ok(carroRepository.findAll());
	}

	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Carro>> getByMarca(@PathVariable String marca) {
		return ResponseEntity.ok(carroRepository.findAllByMarcaContainingIgnoreCase(marca));
	}

	@PostMapping
	public ResponseEntity<Carro> post(@Valid @RequestBody Carro carro) {
		return ResponseEntity.status(HttpStatus.CREATED).body(carroRepository.save(carro));
	}

	@PutMapping
	public ResponseEntity<Carro> put(@Valid @RequestBody Carro carro) {
		if (carroRepository.existsById(carro.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(carroRepository.save(carro));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		if (carro.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		carroRepository.deleteById(id);
	}
}