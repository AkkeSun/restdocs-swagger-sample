package com.restdocs.restdocssample.adapter.in.create_user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.restdocs.restdocssample.RestDocsSupport;
import com.restdocs.restdocssample.application.port.in.createUser.CreateUserUseCase;
import com.restdocs.restdocssample.application.service.createUser.CreateUserServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

class CreateUserDocsTest extends RestDocsSupport {

    private final CreateUserUseCase createUserUseCase =
        mock(CreateUserUseCase.class);

    @Override
    protected Object initController() {
        return new CreateUserController(createUserUseCase);
    }

    @Test
    @DisplayName("success")
    void success () throws Exception {
        // given
        CreateUserRequest request = CreateUserRequest.builder()
            .name("exg")
            .age(34)
            .build();
        when(createUserUseCase.create(request.toCommand()))
            .thenReturn(CreateUserServiceResponse.builder().result(true).build());

        // when
        ResultActions actions = mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
        );

        actions.andDo(print())
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("createUser",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                ResourceDocumentation.resource(ResourceSnippetParameters.builder()
                    .tag("USER API")
                    .summary("사용자 등록 API")
                    .requestFields( // request body info
                        fieldWithPath("name").type(JsonFieldType.STRING).description("사용자 이름"),
                        fieldWithPath("age").type(JsonFieldType.NUMBER).description("사용자 나이")
                    )
                    .responseFields( // response body Info
                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("상태메시지"),
                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                        fieldWithPath("data.result").type(JsonFieldType.BOOLEAN).description("결과")
                    )
                    .requestSchema(Schema.schema("사용자 등록 API 요청 파라미터 스키마"))
                    .responseSchema(Schema.schema("사용자 등록 API 응답 스키마"))
                    .build())
            )
        );
    }
}