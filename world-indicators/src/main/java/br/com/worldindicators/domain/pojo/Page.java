package br.com.worldindicators.domain.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Page {
    private Integer page;
    private Integer pages;
    private Integer total;

    public Page() {
    }

    public Page(Integer page, Integer pages, Integer total, Integer perPage) {
        this.page = page;
        this.pages = pages;
        this.total = total;
        this.perPage = perPage;
    }

    @JsonProperty(value = "per_page")
    private Integer perPage;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}
