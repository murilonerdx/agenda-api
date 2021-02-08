package io.github.murilonerdx.agendaapi.resources;

import io.github.murilonerdx.agendaapi.model.entity.Contato;
import io.github.murilonerdx.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/contatos")
@RequiredArgsConstructor
public class ContatoController {
    private final ContatoRepository repository;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato contato){
        return repository.save(contato);
    }
    @DeleteMapping("{id}")
    public void save(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping()
    public ResponseEntity<List<Contato>> findAll(){
        List<Contato> contato = repository.findAll();
        return ResponseEntity.ok().body(contato);
    }

    @PatchMapping(value ="{id}/favorito")
    public void favorite(@PathVariable Integer id, @RequestBody Boolean favorito){
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent(c -> {
            c.setFavorito(favorito);
            repository.save(c);
        });
    }

    }


