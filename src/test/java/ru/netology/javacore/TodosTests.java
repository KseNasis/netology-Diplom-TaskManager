package ru.netology.javacore;

import org.junit.jupiter.api.*;

public class TodosTests {
    private static long suiteStartTime;
    private long testStartTime;

    @BeforeAll
    public static void BeforeAll() {
        System.out.println("Tests beginning");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void AfterAll() {
        System.out.println("Tests finished " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void BeforeEachMethod() {
        System.out.println("    Test started");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void AfterEachMethod() {
        System.out.println("    Test compiled " + (System.nanoTime() - testStartTime));
    }

    @Test
    public void testAddTasks() {
        Todos todos = new Todos();
        String expected = "test";

        todos.addTask("test");
        String result = todos.getAllTasks();

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void testRemoveTask () {
        Todos todos = new Todos();
        String task1 = "test1";
        String task2 = "test2";
        String task3 = "test3";
        String expected = "test1 test3";

        todos.addTask(task1);
        todos.addTask(task2);
        todos.addTask(task3);
        todos.removeTask(task2);
        String result = todos.getAllTasks();

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void testGetAllTasks () {
        Todos todos = new Todos();
        String expected = "test A test B test C";

        todos.addTask("test C");
        todos.addTask("test B");
        todos.addTask("test A");

        String result = todos.getAllTasks();

        Assertions.assertEquals(expected, result);
    }

}
