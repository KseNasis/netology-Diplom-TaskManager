package ru.netology.javacore;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Сервер запущен " + port + "...");
        JSONParser parser = new JSONParser();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    Object obj = parser.parse(in.readLine());
                    JSONObject jsonObject = (JSONObject) obj;
                    String type = (String) jsonObject.get("type");
                    String task = (String) jsonObject.get("task");

                    switch (type) {
                        case "ADD":
                            if (!todos.contains(task)) {
                                todos.addTask(task);
                                out.println("Добавлена задача " + task + ". Список всех задач: " + todos.getAllTasks());
                            } else {
                                out.println("Задача " + task + " не может быть добавлена, т.к. уже создана."
                                        + " Список всех задач: " + todos.getAllTasks());
                            }
                            break;
                        case "REMOVE":
                            if (todos.contains(task)) {
                                todos.removeTask(task);
                                out.println("Удалена задача " + task + ". Список всех задач: " + todos.getAllTasks());
                            } else {
                                out.println("Задача " + task + " не может быть удалена, т.к. нет такой задачи."
                                        + " Список всех задач: " + todos.getAllTasks());
                            }
                            break;
                    }
                    System.out.println(" Список всех задач: " + todos.getAllTasks());
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
