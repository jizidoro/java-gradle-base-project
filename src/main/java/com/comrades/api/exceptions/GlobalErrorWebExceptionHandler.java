package com.comrades.api.exceptions;

import com.comrades.api.i18n.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

	private static final String MESSAGE_ERROR = "error";
	private static final String MESSAGE_FIELD = "message";

	@Autowired
	private Messages messages;

	public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
										  ApplicationContext applicationContext, ServerCodecConfigurer configurer) {

		super(errorAttributes, resourceProperties, applicationContext);
		this.setMessageWriters(configurer.getWriters());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {

		Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

		Throwable th = getError(request);

		log.error(this.messages.get("exception.message.error"), th.getMessage());

		errorPropertiesMap.remove(MESSAGE_ERROR);
		errorPropertiesMap.put("status", HttpStatus.BAD_REQUEST.value());
		errorPropertiesMap.put("method", request.methodName());

		if (errorPropertiesMap.containsKey(MESSAGE_ERROR) && (th instanceof WebExchangeBindException)) {
			@SuppressWarnings("unchecked")
			List<ObjectError> errors = (List<ObjectError>) errorPropertiesMap.get(MESSAGE_ERROR);
			errorPropertiesMap.put(MESSAGE_ERROR, errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
			errorPropertiesMap.put(MESSAGE_FIELD, this.messages.get("exception.message.webexchangebindexception"));

			log.error(this.messages.get("exception.message.error"), errors.stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).reduce("", String::concat));
		} else if (th instanceof DataIntegrityViolationException) {
			errorPropertiesMap.put(MESSAGE_FIELD, this.messages.get("exception.message.dataintegrityviolationexception"));
			log.error(this.messages.get("exception.message.dataintegrityviolationexception"));
		} else {
			errorPropertiesMap.put(MESSAGE_FIELD, th.getMessage());
		}

		return ServerResponse.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(errorPropertiesMap));
	}

}