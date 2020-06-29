package be.vdab.catalogus.events;

import be.vdab.catalogus.domain.Artikel;

import javax.persistence.*;

@Entity
@Table(name = "artikelsgemaakt")
public class ArtikelGemaakt {
    @Id
    private long id;
    private String naam;

    protected ArtikelGemaakt() {
    }

    public ArtikelGemaakt(Artikel artikel){
        this.id = artikel.getId();
        this.naam = artikel.getNaam();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
