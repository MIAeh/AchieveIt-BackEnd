package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class ArchiveEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String archiveLink;

    private Boolean archived;

    public ArchiveEntity() {
    }

    public ArchiveEntity(String archiveLink, Boolean archived) {
        this.archiveLink = archiveLink;
        this.archived = archived;
    }

    public String getArchiveLink() {
        return archiveLink;
    }

    public void setArchiveLink(String archiveLink) {
        this.archiveLink = archiveLink;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveEntity that = (ArchiveEntity) o;
        return Objects.equals(archiveLink, that.archiveLink) &&
                Objects.equals(archived, that.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archiveLink, archived);
    }

    @Override
    public String toString() {
        return "ArchiveEntity{" +
                "archiveLink='" + archiveLink + '\'' +
                ", archived=" + archived +
                '}';
    }
}
