package com.autohome.frostmourne.monitor.tool;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

    private Pattern proxyPattern;

    public void setProxy(Proxy proxy, String proxyRule){
        if(!proxyRule.equals("") && proxyRule != null) {
            proxyRule = proxyRule.replace(".", "\\.")
                    .replace("*", "\\w+")
                    .replace(",", "|");
            this.proxyPattern = Pattern.compile(proxyRule);
        }
        super.setProxy(proxy);
    }

    @Override
    protected HttpURLConnection openConnection(URL url, @Nullable Proxy proxy) throws IOException {
        if(proxyPattern != null) {
            Matcher matcher = proxyPattern.matcher(url.getHost());
            if (!matcher.find()) {
                proxy = null;
            }
        }
        return super.openConnection(url, proxy);
    }
}