package be.vdab.catalogus.services;

import be.vdab.catalogus.domain.Artikel;
import be.vdab.catalogus.events.ArtikelGemaakt;
import be.vdab.catalogus.repositories.ArtikelRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class DefaultArtikelService implements ArtikelService{
    private final ArtikelRepository repository;
    private final AmqpTemplate template;

    public DefaultArtikelService(ArtikelRepository repository, AmqpTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    public void create(Artikel artikel) {
        repository.save(artikel);
        template.convertAndSend("catalogus", null, new ArtikelGemaakt(artikel));
    }
}
