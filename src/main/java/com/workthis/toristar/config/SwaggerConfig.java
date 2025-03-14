package com.workthis.toristar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workthis.toristar.common.annotation.ApiErrorCodeExample;
import com.workthis.toristar.common.annotation.ApiErrorExceptionsExample;
import com.workthis.toristar.common.annotation.DisableSwaggerSecurity;
import com.workthis.toristar.common.annotation.ExplainError;
import com.workthis.toristar.common.dto.ErrorResponse;
import com.workthis.toristar.common.exception.BaseErrorCode;
import com.workthis.toristar.common.exception.ProjectCodeException;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Field;
import java.util.*;

import static io.swagger.v3.oas.models.security.SecurityScheme.In;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type;
import static java.util.stream.Collectors.groupingBy;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public OpenAPI openAPI(ServletContext servletContext) {
        String contextPath = servletContext.getContextPath();
        Server server = new Server()
                .url(contextPath)
                .description("Default Server URL");
        return new OpenAPI().servers(List.of(server)).components(authSetting()).info(swaggerInfo());
    }

    @Bean
    public GroupedOpenApi auth() {
        return GroupedOpenApi
                .builder()
                .group("Auth APIs")
                .addOperationCustomizer(customize())
                .pathsToMatch("/v1/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi members() {
        return GroupedOpenApi
                .builder()
                .group("Members APIs")
                .addOperationCustomizer(customize())
                .pathsToMatch("/v1/members/**")
                .build();
    }

    @Bean
    public GroupedOpenApi stars() {
        return GroupedOpenApi
                .builder()
                .group("Stars APIs")
                .addOperationCustomizer(customize())
                .pathsToMatch("/v1/stars/**")
                .build();
    }

    @Bean
    public GroupedOpenApi conversations() {
        return GroupedOpenApi
                .builder()
                .group("Conversations APIs")
                .addOperationCustomizer(customize())
                .pathsToMatch("/v1/conversations/**")
                .build();
    }

    private Info swaggerInfo() {
        License license = new License();
        license.setUrl("https://github.com/potenday-06/toristar-backend");
        license.setName("토리별 백엔드 서버");

        return new Info()
                .version("v1.0.0")
                .title("토리별 서버 API 문서")
                .description("토리별 서버의 API 문서 입니다.")
                .license(license);
    }

    private Components authSetting() {
        return new Components()
            .addSecuritySchemes(
                "access-token",
                new SecurityScheme()
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(In.HEADER)
                    .name("Authorization"));
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            DisableSwaggerSecurity methodAnnotation =
                    handlerMethod.getMethodAnnotation(DisableSwaggerSecurity.class);
            ApiErrorExceptionsExample apiErrorExceptionsExample =
                    handlerMethod.getMethodAnnotation(ApiErrorExceptionsExample.class);
            ApiErrorCodeExample apiErrorCodeExample =
                    handlerMethod.getMethodAnnotation(ApiErrorCodeExample.class);

            List<String> tags = getTags(handlerMethod);
            // DisableSecurity 어노테이션있을시 스웨거 시큐리티 설정 삭제
            if (methodAnnotation != null) {
                operation.setSecurity(Collections.emptyList());
            }
            // 태그 중복 설정시 제일 구체적인 값만 태그로 설정
            if (!tags.isEmpty()) {
                operation.setTags(Collections.singletonList(tags.get(0)));
            }
            // ApiErrorExceptionsExample 어노테이션 단 메소드 적용
            if (apiErrorExceptionsExample != null) {
                generateExceptionResponseExample(operation, apiErrorExceptionsExample.value());
            }
            // ApiErrorCodeExample 어노테이션 단 메소드 적용
            if (apiErrorCodeExample != null) {
                generateErrorCodeResponseExample(operation, apiErrorCodeExample.value());
            }
            return operation;
        };
    }
    /**
     * BaseErrorCode 타입의 이넘값들을 문서화 시킵니다. ExplainError 어노테이션으로 부가설명을 붙일수있습니다. 필드들을 가져와서 예시 에러 객체를
     * 동적으로 생성해서 예시값으로 붙입니다.
     */
    private void generateErrorCodeResponseExample(
            Operation operation, Class<? extends BaseErrorCode> type) {
        ApiResponses responses = operation.getResponses();

        BaseErrorCode[] errorCodes = type.getEnumConstants();

        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                Arrays.stream(errorCodes)
                        .map(
                                baseErrorCode -> {
                                    try {
                                        ErrorResponse errorResponse = baseErrorCode.getErrorResponse();
                                        return ExampleHolder.builder()
                                                .holder(
                                                        getSwaggerExample(
                                                                baseErrorCode.getExplainError(),
                                                                errorResponse))
                                                .code(errorResponse.getStatus())
                                                .name(errorResponse.getCode())
                                                .build();
                                    } catch (NoSuchFieldException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        .collect(groupingBy(ExampleHolder::getCode));

        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    /**
     * SwaggerExampleExceptions 타입의 클래스를 문서화 시킵니다. SwaggerExampleExceptions 타입의 클래스는 필드로
     * ProjectCodeException 타입을 가지며, ProjectCodeException 의 errorResponse 와,ExplainError 의 설명을
     * 문서화시킵니다.
     */
    private void generateExceptionResponseExample(Operation operation, Class<?> type) {
        ApiResponses responses = operation.getResponses();

        // ----------------생성
        Object bean = applicationContext.getBean(type);
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                Arrays.stream(declaredFields)
                        .filter(field -> field.getAnnotation(ExplainError.class) != null)
                        .filter(field -> field.getType() == ProjectCodeException.class)
                        .map(
                                field -> {
                                    try {
                                        ProjectCodeException exception =
                                                (ProjectCodeException) field.get(bean);
                                        ExplainError annotation =
                                                field.getAnnotation(ExplainError.class);
                                        String value = annotation.value();
                                        ErrorResponse errorResponse = exception.getErrorResponse();
                                        return ExampleHolder.builder()
                                                .holder(getSwaggerExample(value, errorResponse))
                                                .code(errorResponse.getStatus())
                                                .name(field.getName())
                                                .build();
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        .collect(groupingBy(ExampleHolder::getCode));

        // -------------------------- 콘텐츠 세팅 코드별로 진행
        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    private Example getSwaggerExample(String value, ErrorResponse errorResponse) {
        Example example = new Example();
        example.description(value);
        example.setValue(errorResponse);
        return example;
    }

    private void addExamplesToResponses(
            ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();
                    v.forEach(
                            exampleHolder -> mediaType.addExamples(
                                    exampleHolder.getName(), exampleHolder.getHolder()));
                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    responses.addApiResponse(status.toString(), apiResponse);
                });
    }

    private static List<String> getTags(HandlerMethod handlerMethod) {
        List<String> tags = new ArrayList<>();

        Tag[] methodTags = handlerMethod.getMethod().getAnnotationsByType(Tag.class);
        List<String> methodTagStrings =
                Arrays.stream(methodTags).map(Tag::name).toList();

        Tag[] classTags = handlerMethod.getClass().getAnnotationsByType(Tag.class);
        List<String> classTagStrings =
                Arrays.stream(classTags).map(Tag::name).toList();
        tags.addAll(methodTagStrings);
        tags.addAll(classTagStrings);
        return tags;
    }
}
