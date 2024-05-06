package carbonneutral.academy.domain.use.repository;

import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyUseStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.QGetDailyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.QGetDailyUseStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyUseStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.QGetMonthlyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.QGetMonthlyUseStatisticsRes;
import carbonneutral.academy.domain.use.enums.UseStatus;
import carbonneutral.academy.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static carbonneutral.academy.domain.use.QUse.use;

@Repository
@RequiredArgsConstructor
public class UseQueryRepository {

    private final JPAQueryFactory queryFactory;


    public List<GetDailyUseStatisticsRes> getDailyUseStatistics(User user) {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(7);
        return queryFactory
                .select(new QGetDailyUseStatisticsRes(
                        use.useAt.dayOfWeek().intValue(),
                        use.status.when(UseStatus.USING).then(1)
                                  .when(UseStatus.RETURNED).then(1)
                                  .otherwise(0).sum()
                ))
                .from(use)
                .where(use.user.eq(user)
                        .and(use.useAt.after(oneWeekAgo))
                        .and(use.useAt.before(LocalDateTime.now())))
                .groupBy(use.useAt.dayOfWeek())
                .orderBy(use.useAt.dayOfWeek().asc())
                .fetch();
    }
    public List<GetDailyReturnStatisticsRes> getDailyReturnStatistics(User user) {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(7);
        return queryFactory
                .select(new QGetDailyReturnStatisticsRes(
                        use.returnTime.dayOfWeek().intValue(),
                        use.status.when(UseStatus.RETURNED).then(1).otherwise(0).sum()
                ))
                .from(use)
                .where(use.user.eq(user)
                        .and(use.returnTime.after(oneWeekAgo))
                        .and(use.returnTime.before(LocalDateTime.now())))
                .groupBy(use.returnTime.dayOfWeek())
                .orderBy(use.returnTime.dayOfWeek().asc())
                .fetch();
    }

    public List<GetMonthlyUseStatisticsRes> getMonthlyUseStatistics(User user) {
        LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);
        return queryFactory
                .select(new QGetMonthlyUseStatisticsRes(
                        use.useAt.month().intValue(),
                        use.status.when(UseStatus.USING).then(1)
                                .when(UseStatus.RETURNED).then(1)
                                .otherwise(0).sum()
                ))
                .from(use)
                .where(use.user.eq(user)
                        .and(use.useAt.after(oneYearAgo))
                        .and(use.useAt.before(LocalDateTime.now())))
                .groupBy(use.useAt.month())
                .orderBy(use.useAt.month().asc())
                .fetch();
    }

    public List<GetMonthlyReturnStatisticsRes> getMonthlyReturnStatistics(User user) {
        LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);
        return queryFactory
                .select(new QGetMonthlyReturnStatisticsRes(
                        use.returnTime.month().intValue(),
                        use.status.when(UseStatus.RETURNED).then(1).otherwise(0).sum()
                ))
                .from(use)
                .where(use.user.eq(user)
                        .and(use.returnTime.after(oneYearAgo))
                        .and(use.returnTime.before(LocalDateTime.now())))
                .groupBy(use.returnTime.month())
                .orderBy(use.returnTime.month().asc())
                .fetch();
    }

}

