package org.project.model.resource;

import java.util.Objects;

public final class Citizen implements Resource {

    public Citizen() { }

    @Override public String getTypeName() { return "Citizen"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override public int hashCode() { return Objects.hash(getTypeName()); }

    @Override public String toString() { return "Citizen()"; }
}
