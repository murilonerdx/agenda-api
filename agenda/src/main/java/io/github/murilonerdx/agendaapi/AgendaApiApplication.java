package io.github.murilonerdx.agendaapi;

import io.github.murilonerdx.agendaapi.model.entity.Contato;
import io.github.murilonerdx.agendaapi.model.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgendaApiApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ContatoRepository contatoRepository){
        return args ->{
            Contato contato = new Contato();
            contato.setNome("Murilo");
            contato.setEmail("fulano@email.com");
            contato.setFavorito(false);
            contatoRepository.save(contato);
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(AgendaApiApplication.class, args);
    }

}
