package com.restdocs.restdocssample.adapter.in.find_user_name;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
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
import com.epages.restdocs.apispec.SimpleType;
import com.restdocs.restdocssample.RestDocsSupport;
import com.restdocs.restdocssample.application.port.in.findUser.FindUserNameCommand;
import com.restdocs.restdocssample.application.port.in.findUser.FindUserNameUseCase;
import com.restdocs.restdocssample.application.service.findUser.FindUserNameServiceResponse;
import com.restdocs.restdocssample.infrastucture.exception.CustomException;
import com.restdocs.restdocssample.infrastucture.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

/*
    Success, Server Error 까지 테스트코드를 작성한다
 */
class FindUserNameDocsTest extends RestDocsSupport {

    private final FindUserNameUseCase findUserNameUseCase =
        mock(FindUserNameUseCase.class);

    @Override
    protected Object initController() {
        return new FindUserNameController(findUserNameUseCase);
    }

    @Test
    @DisplayName("success")
    void success () throws Exception {
        // given
        FindUserNameCommand command = FindUserNameCommand.builder()
            .id(1).build();
        when(findUserNameUseCase.findUserName(command))
            .thenReturn(FindUserNameServiceResponse.builder().name("od").build());

        // when
        ResultActions actions = mockMvc.perform(get("/user")
            .param("id", "1")
        );

        // then
        actions.andDo(print())
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("findUserInfo/success", // generated-snippets 하위에 adoc 파일이 생성되는 경로
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    ResourceDocumentation.resource(ResourceSnippetParameters.builder()
                        .tag("USER API")
                        .summary("사용자 이름 조회 API")
                        .queryParameters( // request parameter info
                            ResourceDocumentation.parameterWithName("id").type(SimpleType.INTEGER)
                                .description("사용자 아이디")
                        )
                        .responseFields( // response body Info
                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("상태메시지"),
                            fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                            fieldWithPath("data.name").type(JsonFieldType.STRING).description("사용자 이름")
                        )
                        .requestSchema(Schema.schema("사용자 이름 조회 API 요청 파라미터 스키마"))
                        .responseSchema(Schema.schema("사용자 이름 조회 API 응답 스키마"))
                        .build())
                )
            );
    }

    @Test
    @DisplayName("[fail] 존재하지 않는 사용자 정보 조회")
    void fail2 () throws Exception {
        // given
        FindUserNameCommand command = FindUserNameCommand.builder()
            .id(5).build();
        when(findUserNameUseCase.findUserName(command))
            .thenThrow(new CustomException(ErrorCode.NOT_FOUND_USER_INFO));

        // when
        ResultActions actions = mockMvc.perform(get("/user")
            .param("id", "5")
        );

        // then
        actions.andDo(print())
            .andExpect(status().is5xxServerError())
            .andDo(MockMvcRestDocumentationWrapper.document("getUserInfo/notFoundUserInfo",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    ResourceDocumentation.resource(ResourceSnippetParameters.builder()
                        .tag("USER API")
                        .summary("사용자 이름 조회 API")
                        .queryParameters( // request parameter info
                            ResourceDocumentation.parameterWithName("id").type(SimpleType.INTEGER)
                                .description("사용자 아이디")
                        )
                        .responseFields( // response body Info
                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("상태메시지"),
                            fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                            fieldWithPath("data.errorCode").type(JsonFieldType.NUMBER).description("에러코드"),
                            fieldWithPath("data.errorMsg").type(JsonFieldType.STRING).description("에러메시지")
                        )
                        .requestSchema(Schema.schema("사용자 이름 조회 API 요청 파라미터 스키마"))
                        .responseSchema(Schema.schema("사용자 이름 조회 API 응답 스키마"))
                        .build())
                )
            );
    }
}
