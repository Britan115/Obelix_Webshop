package ch.bbw.obelix.quarry.api;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface MenhirApi {
    @GetExchange("/api/menhirs")
    List<MenhirDto> getAllMenhirs();
}
