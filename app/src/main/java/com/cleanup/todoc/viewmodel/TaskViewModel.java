package com.cleanup.todoc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    // Repositories
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void deleteTask(Task task) {
        repository.delete(task);
    }

    public void updateTask(Task task) {
        repository.update(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

}
