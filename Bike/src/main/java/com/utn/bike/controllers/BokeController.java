package com.utn.bike.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PostRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utn.bike.models.Bike;
import com.utn.bike.repositories.BikeRepository;

@RestController
//@RestController y automaticamente se publicara como un  Spring REST Service.
@RequestMapping("api/v1/bikes")
public class BokeController {

	@Autowired
	private BikeRepository bk;

	// metodo que devuelve la lista de bicicletas
	@GetMapping
	public List<Bike> lista() {

		return bk.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody Bike bike) {

		bk.save(bike);
	}

//	http://localhost:8080/api/v1/bikes/1
	@GetMapping("/{id}")
	public Bike get(@PathVariable("id") long id) {

		if (bk.existsById(id)) {

			return bk.getOne(id);
		} else {
			return new Bike();
		}

	}

//	http://localhost:8080/api/v1/bikes/borrar/3
	@GetMapping("/borrar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String delete(@PathVariable long id) {

		if (bk.existsById(id)) {

			bk.deleteById(id);

			return "se Elimino el ID :" + id;
		} else {

			return "no existe";
		}

	}
}
