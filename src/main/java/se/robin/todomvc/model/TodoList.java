package se.robin.todomvc.model;

import javax.persistence.*;

@Entity
@Table(name = "lists")
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listId", nullable = false)
    private int listId;

    public TodoList() {
    }

    public TodoList(int listId) {
        this.listId = listId;
    }
}
