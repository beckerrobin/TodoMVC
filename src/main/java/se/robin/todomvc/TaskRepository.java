package se.robin.todomvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<TodoTask> getTasks(int listId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from TodoTask WHERE listId = :list_id";
        Query<TodoTask> query = session.createQuery(hql, TodoTask.class);
        query.setParameter("list_id", listId);
        System.out.println("Returning: " + query.list().size() + " items");
        return query.list();
    }
    public TodoTask getSingleTask(int listId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from TodoTask WHERE listId = :list_id and order = 1";
        Query<TodoTask> query = session.createQuery(hql, TodoTask.class);
        query.setParameter("list_id", listId);
        System.out.println("Returning: " + ((TodoTask) query.getSingleResult()));
        return query.getSingleResult();
    }
}