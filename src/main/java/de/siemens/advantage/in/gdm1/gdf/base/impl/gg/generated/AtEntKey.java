package de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.util.Objects;

public class AtEntKey implements IAffinityKey {

    @AffinityKeyMapped
    private long affinityKey = -1;

    private int entityDefId;

    private String entityPkAttrs;

    public AtEntKey() {
    }

    private AtEntKey(int entityDefId, String entityPkAttrs) {
        this.entityDefId = entityDefId;
        this.entityPkAttrs = Objects.requireNonNull(entityPkAttrs);
        initAffinityKey();
    }

    public static AtEntKey of(int entityDefId, String entityPkAttrs) {
        return new AtEntKey(entityDefId, entityPkAttrs);
    }

    @Override
    public long getAffinityKey() {
        if (affinityKey == -1) {
            initAffinityKey();
        }
        return affinityKey;
    }

    private void initAffinityKey() {
        affinityKey = IAffinityKey.affinityKey(entityDefId, entityPkAttrs);
    }

    public int getEntityDefId() {
        return entityDefId;
    }

    public String getEntityPkAttrs() {
        return entityPkAttrs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtEntKey atEntKey = (AtEntKey) o;
        return entityDefId == atEntKey.entityDefId && entityPkAttrs.equals(atEntKey.entityPkAttrs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityDefId, entityPkAttrs);
    }

    @Override
    public String toString() {
        return "AtEntKey{" +
                "affinityKey=" + affinityKey +
                ", entityDefId=" + entityDefId +
                ", entityPkAttrs='" + entityPkAttrs + '\'' +
                '}';
    }
}
