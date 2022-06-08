package br.com.worldindicators.domain.pojo;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Country {
    private String iso2Code;
    private String name;

    public Country() {
    }

    public Country(String iso2Code, String name) {
        this.iso2Code = iso2Code;
        this.name = name;
    }

    public String getIso2Code() {
        return iso2Code;
    }

    public void setIso2Code(String iso2Code) {
        this.iso2Code = iso2Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
