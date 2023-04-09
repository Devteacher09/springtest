package com.bs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring.model.vo.Food;
import com.bs.spring.model.vo.Person;
import com.bs.spring.model.vo.Phone;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//springbean으로 등록된 bean사용하기 
	//spring에게 자동으로 불러와 달라고 설정
	//@Autowired어노테이션을 이용 -> 의존성 주입(DI)
	//지역변수에서는 활용이 불가능함!!!
	//멤버변수로 선언한 것을 이용할 때 사용할 수 있다.
	//@Autowired//등록된 springbean중에서 type이 일치하는 bean을 알아서 대입
	//필드명과 등록된 bean의 id값이 일치하면 자동을 맵핑해서 가져옴
	//Autowired했을때 동일한 타입의 bean다수일때는 어노테이션을 이용해서
	//특정할 수 있다.
	//@Qualifier(value="p2")
	private Person person;
	
	private Phone p;
	
	@Autowired
	@Qualifier("f")
	private Food f;
	
	
	//생성자를 이용해서 필드에 주입할 수 있음
//	public HomeController(@Qualifier("p3") Person person) {
//		this.person=person;
//	}
	@Autowired
	public void setP(@Qualifier("p") Phone p) {
		this.p=p;
	}
	
	@Autowired
	public void setPerson(@Qualifier("p3") Person person) {
		this.person=person;
	}
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";// /WEB-INF/views/return값.jsp
	}
	
	
	//추가 기능설정하기
	@RequestMapping("/test")
	public String test() {
		System.out.println("실행됨!");
		//p에 데이터가 있는지 확인하기
		System.out.println("필드 p출력 : "+person);
		System.out.println("필드 Phone출력 : "+p);
		System.out.println("필드 food출력 : "+f);
		
		
		
		return "index";
	}
	
	
	
	
}
