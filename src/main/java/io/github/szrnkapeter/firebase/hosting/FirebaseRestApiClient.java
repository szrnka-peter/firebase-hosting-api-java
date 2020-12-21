package io.github.szrnkapeter.firebase.hosting;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.szrnkapeter.firebase.hosting.config.FirebaseRestApiConfig;
import io.github.szrnkapeter.firebase.hosting.model.GetReleasesResponse;
import io.github.szrnkapeter.firebase.hosting.model.GetVersionFilesResponse;
import io.github.szrnkapeter.firebase.hosting.util.ConnectionUtils;
import io.github.szrnkapeter.firebase.hosting.util.GoogleCredentialUtils;

/**
 * Firebase REST API client for Java
 * 
 * @author Peter Szrnka
 * @since 0.1
 */
public class FirebaseRestApiClient {

	private static final String FILES = "/files";
	private static final String VERSIONS = "/versions/";
	private static final String SITES = "sites/";

	private FirebaseRestApiConfig config;
	private String accessToken;

	public FirebaseRestApiClient(FirebaseRestApiConfig firebaseRestApiConfig) throws IOException {
		if(firebaseRestApiConfig == null) {
			throw new IllegalArgumentException("FirebaseRestApiConfig field is mandatory!");
		}

		config = firebaseRestApiConfig;
		accessToken = GoogleCredentialUtils.getAccessToken(config);
	}

	/**
	 * Returns with the list of releases
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public GetReleasesResponse getReleases() throws Exception {
		return ConnectionUtils.openHTTPGetConnection(config, GetReleasesResponse.class, accessToken,
				SITES + config.getSiteName() + "/releases");
	}

	/**
	 * Returns with all files of a given version
	 * 
	 * @param version
	 * @return
	 * @throws Exception
	 */
	public GetVersionFilesResponse getVersionFiles(String version) throws Exception {
		if(version == null || version.isEmpty()) {
			throw new IllegalArgumentException("Version field is mandatory!");
		}

		String url = SITES + config.getSiteName() + VERSIONS + version + FILES;

		Pattern p = Pattern.compile(SITES + config.getSiteName() + VERSIONS + ".*");
		Matcher m = p.matcher(version);

		if (m.matches()) {
			url = version + FILES;
		}

		return ConnectionUtils.openHTTPGetConnection(config, GetVersionFilesResponse.class, accessToken, url);
	}
}