package nl.andreschepers.the_self_actualization_project.components.http;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HttpClient {

  private final RestClient restClient;

  public HttpClient() {
    restClient = RestClient.builder().requestFactory(createRequestFactory()).build();
  }

  private ClientHttpRequestFactory createRequestFactory() {
    PoolingHttpClientConnectionManager poolingConnManager =
        new PoolingHttpClientConnectionManager();
    var httpClient = HttpClients.custom().setConnectionManager(poolingConnManager).build();
    var factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    factory.setConnectionRequestTimeout(2000);
    factory.setReadTimeout(2000);
    factory.setConnectTimeout(2000);
    return factory;
  }
}
