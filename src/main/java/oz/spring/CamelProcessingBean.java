package oz.spring;

import org.apache.camel.Exchange;

public class CamelProcessingBean {
	
	public void process(Exchange exchange){
		System.out.println("==> Payload: " + exchange.getIn().getBody());
		System.out.println("==> Headers: " + exchange.getIn().getHeaders());
	}

}
