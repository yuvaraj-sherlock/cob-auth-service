## Below are the Common Java/Spring Boot Exceptions I came across during the developement and providing the Reasons & Fixes
### 1. `java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'TDP' for key 'cob_user.userName'`

* **Reason**: Attempting to insert a duplicate value into a column (`userName`) that has a unique constraint.
* **Fix**:

    * Perform a pre-check before inserting: `userRepository.existsByUserName(...)`
    * Add a unique constraint in the entity:

      ```java
      @Column(unique = true)
      private String userName;
      ```

---

### 2. `org.hibernate.NonUniqueResultException: Query did not return a unique result: 2 results were returned`

* **Reason**: You used a method (e.g., `getSingleResult()` or `Optional<T> findByXyz()`) expecting **only one** record, but multiple rows were found in the database.
* **Fix**:

    * Ensure the DB has unique values for the queried field.
    * Or, use `List<T> findByXyz()` if multiple results are expected.

---

### 3. `org.hibernate.InstantiationException: No default constructor for entity 'com.cob.entity.UserEntity'`

* **Reason**: Hibernate requires a **no-args constructor** to instantiate entity objects using reflection.
* **Fix**:

    * If using Lombok `@Builder`, also add:

      ```java
      @NoArgsConstructor
      @AllArgsConstructor
      @Builder
      ```
    * Avoid using `@Builder` alone for entities without constructors.

---

### 4. `io.jsonwebtoken.security.SignatureException: JWT signature does not match locally computed signature`

* **Reason**: The JWT token is **tampered or signed with a different secret key** than the one used for verification.
* **Fix**:

    * Ensure the same secret key is used for signing and validating tokens.
    * Catch `JwtException` in your filter:

      ```java
      try {
          Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      } catch (JwtException ex) {
          // handle invalid token
      }
      ```

---

### 5. `org.hibernate.exception.JDBCConnectionException: unable to obtain isolated JDBC connection [Public Key Retrieval is not allowed]`

* **Reason**: JDBC driver cannot connect to MySQL because **public key retrieval is disabled**.
* **Fix**:

    * Add the following to your DB URL in `application.properties`:

      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/dbname?allowPublicKeyRetrieval=true&useSSL=false
      ```

> `ResponseEntity` Usage

* **Use `ResponseEntity<>` only in `@Controller` or `@RestController` layers.**
* **Do NOT return `ResponseEntity<>` in `@Service` or utility classes.** Instead, return plain data or throw a custom exception, and handle the response formatting in the controller.

---

### 6. `org.springframework.web.bind.MethodArgumentNotValidException`

* **Reason**: Validation failed for a controller method parameter annotated with `@Valid` because the request body didn't satisfy the constraints (`@NotNull`, `@Size`, etc.)
* **Fix**:

    * Ensure the incoming JSON or form data matches the validation rules.
    * Example DTO:

      ```java
      public class UserDto {
          @NotBlank(message = "Username is mandatory")
          private String username;
      }
      ```

---
### 7. Record use-case
> ✅ **Use Case:** When a method needs to return multiple values (e.g., a boolean and a custom object), use a `record` to wrap them together cleanly.

> 🔄 **Solution:** Define a `record` (e.g., `ValidationResult(boolean valid, UserDto userDto)`) to encapsulate the response.

> 🧼 **Benefit:** Records are immutable, concise, and reduce boilerplate — perfect for returning structured data from service methods.

---