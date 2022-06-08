package br.com.worldindicators.domain.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;

import java.util.Set;

@RegisterForReflection
public class CountryResponse implements Serializable {
    private Page pagination;
    private Set<Country> countries;


    public Page getPagination() {
        return pagination;
    }

    public void setPagination(Page pagination) {
        this.pagination = pagination;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
