package be.vdab.catalogus.services;

import be.vdab.catalogus.domain.Artikel;
import be.vdab.catalogus.events.ArtikelGemaakt;
import be.vdab.catalogus.repositories.ArtikelGemaaktRepository;
import be.vdab.catalogus.repositories.ArtikelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultArtikelService implements ArtikelService{
    private final ArtikelRepository repository;
    private final ArtikelGemaaktRepository artikelGemaaktRepository;

    public DefaultArtikelService(ArtikelRepository repository, ArtikelGemaaktRepository artikelGemaaktRepository) {
        this.repository = repository;
        this.artikelGemaaktRepository = artikelGemaaktRepository;
    }

    @Override
    @Transactional
    public void create(Artikel artikel) {
        repository.save(artikel);
        artikelGemaaktRepository.save(new ArtikelGemaakt(artikel));
    }
}
