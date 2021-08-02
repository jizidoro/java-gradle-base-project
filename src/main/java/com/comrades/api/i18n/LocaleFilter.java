package com.comrades.api.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;

@Component
public class LocaleFilter implements WebFilter {

	@Autowired
	private Messages messages;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		List<String> list = exchange.getRequest().getHeaders().get(HttpHeaders.ACCEPT_LANGUAGE);

		if ( list == null || list.isEmpty())
			this.messages.setLocale(Locale.forLanguageTag("pt-BR"));
		else
			this.messages.setLocale(Locale.forLanguageTag(list.get(0)));

		return chain.filter(exchange);
	}

}
