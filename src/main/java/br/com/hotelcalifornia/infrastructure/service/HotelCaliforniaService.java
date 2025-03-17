package br.com.hotelcalifornia.infrastructure.service;

import br.com.hotelcalifornia.infrastructure.model.HotelCaliforniaModel;
import br.com.hotelcalifornia.infrastructure.repository.HotelCaliforniaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class HotelCaliforniaService {
    private final HotelCaliforniaRepository repository;

    public List<HotelCaliforniaModel> lista(){
        return repository.findAll();
    }

    public HotelCaliforniaModel criaHotel(HotelCaliforniaModel hotelCaliforniaModel){
        return repository.save(hotelCaliforniaModel);
    }

    public ResponseEntity<HotelCaliforniaModel> acharId(UUID id){
        return repository.findById(id).map(mapping -> ResponseEntity.ok().body(mapping))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<HotelCaliforniaModel> atualizaHotel(UUID id, HotelCaliforniaModel hotelCaliforniaModel){
        return repository.findById(id).map(mapping -> {
            mapping.setName(hotelCaliforniaModel.getName());
            mapping.setCapacidade(hotelCaliforniaModel.getCapacidade());
            mapping.setCnpj(hotelCaliforniaModel.getCnpj());
            mapping.setLocal(hotelCaliforniaModel.getLocal());

            HotelCaliforniaModel updatedHotelCaliforniaModel = repository.save(mapping);

           return ResponseEntity.ok().body(updatedHotelCaliforniaModel);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> apaga(UUID id){
        return repository.findById(id).map(mapping -> {
            repository.deleteById(id);

            return ResponseEntity.ok().body("Deletado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }
}
