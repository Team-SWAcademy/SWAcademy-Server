package carbonneutral.academy.domain.use.repository;

import carbonneutral.academy.api.controller.use.dto.response.GetDailyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.GetDailyUseStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.QGetDailyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.QGetDailyUseStatisticsRes;
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


}

