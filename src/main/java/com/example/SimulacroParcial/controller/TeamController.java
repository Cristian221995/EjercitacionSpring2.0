package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Player;
import com.example.SimulacroParcial.model.Team;
import com.example.SimulacroParcial.model.TeamMapper;
import com.example.SimulacroParcial.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/team")
@RestController
public class TeamController {

    private static final String TEAM_NOT_FOUND = "No existe el equipo con el id: %s";

    @Autowired
    private TeamRepository teamRepository;

    private ModelMapper mm = new ModelMapper();

    @PostMapping("")
    public void addTeam(@RequestBody final Team t){
        teamRepository.save(t);
    }

    @GetMapping("")
    public List<TeamMapper> getAll(){
        List<TeamMapper> list = new ArrayList<>();

        for (Team t: teamRepository.findAll()){
            list.add(mm.map(t,TeamMapper.class));
        }
        return list;
    }

    @PostMapping("/addPlayer/{idEquipo}")
    public void addPlayer(@PathVariable final Integer idEquipo, @RequestBody final Player p){
        Team t = teamRepository.findById(idEquipo).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(TEAM_NOT_FOUND,idEquipo)));
        t.getPlayers().add(p);
        p.setTeam(t);
        this.addTeam(t);
    }

    @GetMapping("/mapper/{idEquipo}")
    public TeamMapper getWithMapper(@PathVariable final Integer idEquipo){
        return mm.map(teamRepository.findById(idEquipo),TeamMapper.class);
    }

    /** Comentado por optimizacion de codigo
    @GetMapping("/mapper/")
    public List<Team> GetAllWithMapper(){
        ModelMapper mm = new ModelMapper();
        List<Team> list = new ArrayList<>();

        for (Team t: teamRepository.findAll()){
            list.add(mm.map(t,Team.class));
        }
        return list;
    }
    **/
}
