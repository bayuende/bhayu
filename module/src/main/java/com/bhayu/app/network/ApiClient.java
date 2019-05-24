package com.bhayu.app.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.bhayu.app.helper.SecretKeyHelper;
import com.bhayu.module.BuildConfig;
import com.bhayu.module.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mzennis on 12/13/16.
 */
public class ApiClient {

	private static Retrofit retrofit = null;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static SSLContext getSSLConfig(Context context) throws CertificateException, IOException,
            KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        Certificate ca;
        try (InputStream cert = context.getResources().openRawResource(R.raw.cert)) {
            ca = cf.generateCertificate(cert);
        }

        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore   = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        return sslContext;
    }

	public static Retrofit getClient(Context context) {
		return retrofit(okhttpBuilder(context), ApiConfig.BASE_URL);
	}

    public static OkHttpClient.Builder okhttpBuilder(Context context) {
//        CertificatePinner certificatePinner = new CertificatePinner.Builder()
//                .add("beta.smartcom.id", "sha256/3+buBfi9K+OhPLZjRhPtL5LnAgNvG1442uY3ShsPy10=")
//                .build();

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        dispatcher.setMaxRequestsPerHost(1);

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);
        //okhttpBuilder.certificatePinner(certificatePinner);
        okhttpBuilder.cache(cache);
        okhttpBuilder.dispatcher(dispatcher);

        try {
            okhttpBuilder.sslSocketFactory(getSSLConfig(context).getSocketFactory());
        }catch (Exception e){}

        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder ongoing = chain.request().newBuilder();
                ongoing.addHeader("Accept", "application/json;versions=1");
                ongoing.addHeader("Content-Type", "application/x-www-form-urlencoded");
                ongoing.addHeader("Api-Key", SecretKeyHelper.defaultKey());
                return chain.proceed(ongoing.build());
            }
        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpBuilder.addInterceptor(interceptor);
        }

        return okhttpBuilder;
    }


//	public static OkHttpClient.Builder okhttpBuilder(Context context) {
//		OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
//		okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
//		okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
//		okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);
//
//		int cacheSize = 10 * 1024 * 1024; // 10 MiB
//		Cache cache = new Cache(context.getCacheDir(), cacheSize);
//		okhttpBuilder.cache(cache);
//
//		if (BuildConfig.DEBUG) {
//			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//			okhttpBuilder.addInterceptor(interceptor);
//		}
//		okhttpBuilder.addInterceptor(new Interceptor() {
//			@Override
//			public Response intercept(Chain chain) throws IOException {
//				Request.Builder ongoing = chain.request().newBuilder();
//				ongoing.addHeader("Accept", "application/json;versions=1");
//				ongoing.addHeader("Content-Type", "application/x-www-form-urlencoded");
//				ongoing.addHeader("Api-Key", SecretKeyHelper.defaultKey());
//				return chain.proceed(ongoing.build());
//			}
//		});
//		return okhttpBuilder;
//	}

	public static Retrofit retrofit(OkHttpClient.Builder okhttpBuilder, String baseUrl) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLenient();
		Gson gson = gsonBuilder.create();
		return retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(okhttpBuilder.build())
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
	}

	public static Retrofit retrofit(OkHttpClient.Builder okhttpBuilder, String baseUrl, String add) {
		return retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(okhttpBuilder.build())
				.build();
	}

	public static RequestBody convert(Map<String, Object> map){
		return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
				(new JSONObject(map)).toString());
	}
}
