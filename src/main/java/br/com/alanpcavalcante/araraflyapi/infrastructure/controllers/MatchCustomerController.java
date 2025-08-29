package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;

import br.com.alanpcavalcante.araraflyapi.application.usecases.match.ConfirmMatch;
import br.com.alanpcavalcante.araraflyapi.application.usecases.match.GetListMatchCustomer;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.MatchResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.MatchMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.security.UserLogged;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer/projects/matches")
@RequiredArgsConstructor
@Tag(name = "Match de projetos - Clientes")
public class MatchCustomerController {

    private final UserLogged logged;
    private final MatchMapper matchMapper;

    private final GetListMatchCustomer getListMatchCustomer;
    private final ConfirmMatch confirmMatch;


    @GetMapping
    public ResponseEntity<List<MatchResponse>> listMatches() {
        List<Match> matches = getListMatchCustomer.get(logged.load());
        List<MatchResponse> responses = matches.stream().map(matchMapper::domainToResponse).toList();
        return ResponseEntity.ok(responses);
    }


    @PatchMapping("{idMatch}")
    public ResponseEntity<MatchResponse> confirmMatch(@PathVariable("idMatch") String id) {
        Match save = confirmMatch.confirmMatch(logged.load(), UUID.fromString(id));
        return ResponseEntity.ok(matchMapper.domainToResponse(save));
    }

}
