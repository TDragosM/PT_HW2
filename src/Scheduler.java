import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    public final List<Server> servers = new ArrayList<>();
    private final int maxNoServers;
    private final int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer, SelectionPolicy selectionPolicy) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(i,maxTasksPerServer);
            servers.add(server);
            Thread thread = new Thread(server);
            thread.start();
        }
        setStrategy(selectionPolicy);
    }

    public void setStrategy(SelectionPolicy policy) {
        switch (policy) {
            case SHORTEST_QUEUE -> strategy =  new ConcreteStrategyQueue();
            case SHORTEST_TIME -> strategy =  new ConcreteStrategyTime();
            default -> throw new IllegalArgumentException("Unsupported selection policy: " + policy);
        }
    }

    public void dispatchTask(Task task) {
        strategy.addTask(servers, task);
    }

    public List<Server> getServers() {
        return new ArrayList<>(servers);
    }
}
