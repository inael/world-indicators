package br.com.worldindicators.integration;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/v2/country")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@RegisterRestClient(configKey = "worldbank-api")
public interface IWorldBankClient {

    @GET
    @Produces(APPLICATION_JSON)
    String getCountries(@DefaultValue("json") final String format,
                        @DefaultValue("0") @QueryParam("page") final Integer currentPage,
                        @DefaultValue("50") @QueryParam("per_page") final Integer pageSize);

    @GET
    @Produces(APPLICATION_JSON)
    Response getCountries2(@QueryParam("format") @DefaultValue("json") String format,
                           @QueryParam("page") final Integer currentPage,
                           @DefaultValue("50") @QueryParam("per_page") final Integer pageSize);

    @GET
    @Path("/{COUNTRY_CODE}/indicator/SI.POV.DDAY")
    Response getIndicators(@PathParam("COUNTRY_CODE") final String countryCode,
                           @DefaultValue("json") @QueryParam("format") final String format,
                           @QueryParam("page") final Integer currentPage,
                           @QueryParam("per_page") final Integer pageSize);

}
