package designPatterns.observerPattern;

public class Runner {

    public static void main(String ar[]) {
        Channel channelOne = new Channel("1211");
        Channel channelTwo = new Channel("1212");
        Channel channelThree = new Channel("1213");

        Subscriber subscriberOne = new Subscriber("1");
        Subscriber subscriberTwo = new Subscriber("2");
        Subscriber subscriberThree = new Subscriber("3");

        subscriberOne.subscribeToChannel(channelOne);
        subscriberOne.subscribeToChannel(channelTwo);
        subscriberOne.subscribeToChannel(channelThree);

        subscriberTwo.subscribeToChannel(channelTwo);

        subscriberThree.subscribeToChannel(channelOne);

        subscriberOne.unsubscribeToChannel(channelOne);

        channelOne.pushNotificationToSubscribers("New content released !!");
        channelTwo.pushNotificationToSubscribers("New content released !!");
        channelThree.pushNotificationToSubscribers("New content released !!");
    }
}
