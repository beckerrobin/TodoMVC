package se.robin.todomvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
