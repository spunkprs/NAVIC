package lld.notificationSystem.factory;

import lld.notificationSystem.channel.Channels;
import lld.notificationSystem.model.Message;
import java.util.Map;

/**
Though we are making use of factory but to get rid of if-else block
we are making use of Strategy i.e MessageCreator [different implementations available]
and depending on the channel at the run time we wil decide which implementation of MessageCreator
will be used
 * */

public class MessageCreationFactory {

    private Map<Channels, MessageCreator> messageCreatorMap;

    public MessageCreationFactory(Map<Channels, MessageCreator> messageCreatorMap) {
        this.messageCreatorMap = messageCreatorMap;
    }

    public Message createMessage(Channels channel, String content, String fromAddress, String toAddress) {
        MessageCreator creator = messageCreatorMap.get(channel);
        if (creator == null) {
            throw new IllegalArgumentException("Unsupported channel: " + channel);
        }
        return creator.createMessage(content, fromAddress, toAddress);
    }
}
