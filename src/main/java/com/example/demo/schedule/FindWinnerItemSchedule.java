package com.example.demo.schedule;

import com.example.demo.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Slf4j
@AllArgsConstructor
public class FindWinnerItemSchedule {

    private final ItemService itemService;

    @Transactional
    @Scheduled(cron = "0 */2 * * * ?")
    @SchedulerLock(name = "schedule-find-winner-item", lockAtLeastFor = "1m", lockAtMostFor = "2m")
    public void findWinner() {
        log.info(">>>>>>>>>>>>>> Start find winner <<<<<<<<<<<<<<<<<");
        itemService.updateWinner();
        log.info(">>>>>>>>>>>>>> End find winner <<<<<<<<<<<<<<<<<");
    }

}