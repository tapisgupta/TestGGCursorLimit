package org.example;

import de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated.AtEnt;
import de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated.AtEntKey;

public class EntQueryResult {
    private final AtEntKey key;
    private final AtEnt entity;

    public EntQueryResult(AtEntKey key, AtEnt entity) {
        this.key = key;
        this.entity = entity;
    }

    public AtEntKey getKey() {
        return key;
    }

    public AtEnt getEntity() {
        return entity;
    }

    @Override
    public String toString() {
        return "EntQueryResult{" +
                "key=" + key.toString() +
                ", entity=" + entity.toString() +
                '}';
    }
}
