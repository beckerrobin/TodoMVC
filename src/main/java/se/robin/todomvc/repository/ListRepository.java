package se.robin.todomvc.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import se.robin.todomvc.model.TodoList;

@Component
public class ListRepository {
    private SessionFactory sessionFactory;
    public ListRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public TodoList addList(TodoList list) {
        Session session = sessionFactory.openSession();
        session.save(list);
        return list;
    }
}
