package io.github.murilonerdx.agendaapi.resources;

import io.github.murilonerdx.agendaapi.model.entity.Contato;
import io.github.murilonerdx.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
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

//    @GetMapping()
//    public ResponseEntity<List<Contato>> findAll(){
//        List<Contato> contato = repository.findAll();
//        return ResponseEntity.ok().body(contato);
//    }
    
    @GetMapping()
    public Page<Contato> list(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "10")Integer tamanhoPagina
    ){
        Sort sort = Sort.by(Sort.Direction.ASC, "id"); //Ordenando pelo id
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return repository.findAll(pageRequest);
    }

    @PatchMapping(value ="{id}/favorito")
    public void favorite(@PathVariable Integer id, @RequestBody Boolean favorito){
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent(c -> {
            c.setFavorito(favorito);
            repository.save(c);
        });
    }
    @PutMapping("{id}/foto")
    public byte[] addPhoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo){
        Optional<Contato> contato= repository.findById(id);
        return contato.map(c -> {
            try{
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                c.setFoto(bytes);
                repository.save(c);
                is.close();
                return bytes;
            }catch(IOException e){
                return null;
            }
        }).orElse(null);
    }

    }


