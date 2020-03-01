package com.openclassrooms.cleanup;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.cleanup.todoc.database.CleanUpDatabase;
import com.cleanup.todoc.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoTest {
    // FOR DATA
    private CleanUpDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                CleanUpDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    // DATA SET FOR TEST
    private static long TASK_ID = 1;
    private static Task TASK_DEMO = new Task(TASK_ID, 1L, "TEST", 2);

    @Test
    public void insertAndGetTask() throws InterruptedException {
        // BEFORE : Adding a new task
        this.database.taskDao().insert(TASK_DEMO);
        // TEST

        Task task = LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()) && task.getId() == TASK_ID);
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        // BEFORE : Adding a new task
        this.database.taskDao().insert(TASK_DEMO);
        this.database.taskDao().delete(TASK_DEMO);
        // TEST
        assertNull(LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID)));
    }

    @After
    public void closeDb() {
        database.close();
    }
}
