package com.bs.spring.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bs.spring.jpa.common.MemberLevel;

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
	//@Id : 아래 필드를 pk컬럼으로 사용하는 설정 * 한개 필드에 반드시 선언!
	//@GeneratedValue : 기본키의 값을 자동생성해서 부여하게하는 설정 
	//		sequence와 연동하여 처리 *DB마다 설정하는것이 다름
	//	속성 
	//	strategy(전략) : 
	//		GenerationType.IDENTITY : auto_increment기능, * mssql,mysql,mariadb postgreSQL
	//		GenerationType.SEQUENCE : sequence객체를 이용해서 부여, * oracle, postgreSQL
	//		GenerationType.TABLE : 전체 다 적용 별도의 Sequence테이블을 생성해서 활용
	//		GenerationType.AUTO : 설정한 방언 전략방식에 따라서 자동으로 결정.
	//  generator : 선언한 sequenceGenerator name을 설정함
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	generator = "seq_jpamember")
	private Long memberNo;
	// @Column : 필드와 매핑되는 테이블컬럼에 대해 설정하는 어노테이션
	// 속성
	// name : 필드와 매핑될 컬럼의 이름 설정
	// insertTable : true(default), false false면 필드값을 DB에 저장하지 않는 설정
	// updateTable : 수정시 수정을 하지 않는것 true(default),false
	// nullable : 컬럼에 null제약조건설정 true : null, false : not null
	// unique : 컬럼에 unique제약조건설정 true:설정, false:미설정
	// columnDefinition : 컬럼에 대한 종합적인 설정 DB에서 컬럼설정한내용을 작성
	//   예) "varchar2(200) not null default "tt"
	// length : 문자열 길이에 대한 설정
	// precision : Number형에 대한 전체 자리수설정 
	// scale : number형에 대한 소수점 자리수 설정
	@Column(name="member_id", nullable=false, unique=true, length=50)
	private String memberId;
	@Column(name="member_pwd", 
			columnDefinition = "varchar2(80) not null")
	private String memberPwd;
	@Column(name="age")// precision = 20, scale=0)
	private Integer age;
//	@Column(insertable = false)
	private double height;

	//컬럼타입을 설정하는 어노테이션
	// @Enumerated : java의 EnumType과 매핑할때 사용하는 어노테이션
	// 속성 : value 
	//		EnumType.ORDINAL : DB에 저장할때 Enum의 숫자를 저장 
	//		EnumType.String : DB에 저장할때 Enum의 문자열을 저장 * 권장
	// @Temporal : java의 Date타입과 매핑할때 사용하는 어노테이션
	// 속성 : value
	//		TemporalType.DATE : DB에 DATE타입과 매핑해서 저장
	//		TemporalType.TIMESTAMP : DB에 TIMESTAMP타입과 매핑해서 저장
	// @Lob : DB의 blob,clob타입으로 저장할때 사용하는 어노테이션
	// 필드타입에따라서 bloc, clob결정이됨.
	// String, String[] : clob
	// byte[] : blob
	
//	@Enumerated(value = EnumType.ORDINAL)
	@Enumerated(value = EnumType.STRING)
	private MemberLevel memberLevel;//MemberLevel.GOLD
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date enrollDate;
	
	@Lob
	private String info;
	
//	@Lob
//	private byte[] data;
	
	
	
}








