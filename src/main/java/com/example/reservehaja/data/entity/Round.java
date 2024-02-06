package com.example.reservehaja.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "round")
@Getter
@Setter
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roundName;

    /*
    @ElementCollection
    private List<LocalDate> roundDates;
    */

    private LocalTime roundUseBeginTime;

    private LocalTime roundUseEndTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "amenity_id")
    Amenity amenity;

    @OneToMany(mappedBy = "round", cascade = CascadeType.REMOVE)
    private List<RoundCell> roundCellList = new ArrayList<>();

}
