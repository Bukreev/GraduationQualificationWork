package ru.GraduationQualificationWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import ru.GraduationQualificationWork.Crawler.Crawler;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;
import ru.GraduationQualificationWork.Model.Entity.Link;
import ru.GraduationQualificationWork.Spider.SpiderController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class GraduationQualificationWorkApplication {


	public static void main(String[] args) {

		final ConfigurableApplicationContext context = SpringApplication.run(GraduationQualificationWorkApplication.class, args);
		final LinkDao linkDao = context.getBean(LinkDao.class);
		final Crawler spider = context.getBean(Crawler.class);

		try {
		    spider.setBaseUrl("http://localhost:8080");
			spider.crawl(3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Link> request = linkDao.getAllLinks();

		request.stream().map((x) -> x.getAdress())
				.peek((x) -> System.out.println("#### " + x))
				.collect(Collectors.toSet());

	}
}
