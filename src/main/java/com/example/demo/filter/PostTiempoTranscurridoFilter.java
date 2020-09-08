package com.example.demo.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter{

	 private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
	
	@Override
	public boolean shouldFilter() {
		//se ejecutará siempre si es true
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//Lógica del filtro
		//Recuperar el tiempo actual y setearlo en la request
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		Long preCurrentTime = (Long) request.getAttribute("preCurrentTime");
		Long postCurrentTime = System.currentTimeMillis();

		Long tiempoTranscurridoMilis = postCurrentTime - preCurrentTime;
		Double tiempoTranscurrido = tiempoTranscurridoMilis / 1000.0;
		
		log.info(String.format("Tiempo transcurrido en milisegundos %s", tiempoTranscurridoMilis));
		log.info(String.format("Tiempo transcurrido en segundos %s", tiempoTranscurrido));
		
				
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
