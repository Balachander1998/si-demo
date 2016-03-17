package oz.spring;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

public class CamelSampleSender {
	
	private final ProducerTemplate producerTemplate;
	
	private final String defaultEndpointUri;
	
	public CamelSampleSender(CamelContext camelContext, String defaultEndPointUri){
		this.producerTemplate = camelContext.createProducerTemplate();
		this.defaultEndpointUri = defaultEndPointUri;
	}
	

	public void toCamel(Object payload, Map<String, Object> headers) throws Exception {
		System.out.println("Sending to Camel");
		producerTemplate.setDefaultEndpointUri(this.defaultEndpointUri);
		producerTemplate.sendBodyAndHeaders(payload, headers);
	}
}
