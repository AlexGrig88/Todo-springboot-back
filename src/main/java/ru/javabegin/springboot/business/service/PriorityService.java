package ru.javabegin.springboot.business.service;


import org.springframework.stereotype.Service;
import ru.javabegin.springboot.business.entity.Priority;
import ru.javabegin.springboot.business.repository.PriorityRepository;

import javax.transaction.Transactional;
import java.util.List;

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

public class PriorityService {

    private final PriorityRepository repository; // сервис имеет право обращаться к репозиторию (БД)

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<Priority> findAll(String email) {
        return repository.findByUserEmailOrderByIdAsc(email);
    }

    public Priority add(Priority priority) {
        return repository.save(priority); // метод save обновляет или создает новый объект, если его не было
    }

    public Priority update(Priority priority) {
        return repository.save(priority); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Priority findById(Long id) {
        return repository.findById(id).get(); // т.к. возвращается Optional - можно получить объект методом get()
    }

    public List<Priority> find(String title, String email) {
        return repository.find(title, email);
    }

}
