package ch.bbw.obelix.quarry.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.UUID;

/**
 * API für Menhir-Operationen im Steinbruch von Obelix
 */
public interface MenhirApi {
    @GetExchange("/api/menhirs")
    List<MenhirDto> getAllMenhirs();

    @GetExchange("/api/menhirs/{menhirId}")
    MenhirDto getMenhirById(@PathVariable("menhirId") UUID menhirId);

    @DeleteExchange("/api/quarry/{menhirId}")
    void deleteById(@PathVariable("menhirId") UUID menhirId);
}
