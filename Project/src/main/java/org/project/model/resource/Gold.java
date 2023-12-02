package org.project.model.resource;

import java.util.Objects;

public final class Gold implements Resource {

    public Gold() { }

    @Override  public String getTypeName() { return "Gold"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override public int hashCode() { return Objects.hash(getTypeName()); }

    @Override public String toString() { return "Gold()"; }
}
