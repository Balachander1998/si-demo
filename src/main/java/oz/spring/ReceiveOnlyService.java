package oz.spring;

import java.util.Map;

public class ReceiveOnlyService {

	public void printMessage(String payload, Map<String, Object> headers) {
		System.out.println("\n=> Received message form NIFI");
		System.out.println("=> Payload: " + payload);
		System.out.println("=> Headers: " + headers);
	}
}
