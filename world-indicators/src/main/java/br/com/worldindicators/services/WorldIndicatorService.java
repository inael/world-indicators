package br.com.worldindicators.services;

import br.com.worldindicators.domain.pojo.CountryResponse;
import br.com.worldindicators.integration.IWorldBankClient;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;


@ApplicationScoped
public class WorldIndicatorService {

    public static final String JSON_FORMAT = "json";

    @Inject
    @RestClient
    IWorldBankClient worldBankCountryClient;

    @CacheResult(cacheName = "indicators-cache")
    public Response findHistoricalPovertyHeadcountRatioByCountry(String countryCode, int page, int pageSize) {
        var historicalPovertyHeadcountRatio = worldBankCountryClient.getIndicators(countryCode, JSON_FORMAT, page, pageSize);
        return Response.ok(historicalPovertyHeadcountRatio).build();
    }

    public CountryResponse findAllCountry(int page, int pageSize) {
        var countries =  worldBankCountryClient.getCountries(JSON_FORMAT, page, pageSize);
        var countries2 =  worldBankCountryClient.getCountries2(JSON_FORMAT, page, pageSize);
        return null;
    }
}
