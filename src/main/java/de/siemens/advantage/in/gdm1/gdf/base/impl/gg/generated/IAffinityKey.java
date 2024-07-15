package de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated;

import org.apache.commons.codec.digest.MurmurHash3;

import java.nio.charset.StandardCharsets;

public interface IAffinityKey {
    long getAffinityKey();

    static long affinityKey(Integer defId, String attr) {
        return MurmurHash3.hash32x86((defId + "|" + attr).getBytes(StandardCharsets.UTF_8));
    }
}
