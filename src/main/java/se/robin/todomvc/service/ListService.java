package se.robin.todomvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.robin.todomvc.model.TodoList;
import se.robin.todomvc.repository.ListRepository;

@Component
public class ListService {
    private ListRepository repository;

    @Autowired
    public void prepare (ListRepository repository) {
        this.repository = repository;
    }

    public TodoList add() {
        return repository.addList(new TodoList());
    }
}
