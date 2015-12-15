package com.beginner.jsr339;

import com.owlike.genson.Genson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UserResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response get(@PathParam("id") Long id) {
//        User user = new User() {{
//            setId(id);
//            setCode("dragon" + id);
//            setName("测试员" + id);
//        }};
		User user = new User();
		user.setId(id);
		user.setCode("dragon" + id);
		user.setName("测试员" + id);

		return Response.status(Response.Status.OK)
				.entity(new Genson().serialize(user))
				.build();
	}
}