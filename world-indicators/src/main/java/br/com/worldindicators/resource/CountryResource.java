package br.com.worldindicators.resource;

import br.com.worldindicators.domain.pojo.CountryResponse;
import br.com.worldindicators.infrastructure.fallback.CountryFallBack;
import br.com.worldindicators.services.WorldIndicatorService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/world-indicators")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "World Indicators", description = "Endpoint to access infos about National Indicators\n")
public class CountryResource {

    private static final Logger LOG = Logger.getLogger(CountryResource.class);
    @Inject
    WorldIndicatorService worldIndicatorService;

    @GET
    @Path("countries")
    @Timeout(2500)
    @Retry(maxRetries = 2, delay = 2000)
    @Fallback(CountryFallBack.class)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 5000)
    @Timed(name = "timed/getAllCountry")
    @Counted(name = "counted/getAllCountry")
    public Response getAllCountry(@QueryParam("pageNum")
                                         @DefaultValue("0") final Integer page,
                                  @QueryParam("pageSize")
                                         @DefaultValue("10") final Integer pageSize) {
        LOG.info("GET All Country");
        var response =  worldIndicatorService.findAllCountry(page, pageSize);
        return Response.ok(response).build();
    }
}