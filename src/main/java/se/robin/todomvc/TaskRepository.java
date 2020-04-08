package se.robin.todomvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class TaskRepository {
    private SessionFactory sessionFactory;

    public TaskRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Creates new task
     *
     * @param task Task-object to be added to database
     * @return Object that was created, with real ID
     */
    public TodoTask addTask(TodoTask task) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(task);
        return session.get(TodoTask.class, id);
    }

    public List<TodoTask> getTasks(int listId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from TodoTask WHERE listId = :list_id";
        Query<TodoTask> query = session.createQuery(hql, TodoTask.class);
        query.setParameter("list_id", listId);
        return query.list();
    }

    public TodoTask getSingleTask(int taskId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from TodoTask WHERE taskId = :task_id";
        Query<TodoTask> query = session.createQuery(hql, TodoTask.class);
        query.setParameter("task_id", taskId);
        return query.getSingleResult();
    }

    /**
     * Deletes task with specified taskId
     *
     * @param taskId Task to be deleted
     * @return No of rows deleted (should be 1)
     */
    public int deleteTask(int taskId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE TodoTask WHERE taskId = :task_id";
        Query query = session.createQuery(hql);
        query.setParameter("task_id", taskId);
        return query.executeUpdate();
    }

    /**
     * Updates a row in the database with new content and isCompleted
     *
     * @param task Task containing the new data as well as the intended taskId
     * @return The updated TodoTask-object
     */
    public TodoTask updateTask(TodoTask task) {
        Session session = sessionFactory.getCurrentSession();
        return (TodoTask) session.merge(task);
    }
}