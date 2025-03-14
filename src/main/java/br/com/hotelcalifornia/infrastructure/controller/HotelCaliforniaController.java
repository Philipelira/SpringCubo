package br.com.hotelcalifornia.infrastructure.controller;

import br.com.hotelcalifornia.infrastructure.model.HotelCaliforniaModel;
import br.com.hotelcalifornia.infrastructure.repository.HotelCaliforniaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/hotel"})
@RequiredArgsConstructor
public class HotelCaliforniaController {

    private final HotelCaliforniaRepository repository;

    @GetMapping//localhost:8080/api/hotel
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(value = "{id}")//localhost:8080/api/hotel
    public ResponseEntity<HotelCaliforniaModel> findById(@PathVariable UUID id){
        return repository.findById(id).map(mapping -> ResponseEntity.ok().body(mapping))
                .orElse(ResponseEntity.notFound().build());
    }

    //Create
    @PostMapping//localhost:8080/api/hotel
    public HotelCaliforniaModel create(@RequestBody HotelCaliforniaModel hotelCaliforniaModel){
        return repository.save(hotelCaliforniaModel);
    }

    // Update
    @PutMapping(value = "{id}")
    public ResponseEntity<HotelCaliforniaModel> update(@PathVariable UUID id, @RequestBody HotelCaliforniaModel hotelCaliforniaModel){
        return repository.findById(id).map(mapping -> {
            mapping.setName(hotelCaliforniaModel.getName());
            mapping.setLocal(hotelCaliforniaModel.getLocal());
            mapping.setCapacidade(hotelCaliforniaModel.getCapacidade());
            mapping.setCnpj(hotelCaliforniaModel.getCnpj());

            HotelCaliforniaModel updatedModel = repository.save(mapping);

            return ResponseEntity.ok().body(updatedModel);
        }).orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        return repository.findById(id).map(mapping ->{
            repository.deleteById(id);

            return ResponseEntity.ok().body("DELETADO COM SUCESSO");
        }).orElse(ResponseEntity.notFound().build());
    }
}
