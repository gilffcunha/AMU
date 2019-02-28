package com.example.lightmonitor;

import java.util.Objects;

public class Protocol
{
    private int id;
    private String type;
    private String description;

    public Protocol(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Protocol() {
        this.id = 0;
        this.type = "";
        this.description = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Protocol)) return false;
        Protocol protocol = (Protocol) o;
        return getId() == protocol.getId() &&
                Objects.equals(getType(), protocol.getType()) &&
                Objects.equals(getDescription(), protocol.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDescription());
    }
}
