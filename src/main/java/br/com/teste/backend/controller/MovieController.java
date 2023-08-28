package br.com.teste.backend.controller;

import br.com.teste.backend.dto.ResponseDto;
import br.com.teste.backend.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {
	
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Intervalo de prêmios",
            description = "Retorna informações sobre o produtor com o maior intervalo entre dois prêmios consecutivos e o produtor que obteve dois prêmios mais rapidamente.")
    @ApiResponse(responseCode = "200",
            description = "Retorna a lista de informações dos produtores com seus respectivos intervalos.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))})
    @GetMapping(value = "/prizes")
    public ResponseEntity<ResponseDto> calculateIntervals() {
        log.info("Starting calculateIntervals");
        ResponseDto responseDto = movieService.calculateIntervals();
        log.info("Finishing calculateIntervals");
        return ResponseEntity.ok(responseDto);
    }

}
