package com.example.catfactsdaily.service;

import com.example.catfactsdaily.dto.CatFactDTO;
import com.example.catfactsdaily.entity.CatFact;
import com.example.catfactsdaily.repository.CatFactRepository;
import com.example.catfactsdaily.response.CatFactResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CatFactsService {

    private static final Logger logger = LoggerFactory.getLogger(CatFactsService.class);

    private final CatFactRepository catFactRepository;

    @Autowired
    public CatFactsService(CatFactRepository catFactRepository) {
        this.catFactRepository = catFactRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CatFact add(CatFactDTO catFactDTO, Integer userIdFromToken) {
        CatFact catFact = new CatFact();
        catFact.setUserID(userIdFromToken);
        catFact.setFact(catFactDTO.getFact());
        CatFact savedCatFact = catFactRepository.save(catFact);
        logger.info("Added CatFact for userId {}: {}", userIdFromToken, savedCatFact);
        return savedCatFact;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<CatFact> getAll(Integer userIdFromToken) {
        List<CatFact> userCatFacts = catFactRepository.findByUserID(userIdFromToken);
        logger.info("Fetched CatFacts for userId {}: {}", userIdFromToken, userCatFacts);
        return userCatFacts;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CatFact editCatFact(UUID id, CatFactDTO updateCatFactDTO, Integer userIdFromToken) {
        CatFact catFact = catFactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatFact not found"));

        if (!catFact.getUserID().equals(userIdFromToken)) {
            throw new RuntimeException("Unauthorized access");
        }

        if (updateCatFactDTO.getFact() != null && catFact.getIsActive()) {
            catFact.setFact(updateCatFactDTO.getFact());
        }

        CatFact updatedCatFact = catFactRepository.save(catFact);
        if (!updatedCatFact.getIsActive()) {
            updatedCatFact = null;
        }
        logger.info("Edited CatFact for userId {}: {}", userIdFromToken, updatedCatFact);
        return updatedCatFact;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CatFact deleteCatFact(UUID id, Integer userIdFromToken) {
        CatFact catFact = catFactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatFact not found"));

        if (!catFact.getUserID().equals(userIdFromToken)) {
            throw new RuntimeException("Unauthorized access");
        }

        if (!catFact.getIsActive()) {
            return null;
        }

        catFact.setIsActive(false);

        CatFact savedCatFact = catFactRepository.save(catFact);
        logger.info("Deleted (deactivated) CatFact for userId {}: {}", userIdFromToken, savedCatFact);
        return savedCatFact;
    }

    public CatFactResponse convertCatFactToCatFactResponse(CatFact catFact) {
        CatFactResponse catFactResponse = new CatFactResponse();
        catFactResponse.setId(catFact.getId());
        catFactResponse.setFact(catFact.getFact());
        catFactResponse.setAdded(catFact.getAdded());
        catFactResponse.setModified(catFact.getModified());
        return catFactResponse;
    }
}
