package de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.util.Objects;

public class AtRefKey implements IAffinityKey {
    private int sourceEntityDefId;
    private String sourceEntityPkAttrs;
    @AffinityKeyMapped
    private long affinityKey = -1;

    public AtRefKey() {
    }

    public AtRefKey(int sourceEntityDefId, String sourceEntityPkAttrs) {
        this.sourceEntityDefId = sourceEntityDefId;
        this.sourceEntityPkAttrs = sourceEntityPkAttrs;

        this.affinityKey = IAffinityKey.affinityKey(sourceEntityDefId, sourceEntityPkAttrs);
    }

    public int getSourceEntityDefId() {
        return sourceEntityDefId;
    }

    public void setSourceEntityDefId(int sourceEntityDefId) {
        this.sourceEntityDefId = sourceEntityDefId;
    }

    public String getSourceEntityPkAttrs() {
        return sourceEntityPkAttrs;
    }

    public void setSourceEntityPkAttrs(String sourceEntityPkAttrs) {
        this.sourceEntityPkAttrs = sourceEntityPkAttrs;
    }

    @Override
    public long getAffinityKey() {
        if (affinityKey == -1) {
            affinityKey = IAffinityKey.affinityKey(sourceEntityDefId, sourceEntityPkAttrs);
        }
        return affinityKey;
    }

    public void setAffinityKey(long affinityKey) {
        this.affinityKey = affinityKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AtRefKey refKey = (AtRefKey) o;
        return sourceEntityDefId == refKey.sourceEntityDefId && sourceEntityPkAttrs.equals(refKey.sourceEntityPkAttrs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceEntityDefId, sourceEntityPkAttrs);
    }

    @Override
    public String toString() {
        return "AtRefKey{" + "sourceEntityDefId=" + sourceEntityDefId + ", sourceEntityPkAttrs='" + sourceEntityPkAttrs + '\'' + '}';
    }

    public AtEntKey toAtEntKey() {
        return AtEntKey.of(sourceEntityDefId, sourceEntityPkAttrs);
    }
}
