package com.firstproject.rest;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.firstproject.domain.model.User;
import com.firstproject.rest.dto.CreateUserRequest;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("/users")
@Transactional
public class UserResource {

    @POST
    public Response createUser(CreateUserRequest userRequest) {

        User user = new User(userRequest.getName(), userRequest.getAge());
        
        user.persist();
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){

        PanacheQuery<PanacheEntityBase> query = User.findAll();
        return Response.ok(query.list()).build();
    }

}
