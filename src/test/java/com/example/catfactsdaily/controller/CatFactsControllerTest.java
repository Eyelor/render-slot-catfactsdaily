package com.example.catfactsdaily.controller;

import com.example.catfactsdaily.dto.CatFactDTO;
import com.example.catfactsdaily.entity.CatFact;
import com.example.catfactsdaily.response.CatFactResponse;
import com.example.catfactsdaily.service.CatFactsService;
import com.example.catfactsdaily.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CatFactsControllerTest {

    @Mock
    private CatFactsService catFactsService;

    @Mock
    private JwtService jwtService;

    @Mock
    private Principal principal;

    @InjectMocks
    private CatFactsController catFactsController;

    private static final String BEARER_TOKEN = "Bearer fake_jwt_token";
    private static final UUID CAT_FACT_ID = UUID.randomUUID();
    private static final int USER_ID = 123;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jwtService.extractUserIdFromPrincipal(principal)).thenReturn(USER_ID);
        when(jwtService.extractUserId(anyString())).thenReturn(USER_ID);
    }

    @Test
    void testCreateCatFact() {
        CatFactDTO catFactDTO = new CatFactDTO();
        CatFact mockCatFact = new CatFact();
        CatFactResponse mockResponse = new CatFactResponse();
        when(catFactsService.add(any(CatFactDTO.class), eq(USER_ID))).thenReturn(mockCatFact);
        when(catFactsService.convertCatFactToCatFactResponse(mockCatFact)).thenReturn(mockResponse);

        ResponseEntity<CatFactResponse> response = catFactsController.create(BEARER_TOKEN, catFactDTO, principal);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(catFactsService, times(1)).add(any(CatFactDTO.class), eq(USER_ID));
    }

    @Test
    void testCreateCatFact_Unauthorized() {
        when(jwtService.extractUserId(anyString())).thenReturn(999);

        ResponseEntity<CatFactResponse> response = catFactsController.create(BEARER_TOKEN, new CatFactDTO(), principal);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(new CatFactResponse(), response.getBody());
    }

    @Test
    void testReadAllCatFacts() {
        CatFact mockCatFact = new CatFact();
        CatFactResponse mockResponse = new CatFactResponse();
        mockCatFact.setIsActive(true);
        when(catFactsService.getAll(USER_ID)).thenReturn(List.of(mockCatFact));
        when(catFactsService.convertCatFactToCatFactResponse(mockCatFact)).thenReturn(mockResponse);

        ResponseEntity<List<CatFactResponse>> response = catFactsController.readAll(BEARER_TOKEN, principal);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(List.of(mockResponse), response.getBody());
        verify(catFactsService, times(1)).getAll(USER_ID);
    }

    @Test
    void testReadAllCatFacts_Unauthorized() {
        when(jwtService.extractUserId(anyString())).thenReturn(999);

        ResponseEntity<List<CatFactResponse>> response = catFactsController.readAll(BEARER_TOKEN, principal);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(new ArrayList<CatFactResponse>(), response.getBody());
    }

    @Test
    void testUpdateCatFact() {
        CatFactDTO catFactDTO = new CatFactDTO();
        CatFact mockCatFact = new CatFact();
        CatFactResponse mockResponse = new CatFactResponse();
        when(catFactsService.editCatFact(eq(CAT_FACT_ID), any(CatFactDTO.class), eq(USER_ID))).thenReturn(mockCatFact);
        when(catFactsService.convertCatFactToCatFactResponse(mockCatFact)).thenReturn(mockResponse);

        ResponseEntity<CatFactResponse> response = catFactsController.update(BEARER_TOKEN, CAT_FACT_ID, catFactDTO, principal);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(catFactsService, times(1)).editCatFact(eq(CAT_FACT_ID), any(CatFactDTO.class), eq(USER_ID));
    }

    @Test
    void testUpdateCatFact_Unauthorized() {
        when(jwtService.extractUserId(anyString())).thenReturn(999);

        ResponseEntity<CatFactResponse> response = catFactsController.update(BEARER_TOKEN, CAT_FACT_ID, new CatFactDTO(), principal);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(new CatFactResponse(), response.getBody());
    }

    @Test
    void testUpdateCatFact_NotFound() {
        when(catFactsService.editCatFact(eq(CAT_FACT_ID), any(CatFactDTO.class), eq(USER_ID))).thenReturn(null);

        ResponseEntity<CatFactResponse> response = catFactsController.update(BEARER_TOKEN, CAT_FACT_ID, new CatFactDTO(), principal);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteCatFact() {
        CatFact mockCatFact = new CatFact();
        mockCatFact.setId(CAT_FACT_ID);
        mockCatFact.setIsActive(false);
        when(catFactsService.deleteCatFact(CAT_FACT_ID, USER_ID)).thenReturn(mockCatFact);

        ResponseEntity<String> response = catFactsController.delete(BEARER_TOKEN, CAT_FACT_ID, principal);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("deleted successfully"));
        verify(catFactsService, times(1)).deleteCatFact(CAT_FACT_ID, USER_ID);
    }

    @Test
    void testDeleteCatFact_Unauthorized() {
        when(jwtService.extractUserId(anyString())).thenReturn(999);

        ResponseEntity<String> response = catFactsController.delete(BEARER_TOKEN, CAT_FACT_ID, principal);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized delete attempt.", response.getBody());
    }

    @Test
    void testDeleteCatFact_NotFound() {
        when(catFactsService.deleteCatFact(CAT_FACT_ID, USER_ID)).thenReturn(null);

        ResponseEntity<String> response = catFactsController.delete(BEARER_TOKEN, CAT_FACT_ID, principal);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
