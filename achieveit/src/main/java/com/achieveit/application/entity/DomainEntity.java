package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer domain;

    private String domainContent;

    public DomainEntity() {
    }

    public DomainEntity(Integer domain, String domainContent) {
        this.domain = domain;
        this.domainContent = domainContent;
    }

    public Integer getDomain() {
        return domain;
    }

    public void setDomain(Integer domain) {
        this.domain = domain;
    }

    public String getDomainContent() {
        return domainContent;
    }

    public void setDomainContent(String domainContent) {
        this.domainContent = domainContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntity that = (DomainEntity) o;
        return Objects.equals(domain, that.domain) &&
                Objects.equals(domainContent, that.domainContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, domainContent);
    }

    @Override
    public String toString() {
        return "DomainEntity{" +
                "domain=" + domain +
                ", domainContent='" + domainContent + '\'' +
                '}';
    }
}
