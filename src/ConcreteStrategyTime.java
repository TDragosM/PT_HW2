import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    public void addTask(List<Server> servers, Task task) {
        Server selectedServer = null;
        int minWaitingTime = Integer.MAX_VALUE;

        for (Server server : servers) {
            int waitingTime = server.getWaitingPeriod();
            if (waitingTime < minWaitingTime && server.hasCapacity()) {
                selectedServer = server;
                minWaitingTime = waitingTime;
            }
        }

        if (selectedServer != null) {
            selectedServer.addTask(task);
        }
    }
}
