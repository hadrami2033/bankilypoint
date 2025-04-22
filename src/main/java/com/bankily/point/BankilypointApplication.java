package com.bankily.point;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bankily.point.entity.CommissionsVersement;
import com.bankily.point.entity.CommissionsWithdrawal;
import com.bankily.point.entity.Month;
import com.bankily.point.entity.Role;
import com.bankily.point.entity.Type;
import com.bankily.point.entity.Year;
import com.bankily.point.repository.CommissionsVersementRepository;
import com.bankily.point.repository.CommissionsWithdrawalRepository;
import com.bankily.point.repository.MonthRepository;
import com.bankily.point.repository.RoleRepository;
import com.bankily.point.repository.TypeRepository;
import com.bankily.point.repository.YearRepository;
import java.util.List;

@SpringBootApplication
public class BankilypointApplication implements CommandLineRunner{
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
	@Autowired
	private YearRepository yearRepository;
	
	@Autowired
	private MonthRepository monthRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CommissionsWithdrawalRepository commissionsWithdrawalRepository;
	
	@Autowired
	private CommissionsVersementRepository commissionsVersementRepository;
	
	public static void main(String[] args) {
		
		SpringApplication.run(BankilypointApplication.class, args);
	}
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:3000");
			}
		};
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		List<Year> years = yearRepository.findAll();
		List<Month> months = monthRepository.findAll();
		List<Type> types = typeRepository.findAll();
		List<Role> roles = roleRepository.findAll();
		List<CommissionsWithdrawal> commissionsWithdrawal = commissionsWithdrawalRepository.findAll();
		List<CommissionsVersement> commissionsVersement = commissionsVersementRepository.findAll();

		if(years.size() == 0) {
			Year y2022 = new Year("2022");
			Year y2023 = new Year("2023");
			Year y2024 = new Year("2024");
			Year y2025 = new Year("2025");
			Year y2026 = new Year("2026");
			Year y2027 = new Year("2027");
			Year y2028 = new Year("2028");
			Year y2029 = new Year("2029");
			Year y2030 = new Year("2030");
			Year y2031 = new Year("2031");
			Year y2032 = new Year("2032");
			Year y2033 = new Year("2033");
			Year y2034 = new Year("2034");
			Year y2035 = new Year("2035");
			yearRepository.saveAll(List.of(y2022, y2023, y2024, y2025, y2026, y2027, y2028, y2029, y2030, y2031, y2032, y2033, y2034, y2035));
		}

		if(months.size() == 0) {
			Month jan = new Month(01, "Janvier");
			Month fev = new Month(02, "Fevrier");
			Month mar = new Month(03, "Mars");
			Month avril = new Month(04, "Avril");
			Month mai = new Month(05, "Mai");
			Month juin = new Month(06, "Juin");
			Month juillet = new Month(07, "Juillet");
			Month aout = new Month(8, "Aout");
			Month sep = new Month(9, "Septembre");
			Month oct = new Month(10, "Octobre");
			Month nov = new Month(11, "Novembre");
			Month dec = new Month(12, "Decembre");
			monthRepository.saveAll(List.of(jan, fev, mar, avril, mai, juin, juillet, aout, sep, oct, nov, dec));
		}
		
		if(types.size() == 0) {
			Type v = new Type("versement");
			Type w2 = new Type("withdrawal2");
			Type w = new Type("withdrawal");
			Type e = new Type("entree");
			Type wal = new Type("wallets");
			typeRepository.saveAll(List.of(v, w2, w, e, wal));
		}
		
		if(roles.size() == 0) {
			Role user = new Role("ROLE_USER");
			Role admin = new Role("ROLE_ADMIN");
			roleRepository.saveAll(List.of(user, admin));
		}
		
		if(commissionsVersement.size() == 0) {
			CommissionsVersement commission1 = new CommissionsVersement(100    ,10009    ,10 );
			CommissionsVersement commission2 = new CommissionsVersement(10010  ,20014    ,15 );
			CommissionsVersement commission3 = new CommissionsVersement(20015  ,30019    ,20 );
			CommissionsVersement commission4 = new CommissionsVersement(30020  ,40024    ,25 );
			CommissionsVersement commission5 = new CommissionsVersement(40025  ,50029    ,30 );
			CommissionsVersement commission6 = new CommissionsVersement(50030  ,60034    ,35 );
			CommissionsVersement commission7 = new CommissionsVersement(60035  ,70039    ,40 );
			CommissionsVersement commission8 = new CommissionsVersement(70040  ,80044    ,45 );
			CommissionsVersement commission9 = new CommissionsVersement(80045  ,90049    ,50 );
			CommissionsVersement commission10 = new CommissionsVersement(90050  ,100054   ,55);
			CommissionsVersement commission11 = new CommissionsVersement(100055 ,110059   ,60);
			CommissionsVersement commission12 = new CommissionsVersement(110060 ,120064   ,65);
			CommissionsVersement commission13 = new CommissionsVersement(120065 ,130069   ,70);
			CommissionsVersement commission14 = new CommissionsVersement(130070 ,140074   ,75);
			CommissionsVersement commission15 = new CommissionsVersement(140075 ,150079   ,80);
			CommissionsVersement commission16 = new CommissionsVersement(150080 ,160084   ,85);
			CommissionsVersement commission17 = new CommissionsVersement(160085 ,170089   ,90);
			CommissionsVersement commission18 = new CommissionsVersement(170090 ,180094   ,95);
			CommissionsVersement commission19 = new CommissionsVersement(180095 ,190099   ,100);
			CommissionsVersement commission20 = new CommissionsVersement(190100 ,200104   ,105);
			CommissionsVersement commission21 = new CommissionsVersement(200105 ,210109   ,130);
			CommissionsVersement commission22 = new CommissionsVersement(210110 ,220114   ,135);
			CommissionsVersement commission23 = new CommissionsVersement(220115 ,230119   ,140);
			CommissionsVersement commission24 = new CommissionsVersement(230120 ,240124   ,145);
			CommissionsVersement commission25 = new CommissionsVersement(240125 ,250129   ,150);
			CommissionsVersement commission26 = new CommissionsVersement(250130 ,260134   ,155);
			CommissionsVersement commission27 = new CommissionsVersement(260135 ,270139   ,160);
			CommissionsVersement commission28 = new CommissionsVersement(270140 ,280144   ,165);
			CommissionsVersement commission29 = new CommissionsVersement(280145 ,290149   ,170);
			CommissionsVersement commission30 = new CommissionsVersement(290150 ,300000   ,175);
			
			commissionsVersementRepository.saveAll(List.of(
					commission1	,
					commission2 ,
					commission3 ,
					commission4 ,
					commission5 ,
					commission6 ,
					commission7 ,
					commission8 ,
					commission9 ,
					commission10,
					commission11,
					commission12,
					commission13,
					commission14,
					commission15,
					commission16,
					commission17,
					commission18,
					commission19,
					commission20,
					commission21,
					commission22,
					commission23,
					commission24,
					commission25,
					commission26,
					commission27,
					commission28,
					commission29,
					commission30
			));
		}
		
		if(commissionsWithdrawal.size() == 0) {
			CommissionsWithdrawal comWithdrawal1 = new CommissionsWithdrawal(0   ,	500,  			5);
			CommissionsWithdrawal comWithdrawal2 = new CommissionsWithdrawal(500.01f , 1000,    	7.5f);
			CommissionsWithdrawal comWithdrawal3 = new CommissionsWithdrawal(1000.01f,    2000,     10);
			CommissionsWithdrawal comWithdrawal4 = new CommissionsWithdrawal(2000.01f,    3000,     12.5f);
			CommissionsWithdrawal comWithdrawal5 = new CommissionsWithdrawal(3000.01f,    5000,     15);
			CommissionsWithdrawal comWithdrawal6 = new CommissionsWithdrawal(5000.01f,    7000,     17.5f);
			CommissionsWithdrawal comWithdrawal7 = new CommissionsWithdrawal(7000.01f,    10000,    20);
			CommissionsWithdrawal comWithdrawal8 = new CommissionsWithdrawal(10000.01f,   15000,    25);
			CommissionsWithdrawal comWithdrawal9 = new CommissionsWithdrawal(15000.01f,   20000,    35);
			CommissionsWithdrawal comWithdrawal10 = new CommissionsWithdrawal(20000.01f,   30000,   45);
			CommissionsWithdrawal comWithdrawal11 = new CommissionsWithdrawal(30000.01f,   40000,   55);
			CommissionsWithdrawal comWithdrawal12 = new CommissionsWithdrawal(40000.01f,   50000,   70);
			CommissionsWithdrawal comWithdrawal13 = new CommissionsWithdrawal(50000.01f,   60000,   80);
			CommissionsWithdrawal comWithdrawal14 = new CommissionsWithdrawal(60000.01f,   70000,   95);
			CommissionsWithdrawal comWithdrawal15 = new CommissionsWithdrawal(70000.01f,   80000,   105);
			CommissionsWithdrawal comWithdrawal16 = new CommissionsWithdrawal(80000.01f,   90000,   120);
			CommissionsWithdrawal comWithdrawal17 = new CommissionsWithdrawal(90000.01f,   100000,  137.5f);
			CommissionsWithdrawal comWithdrawal18 = new CommissionsWithdrawal(100000.01f,  130000,  162.5f);
			CommissionsWithdrawal comWithdrawal19 = new CommissionsWithdrawal(130000.01f,  160000,  200);
			CommissionsWithdrawal comWithdrawal20 = new CommissionsWithdrawal(160000.01f,  200000,  250);
			CommissionsWithdrawal comWithdrawal21 = new CommissionsWithdrawal(200000.01f,  230000,  350);
			CommissionsWithdrawal comWithdrawal22 = new CommissionsWithdrawal(230000.01f,  260000,  450);
			CommissionsWithdrawal comWithdrawal23 = new CommissionsWithdrawal(260000.01f,  300000,  500);
			
			commissionsWithdrawalRepository.saveAll(List.of(
					comWithdrawal1,
					comWithdrawal2,
					comWithdrawal3,
					comWithdrawal4,
					comWithdrawal5,
					comWithdrawal6,
					comWithdrawal7,
					comWithdrawal8,
					comWithdrawal9,
					comWithdrawal10,
					comWithdrawal11,
					comWithdrawal12,
					comWithdrawal13,
					comWithdrawal14,
					comWithdrawal15,
					comWithdrawal16,
					comWithdrawal17,
					comWithdrawal18,
					comWithdrawal19,
					comWithdrawal20,
					comWithdrawal21,
					comWithdrawal22,
					comWithdrawal23
			));
		}

	}

}
