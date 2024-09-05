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
import com.rodalivregen.Repository.ReservaRepository;
import com.rodalivregen.model.Carro;
import com.rodalivregen.model.Reserva;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private CarroRepository carroRepository;

	@GetMapping
	public ResponseEntity<List<Reserva>> getAll() {
		return ResponseEntity.ok(reservaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reserva> getByid(@PathVariable Long id) {
		return reservaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<Reserva> post(@Valid @RequestBody Reserva reserva) {
		return carroRepository.findById(reserva.getCarro().getId()).map(carro -> {
			if (!carro.isDisponibilidade()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro Indisponível!", null);
			}
			float valorTotal = (float) (reserva.getDiasalugados() * carro.getPrecoPorDia());
			reserva.setValortotal(valorTotal);

			carro.setDisponibilidade(false);
			carroRepository.save(carro);

			reserva.setCarro(carro);
			return ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reserva));
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro não Encontrado!", null));
	}

	@PutMapping
	public ResponseEntity<Reserva> put(@Valid @RequestBody Reserva reserva) {
		return reservaRepository.findById(reserva.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reserva)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Reserva> reserva = reservaRepository.findById(id);

		if (reserva.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Carro carro = reserva.get().getCarro();

		carro.setDisponibilidade(true);
		carroRepository.save(carro);

		reservaRepository.deleteById(id);
	}
}