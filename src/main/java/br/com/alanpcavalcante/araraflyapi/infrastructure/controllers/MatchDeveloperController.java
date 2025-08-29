package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;


import br.com.alanpcavalcante.araraflyapi.application.usecases.match.ConfirmMatch;
import br.com.alanpcavalcante.araraflyapi.application.usecases.match.CreateMatchDeveloper;
import br.com.alanpcavalcante.araraflyapi.application.usecases.match.DeleteMatchDeveloper;
import br.com.alanpcavalcante.araraflyapi.application.usecases.match.GetListMatchDeveloper;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.GetProjectDeveloper;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.MatchResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.MatchMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.security.UserLogged;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/developer/projects/matches")
@Tag(name = "Match de projetos - Desenvolvedores")
public class MatchDeveloperController {

    private final UserLogged logged;
    private final MatchMapper matchMapper;

    private final GetProjectDeveloper getProjectDeveloper;
    private final CreateMatchDeveloper createMatchDeveloper;
    private final GetListMatchDeveloper getListMatchDeveloper;
    private final DeleteMatchDeveloper deleteMatchDeveloper;
    private final ConfirmMatch confirmMatch;


    @GetMapping
    public ResponseEntity<List<MatchResponse>> listMatches() {
        List<Match> matches = getListMatchDeveloper.get(logged.load());
        List<MatchResponse> responses = matches.stream().map(matchMapper::domainToResponse).toList();

        if (responses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(responses);
    }


    @PostMapping
    public ResponseEntity<MatchResponse> createMatch(@RequestParam String idProject) {
        Match matchCreated = createMatchDeveloper
                .createMatch(
                    logged.load(),
                    getProjectDeveloper.get(logged.load(), UUID.fromString(idProject))
                    .orElseThrow(() -> new RuntimeException("Nao encontrado")
                ));

        return ResponseEntity.status(HttpStatus.CREATED).body(matchMapper.domainToResponse(matchCreated));
    }


    @PatchMapping("{idMatch}")
    public ResponseEntity<MatchResponse> confirmMatch(@PathVariable("idMatch") String id) {
        Match save = confirmMatch.confirmMatch(logged.load(), UUID.fromString(id));
        return ResponseEntity.ok(matchMapper.domainToResponse(save));
    }


    @DeleteMapping("{idMatch}")
    public ResponseEntity<Void> deleteMatch(@PathVariable("idMatch") String id) {
        deleteMatchDeveloper.delete(logged.load(), UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
