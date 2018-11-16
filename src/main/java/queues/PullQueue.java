package queues;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskHandle;

public class PullQueue {
	
	private static PullQueue instance;
	
	public static PullQueue getInstance() {
		if(instance==null)
			instance = new PullQueue();
		return instance;
	}
	
	private Queue myQueue;
	
	private PullQueue() {
		myQueue = QueueFactory.getQueue("pull-queue");
	}
	
	public Queue getQueue() {
		return myQueue;
	}
	
	public void addTask(String taskName,String payload) {
		myQueue.add(TaskOptions.Builder.withMethod(TaskOptions.Method.PULL).payload(payload).taskName(taskName));
	}
	
	public void addTask(String taskName,byte[] payload) {
		myQueue.add(TaskOptions.Builder.withMethod(TaskOptions.Method.PULL).payload(payload).taskName(taskName));
	}
	
	public void addTask(String taskName,byte[] payload,String contentType) {
		myQueue.add(TaskOptions.Builder.withMethod(TaskOptions.Method.PULL).payload(payload,contentType).taskName(taskName));
	}
	
	public List<TaskHandle> taskLease(long time, TimeUnit timeUnit,long numberOfLease){
		return myQueue.leaseTasks(time, timeUnit, numberOfLease);
	}
	
	public void deleteTask(TaskHandle arg0) {
		myQueue.deleteTask(arg0);
	}
	public void deleteTask(String arg0) {
		myQueue.deleteTask(arg0);
	}
	public void deleteTask(List<TaskHandle> arg0) {
		myQueue.deleteTask(arg0);
	}
	public void deleteTaskAsync(TaskHandle arg0) {
		myQueue.deleteTaskAsync(arg0);
	}
	public void deleteTaskAsync(String arg0) {
		myQueue.deleteTaskAsync(arg0);
	}
	public void deleteTaskAsync(List<TaskHandle> arg0) {
		myQueue.deleteTaskAsync(arg0);
	}

}
