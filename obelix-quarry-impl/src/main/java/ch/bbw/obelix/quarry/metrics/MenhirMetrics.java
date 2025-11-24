package ch.bbw.obelix.quarry.metrics;

import ch.bbw.obelix.quarry.repository.MenhirRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenhirMetrics {

	private final MenhirRepository menhirRepository;
	private final MeterRegistry meterRegistry;

	@PostConstruct
	public void registerMetrics() {
		Gauge.builder("menhirs.available.count", menhirRepository, MenhirRepository::count)
				.description("Anzahl der aktuell verfügbaren Hinkelsteine (Menhirs)")
				.register(meterRegistry);
	}
}
