package ca.ciccc.madp202.InterviewerBackend.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.hk2.api.messaging.Topic;

import com.sun.media.jai.codecimpl.util.Service;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import ca.ciccc.madp202.InterviewerBackend.database.Database;
import ca.ciccc.madp202.InterviewerBackend.entities.AnswerCollection;
import ca.ciccc.madp202.InterviewerBackend.entities.CredentialRequest;
import ca.ciccc.madp202.InterviewerBackend.entities.Error;
import ca.ciccc.madp202.InterviewerBackend.entities.History;
import ca.ciccc.madp202.InterviewerBackend.entities.Interview;
import ca.ciccc.madp202.InterviewerBackend.entities.InterviewType;
import ca.ciccc.madp202.InterviewerBackend.entities.Profile;
import ca.ciccc.madp202.InterviewerBackend.entities.Question;
import ca.ciccc.madp202.InterviewerBackend.entities.Result;
import ca.ciccc.madp202.InterviewerBackend.entities.User;
import ca.ciccc.madp202.InterviewerBackend.entities.UserRequest;
import ca.ciccc.madp202.InterviewerBackend.services.InterviewService;

@Path("interview")
public class InterviewResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessages() {
		return "aaa";
//		return messagesService.getAllMessages();
	}

    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile createUser(UserRequest requestModel) {
    		int id = InterviewService.createUser(requestModel);
        return new Profile(InterviewService.getUser(id));
    }

    /**
     * If the username exists but the password is wrong you will ask the user to try again.
     * If the username does not exist you will ask the user to register and will show the register message
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLogin(CredentialRequest cr) {
    		User user = InterviewService.getUserByCredential(cr);
    		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).entity(new Error(1, Error.EMAIL_INVALID)).build();
		} else if (user.getPassword().equals(cr.getPassword())) {
			return Response.ok().entity(new Profile(user)).build();
		}
    		return Response.status(Status.BAD_REQUEST).entity(new Error(2, Error.PASSWORD_INVALID)).build();
    }

    @GET
    @Path("/topics/{interview_topic}")
    @Produces(MediaType.APPLICATION_JSON)
    public Interview getTopic(@PathParam("interview_topic")InterviewType topic) {
    		System.out.println(topic);
    		Interview interview = InterviewService.getInterview(topic.id);
        return interview;
    }

    @POST
    @Path("/interviews/{interview_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Result submitAnswer(@PathParam("interview_id")int id, AnswerCollection ac) {
    		System.out.println(ac.getInterviewId() + " " + ac.getAnswers().size());
    		return InterviewService.submitAnswer(id, ac);
    }

    @GET
    @Path("/history/user/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<History> getHistory(@PathParam("user_id")int id) {
    		List<History> list = InterviewService.getHistories(id);
        return list;
    }
}
