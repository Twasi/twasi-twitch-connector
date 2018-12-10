package net.twasi.twitchconnector.dto;

/**
 * This object is sent from Twasi-Core on startup.
 */
public class InitialSetupDto extends DataTransferObject {

    /**
     * The name of the Twasi-Core instance
     */
    String instanceName;

    /**
     * The version of the Twasi-Core instance
     */
    String version;

    /**
     * Information about current channels state
     */
    ChannelStateInfoDto channelStateInfo;

}
