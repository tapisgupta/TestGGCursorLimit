package de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated;

import java.util.LinkedList;
import java.util.List;

public class AtRef {
    private Long validFrom;
    private Long invalidFrom;
    private int targetEntityDefId;
    private String targetEntityPkAttrs;
    private List<AtEntKey> lineage;
    private AtEntKey lineageRoot;
    private int rkId;

    public AtRef() {
        lineage = new LinkedList<>();
    }

    private AtRef(long validFrom, long invalidFrom, int targetEntityDefId, String targetEntityPkAttrs
    ,int rkId) {
        this();
        this.validFrom = validFrom;
        this.invalidFrom = invalidFrom;
        this.targetEntityDefId = targetEntityDefId;
        this.targetEntityPkAttrs = targetEntityPkAttrs;
        this.rkId = rkId;
    }

    public AtRef(long validFrom, long invalidFrom, int targetEntityDefId, String targetEntityPkAttrs,
                 int rkId, AtRefKey key) {
        this(validFrom, invalidFrom, targetEntityDefId, targetEntityPkAttrs,rkId);
        AtEntKey parent = AtEntKey.of(targetEntityDefId, targetEntityPkAttrs);
        AtEntKey current = AtEntKey.of(key.getSourceEntityDefId(), key.getSourceEntityPkAttrs());
        lineage.add(parent);
        lineage.add(current);
        lineageRoot = parent;
    }

    public AtRef(long validFrom,
                 long invalidFrom,
                 int targetEntityDefId,
                 String targetEntityPkAttrs,
                 int rkId,
                 AtRefKey key,
                 AtRef parent) {
        this(validFrom, invalidFrom, targetEntityDefId, targetEntityPkAttrs,rkId);
        AtEntKey current = AtEntKey.of(key.getSourceEntityDefId(), key.getSourceEntityPkAttrs());
        lineage.addAll(parent.lineage);
        lineage.add(current);
        lineageRoot = parent.lineageRoot;
    }

    public Long getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Long validFrom) {
        this.validFrom = validFrom;
    }

    public Long getInvalidFrom() {
        return invalidFrom;
    }

    public void setInvalidFrom(Long invalidFrom) {
        this.invalidFrom = invalidFrom;
    }

    public int getTargetEntityDefId() {
        return targetEntityDefId;
    }

    public void setTargetEntityDefId(int targetEntityDefId) {
        this.targetEntityDefId = targetEntityDefId;
    }

    public String getTargetEntityPkAttrs() {
        return targetEntityPkAttrs;
    }

    public void setTargetEntityPkAttrs(String targetEntityPkAttrs) {
        this.targetEntityPkAttrs = targetEntityPkAttrs;
    }

    public List<AtEntKey> getLineage() {
        return lineage;
    }

    public void setLineage(List<AtEntKey> lineage) {
        this.lineage = lineage;
    }

    public AtEntKey getLineageRoot() {
        return lineageRoot;
    }

    public void setLineageRoot(AtEntKey lineageRoot) {
        this.lineageRoot = lineageRoot;
    }

    public int getRkId() { return rkId;}

    @Override
    public String toString() {
        return "AtRef{" + "targetEntityDefId=" + targetEntityDefId + ", targetEntityPkAttrs='" + targetEntityPkAttrs +'\'' + ", lineageRoot=" + lineageRoot + ", rkId=" + rkId + '}';
    }
}
