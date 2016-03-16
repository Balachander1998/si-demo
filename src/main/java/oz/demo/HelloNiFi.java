package oz.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;

public class HelloNiFi {
	int counter;
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		MessageChannel toSpring = context.getBean("from", MessageChannel.class);
		final PollableChannel fromSpring = context.getBean("to", PollableChannel.class);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				while (true){
					Object m = fromSpring.receive(5);
					if (m != null){
						System.out.println(m);
					}
				}
			}
		});
		
		for (int i = 0; i < 50; i++) {
			toSpring.send(MessageBuilder.withPayload("Blah".getBytes()).build());
		}
		//System.out.println(fromSpring.receive());
		
		context.close();
	}
	
	
	
	
	public byte[] sendReceive(byte[] data) {
		System.out.println("=============> HELLO NIFI-");
		return (counter++ + " =============> HELLO NIFI " + data).getBytes(); 
	}
}



