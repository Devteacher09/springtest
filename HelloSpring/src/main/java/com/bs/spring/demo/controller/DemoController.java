package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.service.DemoService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoController {
	
	
	private DemoService service;
	
	@Autowired
	public DemoController(DemoService service) {
		this.service=service;
	}
	
	//클라이언트가 요청한 서비스를 실행해주는 기능
	//클라이언트가 요청한 서비스 주소에 맞는 메소드를 mapping해줌(구현)
	//메소드 선언부에 mapping주소에 대해 설정을 어노테이션으로 함.
	//@RequestMapping, @GetMapping, @PostMapping
	//@RequestMapping("연결주소")
	//@RequestMapping(value="연결주소"[,추가 옵션설정])
	
	@RequestMapping("/demo/demo.do")
	public String demoPage() {
		//맵핑메소드는 servlet에서 doGet, doPost와 동일한 기능을 함.
		//맵핑메소드의 메소드명은 개발자가 정함(서비스알아볼 수 있게 설정하자)
		//demo페이지를 출력해주는 서비스
		
		//Spring에서 view를 연결할때는 ViewResolverBean을 이용한다.
		//InternalResourceViewResolver를 등록해서 활용함.(jsp페이지연결시...)
		//InternalResourceViewResolver 맵핑메소드가 반환하는 값을 가지고
		//Resource내부에 있는 jsp화면을 찾아서 응답을 해줌.
		//RequestDispatcher.forward()방식으로 응답함.
		
		//String이 반환값일때 그 string값은 응답할 페이지 이름이 되고.
		//InternalResoureViewResolver에 설정된 
		//prefix, suffix값을 붙여서 경로와 페이지 확장자를 설정함.
		// /WEB-INF/views/demo/demo.jsp -> RequestDispatcher.forward();
		return "demo/demo";
		
	}
	
	//spring에서 클라이언트가 전송한 데이터 받기
	//맵핑메소드의 반환형과 매개변수
	//1. 반환형
	// - String : ViewResolver에 의해서 view화면(jsp)를 반환할때 사용
	// - ModelAndView : 화면에서 이용할 데이터와 화면을 모두 저장하는 객체
	// - void : HttpServlerResponse객체를 이용해서 응답메세지를 작성
//				(download, upload, json데이터전송)
	// - 클래스타입(Vo객체, List, Map) : 서비스에서 생성된 객체 자체를 반환할때 -> json변환
	//   @ReponseBody선언과 converter(jackson)를 등록
	
	//2. 매개변수선언 -> springcontainer가 알아서 전달해줌.
	// - HttpServletRequest : 서블릿에서 사용한 그 객체 -> request
	// - HttpServletResponse : 서블릿에서 사용한 그 객체 -> response
	// - HttpSession : HttpSelverRequest객체 없어도 선언만하면 됨. 
	// - java.util.Locale : 서버의 로케일정보를 저장한 객체를 전달해줌.
	// - InputStream/Reader : 파일을 IO할때 사용하는 Stream객체
	// - OutputStrea/Writer : 파일을 IO할때 사용하는 Stream객체 
	// - 기본자료형 : 클라이언트가 보낸 name명칭이 선언한 매개변수의 이름과 동일할 경우 대입
	//				이름이 다를 경우 @RequestParam어노테이션으로 연결해줄 수 있음. 추가 옵션도 설정가능 
	// - 클래스타입(vo,dto) : command라고 하고 클라이언트가 보낸 데이터와 command객체의 멤버변수명이 같으면
	//						객체를 생성해서 대입해줌
	//						주의 : 맴버변수로 클래스타입이 있으면 불가능
	// - java.util.Map : 클라이언트가 보낸 데이터를 map형식으로 변경해서 대입해줌..
	
	// - Model : jsp에서 사용할 데이터를 저장하는 객체 * request.setAttribute한것과 동일
	// - ModelAndView : model객체와 반환할 view를 동시에 설정하는 객체
	
	// - 기본자료형에 @CookieValue, @RequestHeader어노테이션을 이용하면 
	// cookie, Header값을 간편하게 받아올 수 있음
	
	//선언부에, 매개변수선언부에.. -> converter가 설정되어있어야 한다.
	//@ResponseBody : 반환하는 클래스를 json형태로 변환해서 반환
	//@RequestBody : 클라이언트가 보낸 json형태의 데이터를 vo객체로 변환해서 저장
	
	//서블릿으로 이용하기
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest request, HttpServletResponse response) 
	//public void demo1(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException{
//		System.out.println(request);
//		System.out.println(response);
		
		String name=request.getParameter("devName");
		int age=Integer.parseInt(request.getParameter("devAge"));
		String gender=request.getParameter("devGender");
		String email=request.getParameter("devEmail");
		String[] devlang=request.getParameterValues("devLang");
		System.out.println(name+age+gender+email);
		for(String l : devlang) {
			System.out.println(l);
		}
		
		Demo d=Demo.builder()
				.devName(name)
				.devAge(age)
				.devEmail(email)
				.devGender(gender)
				.devLang(devlang)
				.build();
		request.setAttribute("demo", d);
		
//		request.getRequestDispatcher("/WEB-INF/views/demo/demoInfo.jsp")
//		.forward(request, response);
		
//		PrintWriter out=response.getWriter();
//		response.setContentType("text/html;charset=utf-8");
//		out.print("<h1>전송완료</h1>");
		//return "";
		return "demo/demoInfo";
	}
	
	//클라이언트가 보낸데이터 기본자료형으로 받기 
	// -> 클라이언트가 보낸 name값(key), 변수명을 동일하게 설정
	// 매개변수로 선언한 데이터가 모두 전달되어야함.
	// 한개라도 빠지면 안됨.
	@RequestMapping("/demo/demo2.do")
	public String demo2(String devName, int devAge, 
			String devEmail, String devGender, String[] devLang
			,Model m) {
		
		System.out.println(devName+devAge+devEmail+devGender);
		System.out.println(Arrays.toString(devLang));
		
		Demo d=Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devGender(devGender).devEmail(devEmail)
				.devLang(devLang).build();
		//Model객체를 이용해서 화면에 전송할 데이터를 저장한다.
		//저장방식을 request.setAttritbute()와 동일
		//key : value형식으로 저장
		//Model.addAttribute("key",Object);
		m.addAttribute("demo",d);		
		
		return "demo/demoInfo";
	}
	
	
	//@RequestParam어노테이션이용해서 
	//매개변수값에 대한 옵션설정하기
	@RequestMapping("/demo/demo3.do")
	public String demo3(
			@RequestParam(value="devName") String name,
			@RequestParam(value="devAge", defaultValue="10") int age,
			@RequestParam(value="devEmail") String email,
			@RequestParam(value="devGender") String gender,
			@RequestParam(value="devLang",required=false) String[] lang,
			Model m) {
		
		m.addAttribute("demo",
				Demo.builder().devName(name).devAge(age).devEmail(email)
				.devGender(gender).devLang(lang).build());
		
		return "demo/demoInfo";
	}
	
	//Command객체를 이용해서 파라미터 직접받기
	//vo객체에 필드로 다른 클래스가 있는 경우 주의해야함.
	//날짜는 java.sql.Date를 이용하자.
	@RequestMapping("/demo/demo4.do")
	public String demo5(Demo demo,Model m) {
		System.out.println(demo);
		m.addAttribute("demo",demo);
		return "demo/demoInfo";
	}
	
	//Map으로 클라이언트가 보낸 데이터 받아오기
	@RequestMapping("/demo/demo5.do")
	public String mapMappingTest(@RequestParam Map param,String[] devLang,Model m) {
		System.out.println(param);
		param.put("devLang", devLang);
		m.addAttribute("demo",param);
		return "demo/demoInfo";
	}
	
	
	//추가적인 데이터 받아오기
	// session값, header, cookie 정보를 한번에 가져오기
	
	@RequestMapping("/demo/demo6.do")
	public String extraData(
			@CookieValue(value="testCookie",required=false) String cookieVal,
			@SessionAttribute(value="sessionId") String sessionVal,
			@RequestHeader(value="User-agent") String userAgent,
			@RequestHeader(value="Referer") String referer
			) {
		
		System.out.println(cookieVal);
		System.out.println(sessionVal);
		System.out.println(userAgent);
		System.out.println(referer);
		
		return "demo/demoInfo";
	}
	
	//ModelAndView로 반환하기
	//ModelAndView는 model정보와 view의 정보를 한번에 저장관리하는 객체
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndView(ModelAndView mv, Demo demo) {
		//data : mv.addObject("key",value);
		//view : mv.setViewName("view이름");
		mv.addObject("demo",demo);
		mv.setViewName("demo/demoInfo");
		
		return mv;
	}
	
	
	@RequestMapping("demo/insertDemo.do")
	public String insertDemo(Demo demo, Model m) {
		
		int result=service.insertDemo(demo);
		System.out.println(result);
				
		//demo화면으로 전환하기 -> 
		//기본적으로 String으로 반환하면 RequestDispacher.forward방식
		//spring에서 redirect 처리하기!
		//viewName에 redirect:주소작성(맵핑주소) 하면 redirect처리가 됨.
		return "redirect:/demo/demo.do";
	}
	
	@RequestMapping("/demo/demoList.do")
	public String demoList(Model m) {
		List<Demo> demos=service.selectDemoAll();
		m.addAttribute("demos",demos);
		return "demo/demoList";
	}
	
	
	
	
	
	
	
}







