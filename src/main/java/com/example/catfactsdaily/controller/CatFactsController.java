package com.example.catfactsdaily.controller;

import com.example.catfactsdaily.dto.CatFactDTO;
import com.example.catfactsdaily.entity.CatFact;
import com.example.catfactsdaily.response.CatFactResponse;
import com.example.catfactsdaily.service.CatFactsService;
import com.example.catfactsdaily.service.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/cat-facts")
@SecurityRequirement(name = "bearerAuth")
public class CatFactsController {

    private static final Logger logger = LoggerFactory.getLogger(CatFactsController.class);

    private final CatFactsService catFactsService;

    private final JwtService jwtService;

    @Autowired
    public CatFactsController(CatFactsService catFactsService, JwtService jwtService) {
        this.catFactsService = catFactsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/add")
    public ResponseEntity<CatFactResponse> create(@RequestHeader("Authorization") String token, @RequestBody CatFactDTO createCatFactDTO, Principal principal) {
        String cleanedToken = token.replace("Bearer ", "");
        Integer userIdFromToken = jwtService.extractUserIdFromPrincipal(principal);

        if (!jwtService.extractUserId(cleanedToken).equals(userIdFromToken)) {
            CatFactResponse catFactResponse = new CatFactResponse();
            logger.warn("Adding new CatFact for userId: {} is unauthorized", userIdFromToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(catFactResponse);
        }

        logger.info("Adding new CatFact for userId: {}", userIdFromToken);
        CatFact addedCatFact = catFactsService.add(createCatFactDTO, userIdFromToken);

        return ResponseEntity.ok(catFactsService.convertCatFactToCatFactResponse(addedCatFact));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CatFactResponse>> readAll(@RequestHeader("Authorization") String token, Principal principal) {
        String cleanedToken = token.replace("Bearer ", "");
        Integer userIdFromToken = jwtService.extractUserIdFromPrincipal(principal);

        if (!jwtService.extractUserId(cleanedToken).equals(userIdFromToken)) {
            List<CatFactResponse> catFactResponseList = new ArrayList<>();
            logger.warn("Fetching CatFacts for userId: {} is unauthorized", userIdFromToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(catFactResponseList);
        }

        logger.info("Fetching CatFacts for userId: {}", userIdFromToken);
        List<CatFact> allUserCatFacts = catFactsService.getAll(userIdFromToken);
        List<CatFactResponse> catFactResponses = new ArrayList<>();
        for (CatFact catFact : allUserCatFacts) {
            if(catFact.getIsActive()) {
                catFactResponses.add(catFactsService.convertCatFactToCatFactResponse(catFact));
            }
        }

        return ResponseEntity.ok(catFactResponses);
    }

    @PatchMapping("/edit/{cat_fact_id}")
    public ResponseEntity<CatFactResponse> update(@RequestHeader("Authorization") String token, @PathVariable UUID cat_fact_id, @RequestBody CatFactDTO updateCatFactDTO, Principal principal) {
        String cleanedToken = token.replace("Bearer ", "");
        Integer userIdFromToken = jwtService.extractUserIdFromPrincipal(principal);

        if (!jwtService.extractUserId(cleanedToken).equals(userIdFromToken)) {
            CatFactResponse catFactResponse = new CatFactResponse();
            logger.warn("Editing CatFact {} for userId: {} is unauthorized", cat_fact_id, userIdFromToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(catFactResponse);
        }

        logger.info("Editing CatFact {} for userId: {}", cat_fact_id, userIdFromToken);
        CatFact updatedCatFact = catFactsService.editCatFact(cat_fact_id, updateCatFactDTO, userIdFromToken);

        if(updatedCatFact == null) {
            logger.warn("CatFact {} not found for userId: {} to edit", cat_fact_id, userIdFromToken);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(catFactsService.convertCatFactToCatFactResponse(updatedCatFact));
    }

    @PatchMapping("/delete/{cat_fact_id}")
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String token, @PathVariable UUID cat_fact_id, Principal principal) {
        String cleanedToken = token.replace("Bearer ", "");
        Integer userIdFromToken = jwtService.extractUserIdFromPrincipal(principal);

        if (!jwtService.extractUserId(cleanedToken).equals(userIdFromToken)) {
            logger.warn("Deleting CatFact {} for userId: {} is unauthorized", cat_fact_id, userIdFromToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized delete attempt.");
        }

        logger.info("Deleting CatFact {} for userId: {}", cat_fact_id, userIdFromToken);
        CatFact deletedCatFact = catFactsService.deleteCatFact(cat_fact_id, userIdFromToken);

        String deleteResponse;
        if (deletedCatFact == null) {
            logger.warn("CatFact {} not found for userId: {} to delete", cat_fact_id, userIdFromToken);
            return ResponseEntity.notFound().build();
        }
        else if(deletedCatFact.getIsActive()) {
            deleteResponse = "CatFact with id " + deletedCatFact.getId() + " not deleted error";
        }
        else {
            deleteResponse = "CatFact with id " + deletedCatFact.getId() + " deleted successfully";
        }

        return ResponseEntity.ok(deleteResponse);
    }
}
