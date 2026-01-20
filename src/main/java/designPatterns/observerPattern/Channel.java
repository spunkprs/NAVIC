package designPatterns.observerPattern;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Channel {
    private String channelId;
    private Set<Subscriber> subscriberSet;

    public Channel(String channelId) {
        this.channelId = channelId;
        this.subscriberSet = new HashSet<>();
    }

    public void pushNotificationToSubscribers(String information) {
        for (Subscriber subscriber : subscriberSet) {
            subscriber.receiveInformation(information, this);
        }
    }

    public void addSubscriber(Subscriber subscriber) {
        if (!subscriberSet.contains(subscriber)) {
            subscriberSet.add(subscriber);
        }
    }

    public void removeSubscriber(Subscriber subscriber) {
        if (subscriberSet.contains(subscriber)) {
            subscriberSet.remove(subscriber);
        }
    }

    public String getChannelId() {
        return channelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return channelId.equals(channel.channelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId);
    }
}
