package com.Bank.BankClient.Services;

import com.Bank.BankClient.Model.Entities.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorService {

    @Autowired
    private ReactiveMongoOperations mongoOperations;


    public int getSequenceNumber(String sequenceName) {
        //get sequence no
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update the sequence no
        Update update = new Update().inc("sequence", 1);
        //modify in document
        Sequence counter = mongoOperations
                .findAndModify(query, update, options().returnNew(true).upsert(true),
                        Sequence.class).block();
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }
}
