package org.commonstudy.jersey.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("simple/{id}")
public class SimpleServices {

    @Context
    HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AddressInformation getHello(@PathParam("id") String id, @Context HttpServletRequest localRequest) {

        AddressInformation result = new AddressInformation();

        result.setThisAddress(this.toString());
        result.setRequestMemeberAddress(request.toString());
        result.setRequestLocalAddress(localRequest.toString());
        result.setId(id);

        return result;
    }
}

class AddressInformation {

    private String thisAddress;

    private String requestMemeberAddress;

    private String requestLocalAddress;

    private String id;

    public String getThisAddress() {
        return thisAddress;
    }

    public void setThisAddress(String thisAddress) {
        this.thisAddress = thisAddress;
    }

    public String getRequestMemeberAddress() {
        return requestMemeberAddress;
    }

    public void setRequestMemeberAddress(String requestMemeberAddress) {
        this.requestMemeberAddress = requestMemeberAddress;
    }

    public String getRequestLocalAddress() {
        return requestLocalAddress;
    }

    public void setRequestLocalAddress(String requestLocalAddress) {
        this.requestLocalAddress = requestLocalAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
