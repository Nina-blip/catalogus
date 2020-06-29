package be.vdab.catalogus.messaging;

import be.vdab.catalogus.events.ArtikelGemaakt;
import be.vdab.catalogus.repositories.ArtikelGemaaktRepository;
import ch.qos.logback.core.util.FixedDelay;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageSender {
    private final ArtikelGemaaktRepository repository;
    private final AmqpTemplate template;

    public MessageSender(ArtikelGemaaktRepository repository, AmqpTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    @Scheduled(fixedDelay = 5_000)
    @Transactional
    void verstuurMessages(){
        var artikelsgemaakt = repository.findAll();
        for (ArtikelGemaakt gemaakt : artikelsgemaakt){
            template.convertAndSend("catalogus", null, gemaakt);
        }
        repository.deleteAll();
    }
}
