package com.degas.service;

import com.degas.bean.TaskServiceBean;
import com.degas.model.Task;
import org.jetbrains.annotations.NotNull;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Ilya Varlamov aka privratnik (contact with me) degas.developer@gmail.com
 * Date: 20.08.13
 * Time: 19:24
 */

@Path("task")
public class JournalTask {

    private final static Logger LOGGER = Logger.getLogger(TaskServiceBean.class.getName());

    @EJB
    private TaskServiceBean serviceBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getList(){
        return serviceBean.getListTask();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getSubList(@PathParam("id") String id){
        int view = Integer.valueOf(id);
        int end = view * 10;
        int start = end - 9;

        return serviceBean.getSubListTask(start, end);
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(@NotNull Task task){
        if(task.getId() != 0)
            task.setId(0);
        return performChange(task);
    }

    @POST
    @Path("change")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeTask(@NotNull Task task){
        return performChange(task);
    }

    @NotNull
    private Response performChange(@NotNull Task task){
        Response response;
        try {
            serviceBean.createOrModifyTask(task);
            response = Response.ok().build();
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage());
            response = Response.noContent().build();
        }
        return response;
    }

}
