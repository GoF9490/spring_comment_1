package comment.crud;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AutoAppConfig {

}

/**
 * @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록.
 * 이때 스프링 빈의 기본이름은 클래스명을 사용하되 맨 앞글자만 소문자만 사용 (MemberService -> memberService)
 * 만약 스프링 빈의 이름을 직접 지정하고 싶으면 @Component("이름") 형식으로 부여 가능
 *
 * 컴포넌트 스캔은 되도록 프로젝트 최상단 폴더에 넣자.
 * @SpringApplicationBoot 를 사용하면 자동으로 컴포넌트스캔이 이루어지니 따로 등록할 필요가 없음
 *
 * 컴포넌트 스캔은 @Component 뿐 아니라 @Controller / @Service / @Repository / @Configration 도 읽어서 등록
 *
 * @Controller : 스프링 MVC 컨트롤러로 인식
 * @Service : 특별한 처리는 없으나 사용자가 코드를 인식하는데 도움이 됨.
 * @Repository : 스프링 데이터접근 계층으로 인식하고, 데이터 계층(db)의 예외를 스프링 예외로 변환해줌 (db를 바꿔도 호환)
 * @Configuration : 스프링 설정 정보를 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 해줌
 */
