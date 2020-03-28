package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AuthorityList implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<AuthorityEntity> git;

    private List<AuthorityEntity> mail;

    private List<AuthorityEntity> file;

    public AuthorityList(List<AuthorityEntity> git, List<AuthorityEntity> mail, List<AuthorityEntity> file) {
        this.git = git;
        this.mail = mail;
        this.file = file;
    }

    public List<AuthorityEntity> getGit() {
        return git;
    }

    public void setGit(List<AuthorityEntity> git) {
        this.git = git;
    }

    public List<AuthorityEntity> getMail() {
        return mail;
    }

    public void setMail(List<AuthorityEntity> mail) {
        this.mail = mail;
    }

    public List<AuthorityEntity> getFile() {
        return file;
    }

    public void setFile(List<AuthorityEntity> file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityList that = (AuthorityList) o;
        return Objects.equals(git, that.git) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(git, mail, file);
    }

    @Override
    public String toString() {
        return "AuthorityList{" +
                "git=" + git +
                ", mail=" + mail +
                ", file=" + file +
                '}';
    }
}
