package ru.javabegin.springboot.business.service;

import org.springframework.stereotype.Service;
import ru.javabegin.springboot.business.entity.Stat;
import ru.javabegin.springboot.business.repository.StatRepository;

import javax.transaction.Transactional;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service

/*
 @Transactional  будет применяться к каждому методу сервиса.
 Пригодится на будущее, если в одном методе будет неск. вызовов в БД - все будут выполняться в одной транзакции.
 Можно будет настраивать транзакции точечно по необходимости.
 Если в методе при вызове репозитория возникнет исключение - все выполненные вызовы к БД из данного метода откатятся (Rollback)
*/
@Transactional
public class StatService {

    private final StatRepository repository; // сервис имеет право обращаться к репозиторию (БД)

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(String email) {
        return repository.findByUserEmail(email);
    }


}
