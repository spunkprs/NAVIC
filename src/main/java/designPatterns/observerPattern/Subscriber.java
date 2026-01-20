package designPatterns.observerPattern;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
Subscriber has set of subscribed channels, can subscribe OR unsubscribe a Channel
 * */

public class Subscriber {
    private String subscriberId;
    private Set<Channel> subscribedChannels;

    public Subscriber(String subscriberId) {
        this.subscriberId = subscriberId;
        this.subscribedChannels = new HashSet<>();
    }

    public void subscribeToChannel(Channel channel) {
        if (!subscribedChannels.contains(channel)) {
            subscribedChannels.add(channel);
            channel.addSubscriber(this);
        }
    }

    public void unsubscribeToChannel(Channel channel) {
        if (subscribedChannels.contains(channel)) {
            subscribedChannels.remove(channel);
            channel.removeSubscriber(this);
        }
    }

    public void receiveInformation(String information, Channel channel) {
        System.out.println("Information received " + information + " from channel " + channel.getChannelId() + " to subscriber " + this.subscriberId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return subscriberId.equals(that.subscriberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriberId);
    }
}
