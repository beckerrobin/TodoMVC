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
        TodoTask createdTask = session.get(TodoTask.class, id);
        System.out.println(task);
        System.out.println(createdTask);
        return createdTask;
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
        String hql = "from TodoTask WHERE listId = :list_id";
        Query<TodoTask> query = session.createQuery(hql, TodoTask.class);
        query.setParameter("list_id", listId);
        System.out.println("Returning: " + ((TodoTask) query.getSingleResult()));
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
        int response = query.executeUpdate();
        System.out.println("Deleting task id: " + taskId + ", Response: " + response);
        return response;
    }
}