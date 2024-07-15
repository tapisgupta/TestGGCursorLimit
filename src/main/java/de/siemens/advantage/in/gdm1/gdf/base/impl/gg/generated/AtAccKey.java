package de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.util.Objects;


public class AtAccKey implements IAffinityKey {

    @AffinityKeyMapped
    private long affinityKey = -1;

    private int id;

    private String keyAttrs;

    private long vvalidFrom;

    public AtAccKey() {
    }

    private AtAccKey(Integer entityDefId, String entityPkAttrs) {
        affinityKey = IAffinityKey.affinityKey(entityDefId, entityPkAttrs);
    }

    public static AtAccKey forEnt(Integer entityDefId, String entityPkAttrs) {
        return new AtAccKey(entityDefId, entityPkAttrs);
    }

    @Override
    public long getAffinityKey() {
        return affinityKey;
    }

    public int getId() {
        return id;
    }

    public String getKeyAttrs() {
        return keyAttrs;
    }

    public long getVvalidFrom() {
        return vvalidFrom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKeyAttrs(String keyAttrs) {
        this.keyAttrs = keyAttrs;
    }

    public void setVvalidFrom(long vvalidFrom) {
        this.vvalidFrom = vvalidFrom;
    }

    public void initAffinityKey(AtAcc entity) {
        affinityKey = IAffinityKey.affinityKey(entity.getEntityDefId(), entity.getEntityPkAttrs());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtAccKey atAccKey = (AtAccKey) o;
        return id == atAccKey.id && vvalidFrom == atAccKey.vvalidFrom && keyAttrs.equals(atAccKey.keyAttrs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyAttrs, vvalidFrom);
    }

    @Override
    public String toString() {
        return "AtAccKey{" +
                "affinityKey=" + affinityKey +
                ", id=" + id +
                ", keyAttrs='" + keyAttrs + '\'' +
                ", vvalidFrom=" + vvalidFrom +
                '}';
    }
}
