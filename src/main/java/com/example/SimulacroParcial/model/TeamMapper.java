package com.example.SimulacroParcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMapper {

    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 200, message = "Name must be between 10 and 200 characters")
    private String name;

    @Size(max = 23, message = "Team must have a maximum of 23 players")
    private List<Player> players;
}
