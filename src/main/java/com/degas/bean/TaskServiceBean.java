package com.degas.bean;

import com.degas.model.PriorityValues;
import com.degas.model.StatusValues;
import com.degas.model.Task;
import org.jetbrains.annotations.NotNull;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Author: Ilya Varlamov aka privratnik (contact with me) degas.developer@gmail.com
 * Date: 19.08.13
 * Time: 23:37
 */

@Stateless
@LocalBean
public class TaskServiceBean implements TaskService {

    @PersistenceContext(unitName = "restUnit")
    EntityManager em;

    @Override
    public void createOrModifyTask(@NotNull Task task) {
        em.merge(task);
    }

    @Override
    public void changeStatus(long id, @NotNull StatusValues statusValues) {
        Task task = em.find(Task.class, id);
        task.setStatus(statusValues);
    }

    @Override
    public void changePriority(long id, @NotNull PriorityValues statusValues) {
        Task task = em.find(Task.class, id);
        task.setPriority(statusValues);
    }

    @NotNull
    @Override
    public List<Task> getListTask() {
        return em.createNamedQuery("Task.findAll", Task.class).getResultList();
    }

    @NotNull
    @Override
    public List<Task> getSubListTask(int start, int end) {

        Query query = em. createNativeQuery("SELECT * FROM " +
                "(SELECT  t.*, row_number() OVER (ORDER BY ID) rn FROM task t) " +
                "WHERE rn BETWEEN ? AND ?", Task.class);
        query.setParameter(1, start);
        query.setParameter(2, end);

        return query.getResultList();
    }

}
