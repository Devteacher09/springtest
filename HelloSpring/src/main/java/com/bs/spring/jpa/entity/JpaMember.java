package com.bs.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jpa_member")
@SequenceGenerator(name="seq_jpamember",sequenceName="seq_jpamember",
initialValue=1,allocationSize=1)
public class JpaMember {
	//컬럼에 대한 설정을 하는 어노테이션 -> 필드선언부에 선언
	//@Id : 아래 필드를 pk컬럼으로 사용하는 설정
	//@GeneratedValue : 기본키의 값을 자동생성해서 부여하게하는 설정 
	//		sequence와 연동하여 처리 *DB마다 설정하는것이 다름
	//	속성 
	//	strategy(전략) : 
	//		GenerationType.IDENTITY : auto_increment기능, * mssql,mysql,mariadb postgreSQL
	//		GenerationType.SEQUENCE : sequence객체를 이용해서 부여, * oracle, postgreSQL
	//		GenerationType.TABLE : 전체 다 적용 별도의 Sequence테이블을 생성해서 활용
	//		GenerationType.AUTO : 설정한 방언 전략방식에 따라서 자동으로 결정.
	//  generator : 선언한 sequenceGenerator name을 설정함
	private Long memberNo;
	private String memberId;
	private String memberPwd;
	private Integer age;
	private double height;

}








