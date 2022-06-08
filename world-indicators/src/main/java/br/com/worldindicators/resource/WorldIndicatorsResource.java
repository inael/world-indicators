package br.com.worldindicators.resource;

import br.com.worldindicators.services.WorldIndicatorService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/world-indicators")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "World Indicators", description = "Endpoint to access infos about National Indicators\n")
public class WorldIndicatorsResource {

    private static final Logger LOG = Logger.getLogger(WorldIndicatorsResource.class);
    @Inject
    WorldIndicatorService worldIndicatorService;

    @GET
    @Path("/poverty-headcount-ratio/{COUNTRY_CODE}")
    @Timeout(250)
    @Retry(maxRetries = 4)
    @Fallback(fallbackMethod = "fallbackGetHistoricalPovertyHeadcountRatio")
    @CircuitBreaker(requestVolumeThreshold = 4)
    @Timed(name = "timed/getHistoricalPovertyHeadcountRatio")
    @Counted(name = "counted/getHistoricalPovertyHeadcountRatio")
    @Operation(
            description = "Find infos about National poverty headcount ratio is the percentage of the population living below the national poverty line(s).",
            operationId = "countries#getHistoricalPovertyHeadcountRatio",
            summary = "Fetch bookmark information by country code"
    )
    @APIResponse(name = "OK", responseCode = "200", description = "Complete successfully")
    @APIResponse(name = "Internal Server Error", responseCode = "500", description = "There was an internal problem on the server")
    public Response getHistoricalPovertyHeadcountRatio(
            @PathParam("COUNTRY_CODE") final String countryCode,
            @QueryParam("pageNum")
            @DefaultValue("0") final int page,
            @QueryParam("pageSize")
            @DefaultValue("10") final int pageSize) {
        LOG.info("GET Historical Poverty Headcount Ratio");
        Response povertyHeadcountRatio = worldIndicatorService.findHistoricalPovertyHeadcountRatioByCountry(countryCode, page, pageSize);
        return Response.ok(povertyHeadcountRatio).build();
    }

    public Response fallbackGetHistoricalPovertyHeadcountRatio(String countryCode, int page, int pageSize) {
        // LOGGER.info("Falling back to fallbackHistoricalPovertyHeadcountRatio()");
        // safe bet, return nothing
        return Response.ok().build();
    }


}