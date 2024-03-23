import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final int id;
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;
    private final int maxTasksPerQueue;

    public Server(int id, int maxTasksPerQueue) {
        this.id = id;
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger();
        this.maxTasksPerQueue = maxTasksPerQueue;
    }

    public boolean hasCapacity() {
        return tasks.size() < maxTasksPerQueue;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public int getQueueSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        try {
            tasks.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        waitingPeriod.addAndGet(task.getServiceTime());
    }

    public Task[] getTasks() {
        BlockingQueue<Task> queueCopy = new LinkedBlockingQueue<>(tasks);
        return queueCopy.toArray(new Task[queueCopy.size()]);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = tasks.peek();
                if (task != null) {
                    Thread.sleep(1000);
                    task.setServiceTime(task.getServiceTime() - 1);
                    waitingPeriod.decrementAndGet();
                    if (task.getServiceTime() == 0) {
                        tasks.take();
                    }
                } else {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getServerId() {
        return id;
    }
}
