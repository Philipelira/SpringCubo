package br.com.hotelcalifornia.infrastructure.repository;

import br.com.hotelcalifornia.infrastructure.model.HotelCaliforniaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelCaliforniaRepository extends JpaRepository<HotelCaliforniaModel, UUID> {
}
