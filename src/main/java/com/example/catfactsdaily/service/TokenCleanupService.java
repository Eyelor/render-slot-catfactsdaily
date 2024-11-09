package com.example.catfactsdaily.service;

import com.example.catfactsdaily.entity.TokenBlacklisted;
import com.example.catfactsdaily.repository.TokenBlacklistedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TokenCleanupService {

    private static final Logger logger = LoggerFactory.getLogger(TokenCleanupService.class);

    private final TokenBlacklistedRepository tokenBlacklistedRepository;

    @Autowired
    public TokenCleanupService(TokenBlacklistedRepository tokenBlacklistedRepository) {
        this.tokenBlacklistedRepository = tokenBlacklistedRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Scheduled(fixedRate = 420000) // every 7 min
    public void removeExpiredTokens() {
        Date now = new Date();
        List<TokenBlacklisted> expiredTokens = tokenBlacklistedRepository.findByExpirationDateBefore(now);

        if (!expiredTokens.isEmpty()) {
            tokenBlacklistedRepository.deleteAll(expiredTokens);
            logger.info("Expired tokens removed: {}", expiredTokens.size());
        }
    }
}
