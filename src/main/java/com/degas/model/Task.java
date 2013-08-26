package com.degas.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Author: Ilya Varlamov aka privratnik (contact with me) degas.developer@gmail.com
 * Date: 19.08.13
 * Time: 22:50
 */
@Entity
@Table(name = "TASK")
@NamedQueries(
        @NamedQuery(name = "Task.findAll", query = "SELECT u FROM Task u")
)
public class Task implements Serializable {

    @Id
    @GeneratedValue(generator = "oraSeqTask", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "oraSeqTask", sequenceName = "SEQ_TASK", allocationSize = 1)
    private long id;
    private String title;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date datePossibleFinish;

    @Temporal(TemporalType.DATE)
    private Date dataFinish;
    private String description;

    @Enumerated(EnumType.STRING)
    private PriorityValues priority;

    @Enumerated(EnumType.STRING)
    private StatusValues status;

    public Task(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    @NotNull
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull Date startDate) {
        this.startDate = startDate;
    }

    @NotNull
    public Date getDatePossibleFinish() {
        return datePossibleFinish;
    }

    public void setDatePossibleFinish(@NotNull Date datePossibleFinish) {
        this.datePossibleFinish = datePossibleFinish;
    }

    @Nullable
    public Date getDataFinish() {
        return dataFinish;
    }

    public void setDataFinish(@Nullable Date dataFinish) {
        this.dataFinish = dataFinish;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    @NotNull
    public PriorityValues getPriority() {
        return priority;
    }

    public void setPriority(@NotNull PriorityValues priority) {
        this.priority = priority;
    }

    @NotNull
    public StatusValues getStatus() {
        return status;
    }

    public void setStatus(@NotNull StatusValues status) {
        this.status = status;
    }

}
