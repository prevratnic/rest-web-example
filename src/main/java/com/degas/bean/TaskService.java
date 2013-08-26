package com.degas.bean;

import com.degas.model.PriorityValues;
import com.degas.model.StatusValues;
import com.degas.model.Task;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Author: Ilya Varlamov aka privratnik (contact with me) degas.developer@gmail.com
 * Date: 20.08.13
 * Time: 13:28
 */

public interface TaskService {

    void createOrModifyTask(@NotNull Task task);

    /**
     * Method changed status to task
     * @param id - primary key a task
     * @param values - status a task
     * */
    void changeStatus(long id, @NotNull StatusValues values);

    /**
     * Method changed priority to task
     * @param id - primary key a task
     * */
    void changePriority(long id, @NotNull PriorityValues statusValues);

    /**
     * Method return all task
     * @return List<Task> - all Task
     * */
    @NotNull List<Task> getListTask();

    /**
     * Method return to sub list elements
     * @param start - beginning points to an element from which you want to begin selecting
     * @param end - points to an element from which you want to finish
     * @return List<Task> - sub list a task
     * */
    @NotNull
    List<Task> getSubListTask(int start, int end);

}
