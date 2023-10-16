package br.com.gabrielsousa.todolist.entities;

import br.com.gabrielsousa.todolist.entities.enums.Priority;
import br.com.gabrielsousa.todolist.exceptionhandler.EntityFieldExecption;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 50)
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Priority priority;
    private UUID userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) {
        if(title.length() > 50) {
            throw new EntityFieldExecption("Title field must contain a maximum 50 characters.");
        }
        this.title = title;
    }
}
