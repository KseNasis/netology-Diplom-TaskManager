package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final ArrayList<String> tasks;

    public boolean contains (String task) {
        return tasks.contains(task);
    }

    public Todos() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        return tasks.stream()
                .sorted(Comparator.naturalOrder())
                .reduce((t1, t2) -> t1 + " " + t2)
                .orElse("");
    }
}