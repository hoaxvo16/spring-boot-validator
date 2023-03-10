## Usage

### Config

```java

@Configuration
public class ValidationConfig {
    @PostConstruct
    public void annotationContainer() {
        AnnotationContainer.addMember(Arrays.asList(
                ContainerMember.builder().annotation(NotNull.class).build(),
                ContainerMember.builder().serviceBeanName("myCustomSizeService").annotation(Size.class).build(),
                ContainerMember.builder().annotation(Email.class).build()));
    }
}
```

Mark controller validated and add ***ValidationError*** parameter
if there is an error value of ***validationError.isPresent()*** will be true

```java

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @PostMapping(value = "/register")
    @Validated
    public ResponseEntity<Response<RegisterRequest>> registerUser(@RequestBody RegisterRequest request,
                                                                  ValidationError validationError) {
        Response<RegisterRequest> response = new Response<>();

        if (validationError.isPresent()) {
            log.info("Validation error is {}", validationError);
            response.setError(Response.Error.builder()
                    .code(validationError.getCode())
                    .message(validationError.getMessage())
                    .build());
        }
        log.info("Register request is {}", request);
        response.setData(request);

        return ResponseEntity.ok(response);
    }
}
```

Mark field to be validated

```java

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(code = "E01", message = "Email not null")
    @Email(code = "E02", message = "Hey email invalid!!!")
    private String email;

}
```

### Custom service bean for validation

```java

@Service("myCustomSizeService")
public class SizeService extends ValidationService {
    @Override
    public void handleValidation(Field field, Object value, Parameter parameter, ValidationError validationError) {
        Size annotation = getAnnotation(Size.class, parameter, field);
        String stringValue = String.valueOf(value);

        if (annotation.max() < stringValue.length())
            validationError.makeError(annotation.code(), annotation.message());

        if (annotation.min() > stringValue.length())
            validationError.makeError(annotation.code(), annotation.message());
    }

}
```
