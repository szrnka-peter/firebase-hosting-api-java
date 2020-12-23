package io.github.szrnkapeter.firebase.hosting.builder;

import java.io.InputStream;

import io.github.szrnkapeter.firebase.hosting.config.FirebaseHostingApiConfig;
import io.github.szrnkapeter.firebase.hosting.type.SerializerType;

/**
 * Builder class to create {@link FirebaseHostingApiConfig} instances.
 * 
 * @author Peter Szrnka
 * @since 0.2
 */
public class FirebaseHostingApiConfigBuilder {
	
	private FirebaseHostingApiConfig config;
	
	private FirebaseHostingApiConfigBuilder() {
		config = new FirebaseHostingApiConfig();
	}
	
	public static FirebaseHostingApiConfigBuilder builder() {
		return new FirebaseHostingApiConfigBuilder();
	}
	
	public FirebaseHostingApiConfigBuilder withConfigStream(InputStream configStream) {
		config.setConfigStream(configStream);
		return this;
	}
	
	public FirebaseHostingApiConfigBuilder withSiteName(String siteName) {
		config.setSiteName(siteName);
		return this;
	}
	
	public FirebaseHostingApiConfigBuilder withSerializer(SerializerType serializer) {
		config.setSerializer(serializer);
		return this;
	}
	
	public FirebaseHostingApiConfigBuilder withDefaultConnectionTimeout(int defaultConnectionTimeout) {
		config.setDefaultConnectionTimeout(defaultConnectionTimeout);
		return this;
	}
	
	public FirebaseHostingApiConfigBuilder withDefaultReadTimeout(int defaultReadTimeout) {
		config.setDefaultReadTimeout(defaultReadTimeout);
		return this;
	}

	public FirebaseHostingApiConfig build() {
		return config;
	}
}