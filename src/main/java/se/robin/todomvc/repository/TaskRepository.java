package se.robin.todomvc.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import se.robin.todomvc.model.TodoTask;

import java.io.Serializable;
import java.util.List;

@Repository
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

    /**
     * Get all tasks from database
     *
     * @return List of TodoTask-objects
     */
    public List<TodoTask> getTasks() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from TodoTask";
        Query<TodoTask> query = session.createQuery(hql, TodoTask.class);
        return query.list();
    }

    /**
     * Get a single task with the specified taskId
     *
     * @param taskId Id of task to be returned
     * @return Single TodoTask-object
     */
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