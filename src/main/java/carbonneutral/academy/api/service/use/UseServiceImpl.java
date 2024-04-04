package carbonneutral.academy.api.service.use;

import carbonneutral.academy.domain.use.repository.UseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UseServiceImpl implements UseService {

    private final UseRepository useRepository;
}
