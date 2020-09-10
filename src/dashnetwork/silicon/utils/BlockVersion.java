package dashnetwork.silicon.utils;

import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public interface BlockVersion {

    ProtocolVersion getVersion();

    int getRemapId();

}
