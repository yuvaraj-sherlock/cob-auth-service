# cob-portal
# JWT Token Generation - Initial Setup

This module provides a basic foundation for working with JWT (JSON Web Tokens) in a Spring Boot application. It includes utilities for generating tokens and extracting claims, along with a REST API endpoint to test token generation.

---

## ✅ What's Implemented

### 1. JWT Utility (`JwtUtil`)

* Encapsulates logic to:

    * Generate JWT tokens
    * Sign tokens using HMAC-SHA256 and a secure Base64-encoded secret key
    * Extract claims like expiration, subject, etc.
* Token is valid for **1 hour** by default.

### 2. Token Controller (`LoginController`)

* **Endpoint:** `GET /token/generate`
* **Purpose:** Generates a JWT token for the username `abcd`.
* **Returns:**

    * JWT token string
    * Expiration timestamp
    * Issuer (if any)

### 3. Token Response DTO (`TokenDetails`)

* A simple Java class to hold and return token metadata in JSON format.

---

## 📆 Sample Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expireAt": "2025-05-13T16:29:04.132+00:00",
  "issuer": null
}
```

---

## 📌 Next Steps

* Replace hardcoded username with a real login mechanism.
* Integrate Spring Security to validate incoming JWTs.
* Add support for role-based authorization and claim-based checks.
* Optionally, define and return issuer and other metadata.

---

## 📂 Files Modified

* `JwtUtil.java`
* `LoginController.java`
* `TokenDetails.java`

---

Feel free to expand this implementation to support login authentication, user validation, token blacklisting, refresh tokens, and more.
