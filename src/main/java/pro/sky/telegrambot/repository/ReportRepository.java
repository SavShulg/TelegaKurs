package pro.sky.telegrambot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Report;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    final Logger logger = LoggerFactory.getLogger(ReportRepository.class);

    List<Report> findByIdReport (ReportRepository Long);



}
