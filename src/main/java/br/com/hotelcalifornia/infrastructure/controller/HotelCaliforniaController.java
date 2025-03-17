package br.com.hotelcalifornia.infrastructure.controller;

import br.com.hotelcalifornia.infrastructure.model.HotelCaliforniaModel;
import br.com.hotelcalifornia.infrastructure.service.HotelCaliforniaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/hotel"})
@RequiredArgsConstructor
public class HotelCaliforniaController {

    private final HotelCaliforniaService service;

    @GetMapping(value = "listar")//localhost:8080/api/hotel
    public List<HotelCaliforniaModel> getAll() {
        return service.lista();
    }

    @GetMapping(value = "listar/{id}")//localhost:8080/api/hotel
    public ResponseEntity<HotelCaliforniaModel> findById(@PathVariable UUID id){
        return service.acharId(id);
   }

    //Create
    @PostMapping//localhost:8080/api/hotel
    public HotelCaliforniaModel create(@RequestBody HotelCaliforniaModel hotelCaliforniaModel){
        return service.criaHotel(hotelCaliforniaModel);
    }

    // Update
    @PutMapping(value = "/{id}")
    public ResponseEntity<HotelCaliforniaModel> update(@PathVariable UUID id, @RequestBody HotelCaliforniaModel hotelCaliforniaModel){
        return service.atualizaHotel(id, hotelCaliforniaModel);
    }

    //Delete
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        return service.apaga(id);
    }
}
