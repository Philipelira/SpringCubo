package br.com.hotelcalifornia.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

@Table(name = "hotel_california")
public class HotelCaliforniaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "local")
    private String local;

    @Column(name = "capacidade")
    private String capacidade;

    @Column(name = "cnpj")
    private String cnpj;
}
