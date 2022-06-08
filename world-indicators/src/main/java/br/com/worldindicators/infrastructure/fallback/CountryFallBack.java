package br.com.worldindicators.infrastructure.fallback;
import br.com.worldindicators.domain.pojo.CountryResponse;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.ws.rs.core.Response;

public class CountryFallBack implements FallbackHandler<Response> {

    @Override
    public Response handle(ExecutionContext context) {
        return Response.ok("Fail, try again later!").build();
    }

}