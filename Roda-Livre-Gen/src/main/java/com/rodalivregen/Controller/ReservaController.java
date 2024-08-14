package com.rodalivregen.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rodalivregen.Repository.ReservaRepository;
import com.rodalivregen.model.Reserva;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservaController {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Reserva>> getAll() {
		return ResponseEntity.ok(reservaRepository.findAll());
	}
	
	@PostMapping //cadastro de reserva
	public ResponseEntity<Reserva> post(@Valid @RequestBody Reserva reserva) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reserva));
	}
	
}