package com.Bank.BankClient.Services;

import com.Bank.BankClient.Model.Entities.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorService {

    @Autowired
    private ReactiveMongoOperations mongoOperations ;

    private static final Logger log = LoggerFactory.getLogger(SequenceGeneratorService.class);

    public Mono<Integer> getSequenceNumber(String sequenceName){

        String url = "http://localhost:18881/api/sequence/getNext/"+sequenceName;
        Mono<Sequence> sequenceMono = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Sequence.class);
        return sequenceMono.flatMap(x -> Mono.just(x.getSequence()));
    }


    public Mono<Integer> getSequenceNumberMBD(String sequenceName) {
        log.info("Start sequence generator");
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("sequence", 1);
        log.info("Asign : " + update.toString() +" / " + query.toString());
        return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), Sequence.class).flatMap(s ->{
            Integer counter =  !Objects.isNull(s) ? s.getSequence() : 1;
            return Mono.just(counter);
        });
    }
}