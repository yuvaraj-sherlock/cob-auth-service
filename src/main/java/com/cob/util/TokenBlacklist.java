package com.cob.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBlacklist {
    private static final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    public static void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public static boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
