package br.com.samuellna.todo_api.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() { super("Atividade não encontrada."); }
    public TaskNotFoundException(String message) {
        super(message);
    }
}
