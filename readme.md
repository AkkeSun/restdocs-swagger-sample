# [SPRING REST Docs + Swagger Example]

수정일 : 2024.03.08

- RestDocs 를 사용하여 테스트 코드 기반으로 API 문서 정보를 만든 후 Swagger 로 화면을 자동생성해준다.
- 서비스 코드에 침투적인 Swagger 의 단점과, 문서를 직접 작성해야한다는 RestDocs 의 단점을 모두 해소하였다

-----

### API 문서 작성 방법
1. API 문서 작성을 위한 테스트코드를 작성합니다
2. 서비스를 build 합니다
3. build 한 파일을 실행한후 {host}/docs/index.html 에 접속합니다