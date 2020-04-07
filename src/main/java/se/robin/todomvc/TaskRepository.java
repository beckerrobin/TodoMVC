package se.robin.todomvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskRepository {
    private SessionFactory sessionFactory;
    public TaskRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public TodoTask addTask(TodoTask task) {
        Session session = sessionFactory.openSession();
        session.save(task);
        return task;
    }
}