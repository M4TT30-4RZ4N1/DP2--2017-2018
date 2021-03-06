
package it.polito.dp2.NFV.sol3.client2.nfvdeployer;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.uri.UriTemplate;

@Generated(value = {
    "wadl|http://localhost:8080/NfvDeployer/rest/application.wadl"
}, comments = "wadl2java, http://wadl.java.net", date = "2018-01-19T13:11:52.003+01:00")
public class NfvDeployer {

    /**
     * The base URI for the resource represented by this proxy
     * 
     */
    public final static URI BASE_URI;

    static {
    	String newUri = System.getProperty("it.polito.dp2.NFV.lab3.URL", "http://localhost:8080/NfvDeployer/rest/");
    	URI originalURI = URI.create(newUri);
        //URI originalURI = URI.create("http://localhost:8080/NfvDeployer/rest/");
        // Look up to see if we have any indirection in the local copy
        // of META-INF/java-rs-catalog.xml file, assuming it will be in the
        // oasis:name:tc:entity:xmlns:xml:catalog namespace or similar duck type
        java.io.InputStream is = NfvDeployer.class.getResourceAsStream("/META-INF/jax-rs-catalog.xml");
        if (is!=null) {
            try {
                // Ignore the namespace in the catalog, can't use wildcard until
                // we are sure we have XPath 2.0
                String found = javax.xml.xpath.XPathFactory.newInstance().newXPath().evaluate(
                    "/*[name(.) = 'catalog']/*[name(.) = 'uri' and @name ='" + originalURI +"']/@uri", 
                    new org.xml.sax.InputSource(is)); 
                if (found!=null && found.length()>0) {
                    originalURI = java.net.URI.create(found);
                }
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    is.close();
                } catch (java.io.IOException e) {
                }
            }
        }
        BASE_URI = originalURI;
    }

    public static NfvDeployer.Root root(Client client, URI baseURI) {
        return new NfvDeployer.Root(client, baseURI);
    }

    /**
     * Template method to allow tooling to customize the new Client
     * 
     */
    private static void customizeClientConfiguration(ClientConfig cc) {
    }

    /**
     * Template method to allow tooling to override Client factory
     * 
     */
    private static Client createClientInstance(ClientConfig cc) {
        return Client.create(cc);
    }

    /**
     * Create a new Client instance
     * 
     */
    public static Client createClient() {
        ClientConfig cc = new DefaultClientConfig();
        customizeClientConfiguration(cc);
        return createClientInstance(cc);
    }

    public static NfvDeployer.Root root() {
        return root(createClient(), BASE_URI);
    }

    public static NfvDeployer.Root root(Client client) {
        return root(client, BASE_URI);
    }

    public static class Root {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;

        private Root(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        /**
         * Create new instance using existing Client instance, and a base URI and any parameters
         * 
         */
        public Root(Client client, URI baseUri) {
            _client = client;
            _uriBuilder = UriBuilder.fromUri(baseUri);
            _uriBuilder = _uriBuilder.path("/");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        public RNfv getAsRNfvXml() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(RNfv.class);
        }

        public<T >T getAsXml(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsXml(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public RNfv getAsRNfvTextXml() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("text/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(RNfv.class);
        }

        public<T >T getAsTextXml(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("text/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsTextXml(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("text/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public NfvDeployer.Root.Catalog catalog() {
            return new NfvDeployer.Root.Catalog(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public NfvDeployer.Root.Nffgs nffgs() {
            return new NfvDeployer.Root.Nffgs(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public NfvDeployer.Root.Hosts hosts() {
            return new NfvDeployer.Root.Hosts(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public NfvDeployer.Root.SwaggerJson swaggerJson() {
            return new NfvDeployer.Root.SwaggerJson(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public NfvDeployer.Root.SwaggerYaml swaggerYaml() {
            return new NfvDeployer.Root.SwaggerYaml(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class Catalog {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private Catalog(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public Catalog(Client client, URI baseUri) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("catalog");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            public RVnfts getAsRVnftsXml() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RVnfts.class);
            }

            public<T >T getAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RVnfts getAsRVnftsTextXml() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RVnfts.class);
            }

            public<T >T getAsTextXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public NfvDeployer.Root.Catalog.VnfName vnfName(String vnfname) {
                return new NfvDeployer.Root.Catalog.VnfName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), vnfname);
            }

            public static class VnfName {

                private Client _client;
                private UriBuilder _uriBuilder;
                private Map<String, Object> _templateAndMatrixParameterValues;

                private VnfName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                    _client = client;
                    _uriBuilder = uriBuilder.clone();
                    _templateAndMatrixParameterValues = map;
                }

                /**
                 * Create new instance using existing Client instance, and a base URI and any parameters
                 * 
                 */
                public VnfName(Client client, URI baseUri, String vnfname) {
                    _client = client;
                    _uriBuilder = UriBuilder.fromUri(baseUri);
                    _uriBuilder = _uriBuilder.path("{vnfName}");
                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    _templateAndMatrixParameterValues.put("vnfName", vnfname);
                }

                /**
                 * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
                 * 
                 */
                public VnfName(Client client, URI uri) {
                    _client = client;
                    StringBuilder template = new StringBuilder(BASE_URI.toString());
                    if (template.charAt((template.length()- 1))!= '/') {
                        template.append("/catalog/{vnfName}");
                    } else {
                        template.append("catalog/{vnfName}");
                    }
                    _uriBuilder = UriBuilder.fromPath(template.toString());
                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    UriTemplate uriTemplate = new UriTemplate(template.toString());
                    HashMap<String, String> parameters = new HashMap<String, String>();
                    uriTemplate.match(uri.toString(), parameters);
                    _templateAndMatrixParameterValues.putAll(parameters);
                }

                /**
                 * Get vnfName
                 * 
                 */
                public String getVnfname() {
                    return ((String) _templateAndMatrixParameterValues.get("vnfName"));
                }

                /**
                 * Duplicate state and set vnfName
                 * 
                 */
                public NfvDeployer.Root.Catalog.VnfName setVnfname(String vnfname) {
                    Map<String, Object> copyMap;
                    copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                    UriBuilder copyUriBuilder = _uriBuilder.clone();
                    copyMap.put("vnfName", vnfname);
                    return new NfvDeployer.Root.Catalog.VnfName(_client, copyUriBuilder, copyMap);
                }

                public RVnft getAsRVnftXml() {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(RVnft.class);
                }

                public<T >T getAsXml(GenericType<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(returnType);
                }

                public<T >T getAsXml(Class<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                    }
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        return response.getEntity(returnType);
                    } else {
                        return returnType.cast(response);
                    }
                }

                public RVnft getAsRVnftTextXml() {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(RVnft.class);
                }

                public<T >T getAsTextXml(GenericType<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(returnType);
                }

                public<T >T getAsTextXml(Class<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                    }
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        return response.getEntity(returnType);
                    } else {
                        return returnType.cast(response);
                    }
                }

            }

        }

        public static class Hosts {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private Hosts(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public Hosts(Client client, URI baseUri) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("hosts");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            public RHosts getAsRHostsXml() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RHosts.class);
            }

            public<T >T getAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RHosts getAsRHostsTextXml() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RHosts.class);
            }

            public<T >T getAsTextXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public NfvDeployer.Root.Hosts.HostName hostName(String hostname) {
                return new NfvDeployer.Root.Hosts.HostName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), hostname);
            }

            public static class HostName {

                private Client _client;
                private UriBuilder _uriBuilder;
                private Map<String, Object> _templateAndMatrixParameterValues;

                private HostName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                    _client = client;
                    _uriBuilder = uriBuilder.clone();
                    _templateAndMatrixParameterValues = map;
                }

                /**
                 * Create new instance using existing Client instance, and a base URI and any parameters
                 * 
                 */
                public HostName(Client client, URI baseUri, String hostname) {
                    _client = client;
                    _uriBuilder = UriBuilder.fromUri(baseUri);
                    _uriBuilder = _uriBuilder.path("{hostName}");
                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    _templateAndMatrixParameterValues.put("hostName", hostname);
                }

                /**
                 * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
                 * 
                 */
                public HostName(Client client, URI uri) {
                    _client = client;
                    StringBuilder template = new StringBuilder(BASE_URI.toString());
                    if (template.charAt((template.length()- 1))!= '/') {
                        template.append("/hosts/{hostName}");
                    } else {
                        template.append("hosts/{hostName}");
                    }
                    _uriBuilder = UriBuilder.fromPath(template.toString());
                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    UriTemplate uriTemplate = new UriTemplate(template.toString());
                    HashMap<String, String> parameters = new HashMap<String, String>();
                    uriTemplate.match(uri.toString(), parameters);
                    _templateAndMatrixParameterValues.putAll(parameters);
                }

                /**
                 * Get hostName
                 * 
                 */
                public String getHostname() {
                    return ((String) _templateAndMatrixParameterValues.get("hostName"));
                }

                /**
                 * Duplicate state and set hostName
                 * 
                 */
                public NfvDeployer.Root.Hosts.HostName setHostname(String hostname) {
                    Map<String, Object> copyMap;
                    copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                    UriBuilder copyUriBuilder = _uriBuilder.clone();
                    copyMap.put("hostName", hostname);
                    return new NfvDeployer.Root.Hosts.HostName(_client, copyUriBuilder, copyMap);
                }

                public RHost getAsRHostXml() {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(RHost.class);
                }

                public<T >T getAsXml(GenericType<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(returnType);
                }

                public<T >T getAsXml(Class<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                    }
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        return response.getEntity(returnType);
                    } else {
                        return returnType.cast(response);
                    }
                }

                public RHost getAsRHostTextXml() {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(RHost.class);
                }

                public<T >T getAsTextXml(GenericType<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(returnType);
                }

                public<T >T getAsTextXml(Class<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                    }
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        return response.getEntity(returnType);
                    } else {
                        return returnType.cast(response);
                    }
                }

                public NfvDeployer.Root.Hosts.HostName.Connections connections() {
                    return new NfvDeployer.Root.Hosts.HostName.Connections(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                }

                public static class Connections {

                    private Client _client;
                    private UriBuilder _uriBuilder;
                    private Map<String, Object> _templateAndMatrixParameterValues;

                    private Connections(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                        _client = client;
                        _uriBuilder = uriBuilder.clone();
                        _templateAndMatrixParameterValues = map;
                    }

                    /**
                     * Create new instance using existing Client instance, and a base URI and any parameters
                     * 
                     */
                    public Connections(Client client, URI baseUri) {
                        _client = client;
                        _uriBuilder = UriBuilder.fromUri(baseUri);
                        _uriBuilder = _uriBuilder.path("connections");
                        _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    }

                    public RConnections getAsRConnectionsXml() {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RConnections.class);
                    }

                    public<T >T getAsXml(GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T getAsXml(Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public RConnections getAsRConnectionsTextXml() {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RConnections.class);
                    }

                    public<T >T getAsTextXml(GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T getAsTextXml(Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                }

            }

        }

        public static class Nffgs {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private Nffgs(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public Nffgs(Client client, URI baseUri) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("nffgs");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            public RNffgs getAsRNffgsXml() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RNffgs.class);
            }

            public<T >T getAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RNffgs getAsRNffgsXml(String deploytime) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                if (deploytime == null) {
                }
                if (deploytime!= null) {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", deploytime);
                } else {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", ((Object[]) null));
                }
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RNffgs.class);
            }

            public<T >T getAsXml(String deploytime, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                if (deploytime == null) {
                }
                if (deploytime!= null) {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", deploytime);
                } else {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", ((Object[]) null));
                }
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(String deploytime, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                if (deploytime == null) {
                }
                if (deploytime!= null) {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", deploytime);
                } else {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", ((Object[]) null));
                }
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RNffgs getAsRNffgsTextXml() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RNffgs.class);
            }

            public<T >T getAsTextXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RNffgs getAsRNffgsTextXml(String deploytime) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                if (deploytime == null) {
                }
                if (deploytime!= null) {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", deploytime);
                } else {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", ((Object[]) null));
                }
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RNffgs.class);
            }

            public<T >T getAsTextXml(String deploytime, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                if (deploytime == null) {
                }
                if (deploytime!= null) {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", deploytime);
                } else {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", ((Object[]) null));
                }
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextXml(String deploytime, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                if (deploytime == null) {
                }
                if (deploytime!= null) {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", deploytime);
                } else {
                    localUriBuilder = localUriBuilder.replaceQueryParam("deployTime", ((Object[]) null));
                }
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RNffg postXmlAsRNffg(NewRnffg input) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                resourceBuilder = resourceBuilder.type("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RNffg.class);
            }

            public<T >T postXml(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                resourceBuilder = resourceBuilder.type("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T postXml(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                resourceBuilder = resourceBuilder.type("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RNffg postTextXmlAsRNffg(NewRnffg input) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                resourceBuilder = resourceBuilder.type("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RNffg.class);
            }

            public<T >T postTextXml(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                resourceBuilder = resourceBuilder.type("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T postTextXml(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/xml");
                resourceBuilder = resourceBuilder.type("text/xml");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public NfvDeployer.Root.Nffgs.NffgName nffgName(String nffgname) {
                return new NfvDeployer.Root.Nffgs.NffgName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), nffgname);
            }

            public static class NffgName {

                private Client _client;
                private UriBuilder _uriBuilder;
                private Map<String, Object> _templateAndMatrixParameterValues;

                private NffgName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                    _client = client;
                    _uriBuilder = uriBuilder.clone();
                    _templateAndMatrixParameterValues = map;
                }

                /**
                 * Create new instance using existing Client instance, and a base URI and any parameters
                 * 
                 */
                public NffgName(Client client, URI baseUri, String nffgname) {
                    _client = client;
                    _uriBuilder = UriBuilder.fromUri(baseUri);
                    _uriBuilder = _uriBuilder.path("{nffgName}");
                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    _templateAndMatrixParameterValues.put("nffgName", nffgname);
                }

                /**
                 * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
                 * 
                 */
                public NffgName(Client client, URI uri) {
                    _client = client;
                    StringBuilder template = new StringBuilder(BASE_URI.toString());
                    if (template.charAt((template.length()- 1))!= '/') {
                        template.append("/nffgs/{nffgName}");
                    } else {
                        template.append("nffgs/{nffgName}");
                    }
                    _uriBuilder = UriBuilder.fromPath(template.toString());
                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    UriTemplate uriTemplate = new UriTemplate(template.toString());
                    HashMap<String, String> parameters = new HashMap<String, String>();
                    uriTemplate.match(uri.toString(), parameters);
                    _templateAndMatrixParameterValues.putAll(parameters);
                }

                /**
                 * Get nffgName
                 * 
                 */
                public String getNffgname() {
                    return ((String) _templateAndMatrixParameterValues.get("nffgName"));
                }

                /**
                 * Duplicate state and set nffgName
                 * 
                 */
                public NfvDeployer.Root.Nffgs.NffgName setNffgname(String nffgname) {
                    Map<String, Object> copyMap;
                    copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                    UriBuilder copyUriBuilder = _uriBuilder.clone();
                    copyMap.put("nffgName", nffgname);
                    return new NfvDeployer.Root.Nffgs.NffgName(_client, copyUriBuilder, copyMap);
                }

                public RNffg getAsRNffgXml() {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(RNffg.class);
                }

                public<T >T getAsXml(GenericType<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(returnType);
                }

                public<T >T getAsXml(Class<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("application/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                    }
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        return response.getEntity(returnType);
                    } else {
                        return returnType.cast(response);
                    }
                }

                public RNffg getAsRNffgTextXml() {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(RNffg.class);
                }

                public<T >T getAsTextXml(GenericType<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(returnType);
                }

                public<T >T getAsTextXml(Class<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    resourceBuilder = resourceBuilder.accept("text/xml");
                    ClientResponse response;
                    response = resourceBuilder.method("GET", ClientResponse.class);
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                    }
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        return response.getEntity(returnType);
                    } else {
                        return returnType.cast(response);
                    }
                }

                public ClientResponse delete() {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    ClientResponse response;
                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                    return response;
                }

                public<T >T delete(GenericType<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    ClientResponse response;
                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                    return response.getEntity(returnType);
                }

                public<T >T delete(Class<T> returnType) {
                    UriBuilder localUriBuilder = _uriBuilder.clone();
                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                    ClientResponse response;
                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                    }
                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                        return response.getEntity(returnType);
                    } else {
                        return returnType.cast(response);
                    }
                }

                public NfvDeployer.Root.Nffgs.NffgName.Nodes nodes() {
                    return new NfvDeployer.Root.Nffgs.NffgName.Nodes(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                }

                public NfvDeployer.Root.Nffgs.NffgName.Links links() {
                    return new NfvDeployer.Root.Nffgs.NffgName.Links(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                }

                public static class Links {

                    private Client _client;
                    private UriBuilder _uriBuilder;
                    private Map<String, Object> _templateAndMatrixParameterValues;

                    private Links(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                        _client = client;
                        _uriBuilder = uriBuilder.clone();
                        _templateAndMatrixParameterValues = map;
                    }

                    /**
                     * Create new instance using existing Client instance, and a base URI and any parameters
                     * 
                     */
                    public Links(Client client, URI baseUri) {
                        _client = client;
                        _uriBuilder = UriBuilder.fromUri(baseUri);
                        _uriBuilder = _uriBuilder.path("links");
                        _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    }

                    public RLinks getAsRLinksXml() {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RLinks.class);
                    }

                    public<T >T getAsXml(GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T getAsXml(Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public RLinks getAsRLinksTextXml() {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RLinks.class);
                    }

                    public<T >T getAsTextXml(GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T getAsTextXml(Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public RLink postXmlAsRLink(RLink input) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        resourceBuilder = resourceBuilder.type("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RLink.class);
                    }

                    public<T >T postXml(Object input, GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        resourceBuilder = resourceBuilder.type("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T postXml(Object input, Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        resourceBuilder = resourceBuilder.type("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public RLink postTextXmlAsRLink(RLink input) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        resourceBuilder = resourceBuilder.type("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RLink.class);
                    }

                    public<T >T postTextXml(Object input, GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        resourceBuilder = resourceBuilder.type("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T postTextXml(Object input, Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        resourceBuilder = resourceBuilder.type("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public NfvDeployer.Root.Nffgs.NffgName.Links.LinkName linkName(String linkname) {
                        return new NfvDeployer.Root.Nffgs.NffgName.Links.LinkName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), linkname);
                    }

                    public static class LinkName {

                        private Client _client;
                        private UriBuilder _uriBuilder;
                        private Map<String, Object> _templateAndMatrixParameterValues;

                        private LinkName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                            _client = client;
                            _uriBuilder = uriBuilder.clone();
                            _templateAndMatrixParameterValues = map;
                        }

                        /**
                         * Create new instance using existing Client instance, and a base URI and any parameters
                         * 
                         */
                        public LinkName(Client client, URI baseUri, String linkname) {
                            _client = client;
                            _uriBuilder = UriBuilder.fromUri(baseUri);
                            _uriBuilder = _uriBuilder.path("{linkName}");
                            _templateAndMatrixParameterValues = new HashMap<String, Object>();
                            _templateAndMatrixParameterValues.put("linkName", linkname);
                        }

                        /**
                         * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
                         * 
                         */
                        public LinkName(Client client, URI uri) {
                            _client = client;
                            StringBuilder template = new StringBuilder(BASE_URI.toString());
                            if (template.charAt((template.length()- 1))!= '/') {
                                template.append("/nffgs/{nffgName}/links/{linkName}");
                            } else {
                                template.append("nffgs/{nffgName}/links/{linkName}");
                            }
                            _uriBuilder = UriBuilder.fromPath(template.toString());
                            _templateAndMatrixParameterValues = new HashMap<String, Object>();
                            UriTemplate uriTemplate = new UriTemplate(template.toString());
                            HashMap<String, String> parameters = new HashMap<String, String>();
                            uriTemplate.match(uri.toString(), parameters);
                            _templateAndMatrixParameterValues.putAll(parameters);
                        }

                        /**
                         * Get linkName
                         * 
                         */
                        public String getLinkname() {
                            return ((String) _templateAndMatrixParameterValues.get("linkName"));
                        }

                        /**
                         * Duplicate state and set linkName
                         * 
                         */
                        public NfvDeployer.Root.Nffgs.NffgName.Links.LinkName setLinkname(String linkname) {
                            Map<String, Object> copyMap;
                            copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                            UriBuilder copyUriBuilder = _uriBuilder.clone();
                            copyMap.put("linkName", linkname);
                            return new NfvDeployer.Root.Nffgs.NffgName.Links.LinkName(_client, copyUriBuilder, copyMap);
                        }

                        public RLink getAsRLinkXml() {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RLink.class);
                        }

                        public<T >T getAsXml(GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T getAsXml(Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RLink getAsRLinkTextXml() {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RLink.class);
                        }

                        public<T >T getAsTextXml(GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T getAsTextXml(Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RLink putXmlAsRLink(RLink input) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            resourceBuilder = resourceBuilder.type("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RLink.class);
                        }

                        public<T >T putXml(Object input, GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            resourceBuilder = resourceBuilder.type("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T putXml(Object input, Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            resourceBuilder = resourceBuilder.type("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RLink putTextXmlAsRLink(RLink input) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            resourceBuilder = resourceBuilder.type("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RLink.class);
                        }

                        public<T >T putTextXml(Object input, GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            resourceBuilder = resourceBuilder.type("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T putTextXml(Object input, Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            resourceBuilder = resourceBuilder.type("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RLink deleteAsRLinkXml() {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RLink.class);
                        }

                        public<T >T deleteAsXml(GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T deleteAsXml(Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RLink deleteAsRLinkTextXml() {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RLink.class);
                        }

                        public<T >T deleteAsTextXml(GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T deleteAsTextXml(Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                    }

                }

                public static class Nodes {

                    private Client _client;
                    private UriBuilder _uriBuilder;
                    private Map<String, Object> _templateAndMatrixParameterValues;

                    private Nodes(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                        _client = client;
                        _uriBuilder = uriBuilder.clone();
                        _templateAndMatrixParameterValues = map;
                    }

                    /**
                     * Create new instance using existing Client instance, and a base URI and any parameters
                     * 
                     */
                    public Nodes(Client client, URI baseUri) {
                        _client = client;
                        _uriBuilder = UriBuilder.fromUri(baseUri);
                        _uriBuilder = _uriBuilder.path("nodes");
                        _templateAndMatrixParameterValues = new HashMap<String, Object>();
                    }

                    public RNodes getAsRNodesXml() {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RNodes.class);
                    }

                    public<T >T getAsXml(GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T getAsXml(Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public RNodes getAsRNodesTextXml() {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RNodes.class);
                    }

                    public<T >T getAsTextXml(GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T getAsTextXml(Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("GET", ClientResponse.class);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public RNode postXmlAsRNode(RNode input) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        resourceBuilder = resourceBuilder.type("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RNode.class);
                    }

                    public<T >T postXml(Object input, GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        resourceBuilder = resourceBuilder.type("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T postXml(Object input, Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("application/xml");
                        resourceBuilder = resourceBuilder.type("application/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public RNode postTextXmlAsRNode(RNode input) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        resourceBuilder = resourceBuilder.type("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(RNode.class);
                    }

                    public<T >T postTextXml(Object input, GenericType<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        resourceBuilder = resourceBuilder.type("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (response.getStatus()>= 400) {
                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                        }
                        return response.getEntity(returnType);
                    }

                    public<T >T postTextXml(Object input, Class<T> returnType) {
                        UriBuilder localUriBuilder = _uriBuilder.clone();
                        WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                        resourceBuilder = resourceBuilder.accept("text/xml");
                        resourceBuilder = resourceBuilder.type("text/xml");
                        ClientResponse response;
                        response = resourceBuilder.method("POST", ClientResponse.class, input);
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                        }
                        if (!ClientResponse.class.isAssignableFrom(returnType)) {
                            return response.getEntity(returnType);
                        } else {
                            return returnType.cast(response);
                        }
                    }

                    public NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName nodeName(String nodename) {
                        return new NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), nodename);
                    }

                    public static class NodeName {

                        private Client _client;
                        private UriBuilder _uriBuilder;
                        private Map<String, Object> _templateAndMatrixParameterValues;

                        private NodeName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                            _client = client;
                            _uriBuilder = uriBuilder.clone();
                            _templateAndMatrixParameterValues = map;
                        }

                        /**
                         * Create new instance using existing Client instance, and a base URI and any parameters
                         * 
                         */
                        public NodeName(Client client, URI baseUri, String nodename) {
                            _client = client;
                            _uriBuilder = UriBuilder.fromUri(baseUri);
                            _uriBuilder = _uriBuilder.path("{nodeName}");
                            _templateAndMatrixParameterValues = new HashMap<String, Object>();
                            _templateAndMatrixParameterValues.put("nodeName", nodename);
                        }

                        /**
                         * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
                         * 
                         */
                        public NodeName(Client client, URI uri) {
                            _client = client;
                            StringBuilder template = new StringBuilder(BASE_URI.toString());
                            if (template.charAt((template.length()- 1))!= '/') {
                                template.append("/nffgs/{nffgName}/nodes/{nodeName}");
                            } else {
                                template.append("nffgs/{nffgName}/nodes/{nodeName}");
                            }
                            _uriBuilder = UriBuilder.fromPath(template.toString());
                            _templateAndMatrixParameterValues = new HashMap<String, Object>();
                            UriTemplate uriTemplate = new UriTemplate(template.toString());
                            HashMap<String, String> parameters = new HashMap<String, String>();
                            uriTemplate.match(uri.toString(), parameters);
                            _templateAndMatrixParameterValues.putAll(parameters);
                        }

                        /**
                         * Get nodeName
                         * 
                         */
                        public String getNodename() {
                            return ((String) _templateAndMatrixParameterValues.get("nodeName"));
                        }

                        /**
                         * Duplicate state and set nodeName
                         * 
                         */
                        public NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName setNodename(String nodename) {
                            Map<String, Object> copyMap;
                            copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                            UriBuilder copyUriBuilder = _uriBuilder.clone();
                            copyMap.put("nodeName", nodename);
                            return new NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName(_client, copyUriBuilder, copyMap);
                        }

                        public ClientResponse delete() {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            return response;
                        }

                        public<T >T delete(GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T delete(Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            ClientResponse response;
                            response = resourceBuilder.method("DELETE", ClientResponse.class);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RNode putXmlAsRNode(RNode input) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            resourceBuilder = resourceBuilder.type("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RNode.class);
                        }

                        public<T >T putXml(Object input, GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            resourceBuilder = resourceBuilder.type("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T putXml(Object input, Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            resourceBuilder = resourceBuilder.type("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RNode putTextXmlAsRNode(RNode input) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            resourceBuilder = resourceBuilder.type("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RNode.class);
                        }

                        public<T >T putTextXml(Object input, GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            resourceBuilder = resourceBuilder.type("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T putTextXml(Object input, Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            resourceBuilder = resourceBuilder.type("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("PUT", ClientResponse.class, input);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RNode getAsRNodeXml() {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RNode.class);
                        }

                        public<T >T getAsXml(GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T getAsXml(Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("application/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public RNode getAsRNodeTextXml() {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(RNode.class);
                        }

                        public<T >T getAsTextXml(GenericType<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (response.getStatus()>= 400) {
                                throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                            }
                            return response.getEntity(returnType);
                        }

                        public<T >T getAsTextXml(Class<T> returnType) {
                            UriBuilder localUriBuilder = _uriBuilder.clone();
                            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                            resourceBuilder = resourceBuilder.accept("text/xml");
                            ClientResponse response;
                            response = resourceBuilder.method("GET", ClientResponse.class);
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                            }
                            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                return response.getEntity(returnType);
                            } else {
                                return returnType.cast(response);
                            }
                        }

                        public NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.ReachableHosts reachableHosts() {
                            return new NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.ReachableHosts(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        }

                        public NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.Links links() {
                            return new NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.Links(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                        }

                        public static class Links {

                            private Client _client;
                            private UriBuilder _uriBuilder;
                            private Map<String, Object> _templateAndMatrixParameterValues;

                            private Links(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                                _client = client;
                                _uriBuilder = uriBuilder.clone();
                                _templateAndMatrixParameterValues = map;
                            }

                            /**
                             * Create new instance using existing Client instance, and a base URI and any parameters
                             * 
                             */
                            public Links(Client client, URI baseUri) {
                                _client = client;
                                _uriBuilder = UriBuilder.fromUri(baseUri);
                                _uriBuilder = _uriBuilder.path("links");
                                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                            }

                            public RLinks getAsRLinksXml() {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(RLinks.class);
                            }

                            public<T >T getAsXml(GenericType<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(returnType);
                            }

                            public<T >T getAsXml(Class<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                }
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    return response.getEntity(returnType);
                                } else {
                                    return returnType.cast(response);
                                }
                            }

                            public RLinks getAsRLinksTextXml() {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(RLinks.class);
                            }

                            public<T >T getAsTextXml(GenericType<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(returnType);
                            }

                            public<T >T getAsTextXml(Class<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                }
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    return response.getEntity(returnType);
                                } else {
                                    return returnType.cast(response);
                                }
                            }

                            public RLink postXmlAsRLink(RLink input) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                resourceBuilder = resourceBuilder.type("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("POST", ClientResponse.class, input);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(RLink.class);
                            }

                            public<T >T postXml(Object input, GenericType<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                resourceBuilder = resourceBuilder.type("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("POST", ClientResponse.class, input);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(returnType);
                            }

                            public<T >T postXml(Object input, Class<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                resourceBuilder = resourceBuilder.type("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("POST", ClientResponse.class, input);
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                }
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    return response.getEntity(returnType);
                                } else {
                                    return returnType.cast(response);
                                }
                            }

                            public RLink postTextXmlAsRLink(RLink input) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                resourceBuilder = resourceBuilder.type("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("POST", ClientResponse.class, input);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(RLink.class);
                            }

                            public<T >T postTextXml(Object input, GenericType<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                resourceBuilder = resourceBuilder.type("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("POST", ClientResponse.class, input);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(returnType);
                            }

                            public<T >T postTextXml(Object input, Class<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                resourceBuilder = resourceBuilder.type("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("POST", ClientResponse.class, input);
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                }
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    return response.getEntity(returnType);
                                } else {
                                    return returnType.cast(response);
                                }
                            }

                            public NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.Links.LinkName linkName(String linkname) {
                                return new NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.Links.LinkName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), linkname);
                            }

                            public static class LinkName {

                                private Client _client;
                                private UriBuilder _uriBuilder;
                                private Map<String, Object> _templateAndMatrixParameterValues;

                                private LinkName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                                    _client = client;
                                    _uriBuilder = uriBuilder.clone();
                                    _templateAndMatrixParameterValues = map;
                                }

                                /**
                                 * Create new instance using existing Client instance, and a base URI and any parameters
                                 * 
                                 */
                                public LinkName(Client client, URI baseUri, String linkname) {
                                    _client = client;
                                    _uriBuilder = UriBuilder.fromUri(baseUri);
                                    _uriBuilder = _uriBuilder.path("{linkName}");
                                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                                    _templateAndMatrixParameterValues.put("linkName", linkname);
                                }

                                /**
                                 * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
                                 * 
                                 */
                                public LinkName(Client client, URI uri) {
                                    _client = client;
                                    StringBuilder template = new StringBuilder(BASE_URI.toString());
                                    if (template.charAt((template.length()- 1))!= '/') {
                                        template.append("/nffgs/{nffgName}/nodes/{nodeName}/links/{linkName}");
                                    } else {
                                        template.append("nffgs/{nffgName}/nodes/{nodeName}/links/{linkName}");
                                    }
                                    _uriBuilder = UriBuilder.fromPath(template.toString());
                                    _templateAndMatrixParameterValues = new HashMap<String, Object>();
                                    UriTemplate uriTemplate = new UriTemplate(template.toString());
                                    HashMap<String, String> parameters = new HashMap<String, String>();
                                    uriTemplate.match(uri.toString(), parameters);
                                    _templateAndMatrixParameterValues.putAll(parameters);
                                }

                                /**
                                 * Get linkName
                                 * 
                                 */
                                public String getLinkname() {
                                    return ((String) _templateAndMatrixParameterValues.get("linkName"));
                                }

                                /**
                                 * Duplicate state and set linkName
                                 * 
                                 */
                                public NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.Links.LinkName setLinkname(String linkname) {
                                    Map<String, Object> copyMap;
                                    copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                                    UriBuilder copyUriBuilder = _uriBuilder.clone();
                                    copyMap.put("linkName", linkname);
                                    return new NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.Links.LinkName(_client, copyUriBuilder, copyMap);
                                }

                                public RLink getAsRLinkXml() {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("GET", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(RLink.class);
                                }

                                public<T >T getAsXml(GenericType<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("GET", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(returnType);
                                }

                                public<T >T getAsXml(Class<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("GET", ClientResponse.class);
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        if (response.getStatus()>= 400) {
                                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                        }
                                    }
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        return response.getEntity(returnType);
                                    } else {
                                        return returnType.cast(response);
                                    }
                                }

                                public RLink getAsRLinkTextXml() {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("GET", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(RLink.class);
                                }

                                public<T >T getAsTextXml(GenericType<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("GET", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(returnType);
                                }

                                public<T >T getAsTextXml(Class<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("GET", ClientResponse.class);
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        if (response.getStatus()>= 400) {
                                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                        }
                                    }
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        return response.getEntity(returnType);
                                    } else {
                                        return returnType.cast(response);
                                    }
                                }

                                public RLink putXmlAsRLink(RLink input) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    resourceBuilder = resourceBuilder.type("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("PUT", ClientResponse.class, input);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(RLink.class);
                                }

                                public<T >T putXml(Object input, GenericType<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    resourceBuilder = resourceBuilder.type("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("PUT", ClientResponse.class, input);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(returnType);
                                }

                                public<T >T putXml(Object input, Class<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    resourceBuilder = resourceBuilder.type("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("PUT", ClientResponse.class, input);
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        if (response.getStatus()>= 400) {
                                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                        }
                                    }
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        return response.getEntity(returnType);
                                    } else {
                                        return returnType.cast(response);
                                    }
                                }

                                public RLink putTextXmlAsRLink(RLink input) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    resourceBuilder = resourceBuilder.type("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("PUT", ClientResponse.class, input);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(RLink.class);
                                }

                                public<T >T putTextXml(Object input, GenericType<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    resourceBuilder = resourceBuilder.type("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("PUT", ClientResponse.class, input);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(returnType);
                                }

                                public<T >T putTextXml(Object input, Class<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    resourceBuilder = resourceBuilder.type("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("PUT", ClientResponse.class, input);
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        if (response.getStatus()>= 400) {
                                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                        }
                                    }
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        return response.getEntity(returnType);
                                    } else {
                                        return returnType.cast(response);
                                    }
                                }

                                public RLink deleteAsRLinkXml() {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(RLink.class);
                                }

                                public<T >T deleteAsXml(GenericType<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(returnType);
                                }

                                public<T >T deleteAsXml(Class<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("application/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        if (response.getStatus()>= 400) {
                                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                        }
                                    }
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        return response.getEntity(returnType);
                                    } else {
                                        return returnType.cast(response);
                                    }
                                }

                                public RLink deleteAsRLinkTextXml() {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(RLink.class);
                                }

                                public<T >T deleteAsTextXml(GenericType<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                    return response.getEntity(returnType);
                                }

                                public<T >T deleteAsTextXml(Class<T> returnType) {
                                    UriBuilder localUriBuilder = _uriBuilder.clone();
                                    WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                    com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                    resourceBuilder = resourceBuilder.accept("text/xml");
                                    ClientResponse response;
                                    response = resourceBuilder.method("DELETE", ClientResponse.class);
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        if (response.getStatus()>= 400) {
                                            throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                        }
                                    }
                                    if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                        return response.getEntity(returnType);
                                    } else {
                                        return returnType.cast(response);
                                    }
                                }

                            }

                        }

                        public static class ReachableHosts {

                            private Client _client;
                            private UriBuilder _uriBuilder;
                            private Map<String, Object> _templateAndMatrixParameterValues;

                            private ReachableHosts(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                                _client = client;
                                _uriBuilder = uriBuilder.clone();
                                _templateAndMatrixParameterValues = map;
                            }

                            /**
                             * Create new instance using existing Client instance, and a base URI and any parameters
                             * 
                             */
                            public ReachableHosts(Client client, URI baseUri) {
                                _client = client;
                                _uriBuilder = UriBuilder.fromUri(baseUri);
                                _uriBuilder = _uriBuilder.path("reachableHosts");
                                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                            }

                            public RHosts getAsRHostsXml() {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(RHosts.class);
                            }

                            public<T >T getAsXml(GenericType<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(returnType);
                            }

                            public<T >T getAsXml(Class<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("application/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                }
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    return response.getEntity(returnType);
                                } else {
                                    return returnType.cast(response);
                                }
                            }

                            public RHosts getAsRHostsTextXml() {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(RHosts.class);
                            }

                            public<T >T getAsTextXml(GenericType<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (response.getStatus()>= 400) {
                                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                }
                                return response.getEntity(returnType);
                            }

                            public<T >T getAsTextXml(Class<T> returnType) {
                                UriBuilder localUriBuilder = _uriBuilder.clone();
                                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                                resourceBuilder = resourceBuilder.accept("text/xml");
                                ClientResponse response;
                                response = resourceBuilder.method("GET", ClientResponse.class);
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    if (response.getStatus()>= 400) {
                                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                                    }
                                }
                                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                                    return response.getEntity(returnType);
                                } else {
                                    return returnType.cast(response);
                                }
                            }

                        }

                    }

                }

            }

        }

        public static class SwaggerJson {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private SwaggerJson(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public SwaggerJson(Client client, URI baseUri) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("/swagger.json");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class SwaggerYaml {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private SwaggerYaml(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public SwaggerYaml(Client client, URI baseUri) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("/swagger.yaml");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            public<T >T getAsYaml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/yaml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsYaml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/yaml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new NfvDeployer.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }


    /**
     * Workaround for JAX_RS_SPEC-312
     * 
     */
    private static class WebApplicationExceptionMessage
        extends WebApplicationException
    {


        private WebApplicationExceptionMessage(Response response) {
            super(response);
        }

        /**
         * Workaround for JAX_RS_SPEC-312
         * 
         */
        public String getMessage() {
            Response response = getResponse();
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());
            if (status!= null) {
                return (response.getStatus()+(" "+ status.getReasonPhrase()));
            } else {
                return Integer.toString(response.getStatus());
            }
        }

        public String toString() {
            String s = "javax.ws.rs.WebApplicationException";
            String message = getLocalizedMessage();
            return (s +(": "+ message));
        }

    }

}
