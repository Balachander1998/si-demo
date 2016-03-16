package oz.spring;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.SmartLifecycle;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class SendOnlyService implements SmartLifecycle {
	
	private volatile MessageChannel channel;
	
	private volatile boolean started;
	
	private volatile ScheduledExecutorService executor;

	public void setChannel(MessageChannel channel) {
		this.channel = channel;
	}

	@Override
	public void start() {
		this.executor = Executors.newSingleThreadScheduledExecutor();
		final Random r = new Random();
		this.started = true;
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("=> Generating message");
				Message<String> message = MessageBuilder
						.withPayload("Hello-" + UUID.randomUUID())
						.setHeader("foo", String.valueOf(new Random().nextInt(100))).build();
				channel.send(message);
				executor.schedule(this, r.nextInt(2000), TimeUnit.MILLISECONDS);
			}
		});
	}

	@Override
	public void stop() {
		this.started = false;
		this.executor.shutdown();
	}

	@Override
	public boolean isRunning() {
		return this.started;
	}

	@Override
	public int getPhase() {
		return 0;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		this.stop();
		callback.run();
	}

}
